package chess.manager
import scala.xml.XML
import chess.entity.ChessBoard
import chess.entity.Dimension
import chess.entity.MovementInfo
import chess.entity.Piece
import chess.entity.Position
import chess.history.Action
import chess.history.ChessHistory
import chess.ChessModel
import chess.entity.Rook
import chess.entity.Knight
import chess.entity.King
import chess.entity.Bishop
import chess.entity.Queen
import chess.entity.Pawn
import chess.entity.Color._
import chess.entity.Player
import chess.entity.Princess
import chess.entity.Grasshopper
import chess.entity.Empress
import chess.entity.Nightrider
import chess.entity.Piece

object BoardManager {

	def buildPiece(pieceType: String, playerNumber: Int, position: Position): Piece = {
		var piece: Piece = null
		pieceType match {
			case "rook" => piece = new Rook()
			case "knight" => piece = new Knight()
			case "bishop" => piece = new Bishop()
			case "queen" => piece = new Queen()
			case "king" => piece = new King()
			case "pawn" => piece = new Pawn()
			case "princess" => piece = new Princess()
			case "grasshopper" => piece = new Grasshopper()
			case "empress" => piece = new Empress()
			case "nightrider" => piece = new Nightrider()
			case _ => piece = new Pawn()
		}
		piece.color = PlayerManager.playerNumberToColor(playerNumber)
		piece.position = position
		return piece
	}
}

class BoardManager(val chessModel: ChessModel) {
	var board: ChessBoard = _
	var history: ChessHistory = _
	var isHorizontalConnected: Boolean = _
	var isVerticalConnected: Boolean = _
	var is3DBoard: Boolean = _
	var piecesTaken = List[Piece]()
	loadConfig(chessModel.config)

	def loadConfig(configFile: String) {
		history = new ChessHistory
		val config = XML.loadFile(configFile)
		is3DBoard = (config \\ "three-dimensional-board" \ "@enabled").text.toBoolean
		isHorizontalConnected = (config \\ "horizontal-connected-board" \ "@enabled").text.toBoolean
		isVerticalConnected = (config \\ "vertical-connected-board" \ "@enabled").text.toBoolean
		val width = (config \\ "board-dimension" \ "@width").text.toInt
		val length = (config \\ "board-dimension" \ "@length").text.toInt
		var dim: Dimension = null
		if (is3DBoard) {
			//val height = (config \\ "board-dimension" \ "@height").text.toInt
			//val dim = new Dimension(width, length, height)
		} else {
			dim = new Dimension(width, length)
		}
		board = new ChessBoard(dim)
		for (val entry <- config \\ "board-pieces" \ "piece-setup") {
			val pieceType = (entry \ "@piece").text
			val playerNumber = (entry \ "@player").text.toInt
			val x = (entry \ "@x").text.toInt
			val y = (entry \ "@y").text.toInt
			val position = new Position(x, y)
			board.squares(x)(y) = BoardManager.buildPiece(pieceType, playerNumber, position)
		}
	}

	def move(dest: Position, piece: Piece) {
		if (canMove(dest, piece)) {
			var action = new Action(piece.position, dest, piece)
			history.addAction(action)
			board.squares(dest.x)(dest.y) = piece
			piecesTaken = board.squares(piece.position.x)(piece.position.y) :: piecesTaken
			board.squares(piece.position.x)(piece.position.y) = null
			piece.position = new Position(dest.x, dest.y)
			chessModel.playerManager.setNextPlayer
		}
	}

	def canMove(dest: Position, piece: Piece): Boolean = {
		var mvtInfo = new MovementInfo(piece.position, dest, piece, board, history)
		if (!mvtInfo.chessBoard.dimension.isInBounds(dest)
			|| mvtInfo.src.equals(dest)) {
			return false
		}
		if (chessModel.playerManager.isPlayerTurn(piece)) {
			if (board.dimension.isInBounds(dest)) {
				val pieceDst = board.squares(dest.x)(dest.y)
				if (pieceDst == null || (pieceDst != null && piece.color != pieceDst.color)) {
					if (isHorizontalConnected) {
						mvtInfo = adaptMovementInfoForHorizontalBoard(mvtInfo)
						val dst = new Position(mvtInfo.dst.x, mvtInfo.dst.y)
						mvtInfo.dst = dst
						if(piece.canMove(mvtInfo)) return true
						dst.x = dst.x - board.dimension.width
						if(piece.canMove(mvtInfo)) return true
						dst.x = dst.x + 2 * board.dimension.width
						if(piece.canMove(mvtInfo)) return true
					} else if(isVerticalConnected) {
						mvtInfo = adaptMovementInfoForVerticalBoard(mvtInfo)
						val dst = new Position(mvtInfo.dst.x, mvtInfo.dst.y)
						mvtInfo.dst = dst
						if(piece.canMove(mvtInfo)) return true
						dst.y = dst.y - board.dimension.height
						if(piece.canMove(mvtInfo)) return true
						dst.y = dst.y + 2 * board.dimension.height
						if(piece.canMove(mvtInfo)) return true
					
					} else if (piece.canMove(mvtInfo)) {
						return true
					}
				}
			}
		}
		return false
	}

	def adaptMovementInfoForHorizontalBoard(mvtInfo: MovementInfo): MovementInfo = {
		val dimension = new Dimension(board.dimension.width * 3, board.dimension.height)
		val boardH = new ChessBoard(dimension)
		for (x <- 0 to board.dimension.width - 1) {
			for (y <- 0 to board.dimension.height - 1) {
				boardH.squares(x)(y) = board.squares(x)(y)
				boardH.squares(x + board.dimension.width)(y) = board.squares(x)(y)
				boardH.squares(x + board.dimension.width * 2)(y) = board.squares(x)(y)
			}
		}

		val positionH = new Position(mvtInfo.piece.position.x, mvtInfo.piece.position.y)
		positionH.x = positionH.x + board.dimension.width
		val destH = new Position(mvtInfo.dst.x, mvtInfo.dst.y)
		destH.x = destH.x + board.dimension.width
		val pieceH = mvtInfo.piece
		//		pieceH.position = positionH // attention effet de bord grave

		return new MovementInfo(positionH, destH, pieceH, boardH, history)
	}
	
	def adaptMovementInfoForVerticalBoard(mvtInfo: MovementInfo): MovementInfo = {
		val dimension = new Dimension(board.dimension.width, board.dimension.height * 3)
		val boardH = new ChessBoard(dimension)
		for (x <- 0 to board.dimension.width - 1) {
			for (y <- 0 to board.dimension.height - 1) {
				boardH.squares(x)(y) = board.squares(x)(y)
				boardH.squares(x)(y + board.dimension.height) = board.squares(x)(y)
				boardH.squares(x)(y + board.dimension.height * 2) = board.squares(x)(y)
			}
		}

		val positionH = new Position(mvtInfo.piece.position.x, mvtInfo.piece.position.y)
		positionH.y = positionH.y + board.dimension.height
		val destH = new Position(mvtInfo.dst.x, mvtInfo.dst.y)
		destH.y = destH.y + board.dimension.height
		val pieceH = mvtInfo.piece
		//		pieceH.position = positionH // attention effet de bord grave

		return new MovementInfo(positionH, destH, pieceH, boardH, history)
	}
}