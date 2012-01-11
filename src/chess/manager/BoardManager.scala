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
	var isCheck: Boolean = _
	var isCheckMate: Boolean = _
	var piecesTaken = List[Piece]()
	var pieces = List[Piece]()
	loadConfig(chessModel.config)

	def loadConfig(configFile: String) {
		history = new ChessHistory
		isCheck = false
		isCheckMate = false
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
			pieces = board.squares(x)(y) :: pieces
		}
	}

	def move(dest: Position, piece: Piece) {
		if (canMove(dest, piece) && chessModel.playerManager.isPlayerTurn(piece)) {
			val pieceTaken = board.squares(dest.x)(dest.y)
			piecesTaken = pieceTaken :: piecesTaken
			val oldPos = piece.position
			board.squares(dest.x)(dest.y) = piece
			board.squares(piece.position.x)(piece.position.y) = null
			piece.position = new Position(dest.x, dest.y)
			if (isCheckSituation(chessModel.playerManager.currentPlayerIndex)) {
				piecesTaken = piecesTaken.tail
				board.squares(dest.x)(dest.y) = pieceTaken
				board.squares(oldPos.x)(oldPos.y) = piece
				piece.position = oldPos
				chessModel.fireImpossibleMovement()
				return
			}
			isCheck = false
			if (pieceTaken != null) {
				piecesTaken = pieceTaken :: piecesTaken
			}
			var action = new Action(oldPos, dest, piece)
			history.addAction(action)
			isCheck = isCheckSituation(chessModel.playerManager.getNextPlayer())
			if (isCheck) {
				if (isCheckMateSituation()) {
					isCheckMate = true
					chessModel.fireCheckMate()
					return
				}
			}
			chessModel.playerManager.setNextPlayer
		}
	}

	def canMove(dest: Position, piece: Piece): Boolean = {
		var mvtInfo = new MovementInfo(piece.position, dest, piece, board, history)
		if (isHorizontalConnected) {
			mvtInfo = adaptMovementInfoForHorizontalBoard(mvtInfo)
			val dst = new Position(mvtInfo.dst.x, mvtInfo.dst.y)
			mvtInfo.dst = dst
			if (piece.canMove(mvtInfo)) return true
			dst.x = dst.x - board.dimension.width
			if (piece.canMove(mvtInfo)) return true
			dst.x = dst.x + 2 * board.dimension.width
			if (piece.canMove(mvtInfo)) return true
		}
		if (isVerticalConnected) {
			mvtInfo = adaptMovementInfoForVerticalBoard(mvtInfo)
			val dst = new Position(mvtInfo.dst.x, mvtInfo.dst.y)
			mvtInfo.dst = dst
			if (piece.canMove(mvtInfo)) return true
			dst.y = dst.y - board.dimension.height
			if (piece.canMove(mvtInfo)) return true
			dst.y = dst.y + 2 * board.dimension.height
			if (piece.canMove(mvtInfo)) return true
		}
		if (piece.canMove(mvtInfo)) {
			return true
		}
		return false
	}

	def adaptMovementInfoForHorizontalBoard(mvtInfo: MovementInfo): MovementInfo = {
		val dimension = new Dimension(board.dimension.width * 3, board.dimension.height)
		val boardH = new ChessBoard(dimension)
		for (x <- 0 to board.dimension.width - 1) {
			for (y <- 0 to board.dimension.height - 1) {
				val piece = board.squares(x)(y)
				boardH.squares(x)(y) = piece
				boardH.squares(x + board.dimension.width)(y) = piece
				boardH.squares(x + board.dimension.width * 2)(y) = piece
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
				val piece = board.squares(x)(y)
				boardH.squares(x)(y) = piece
				boardH.squares(x)(y + board.dimension.height) = piece
				boardH.squares(x)(y + board.dimension.height * 2) = piece
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

	def isCheckSituation(playerIndex: Int): Boolean = {
//		val attacker = chessModel.playerManager.currentPlayerIndex
		return canCapturePiece(kingOfPlayer(playerIndex))
	}

	def canCapturePiece(piece: Piece): Boolean = {
		for (p <- pieces) {
			if (!piecesTaken.contains(p) && piece.color != p.color) {
				if (canMove(piece.position, p)) {
					return true
				}
			}
		}
		return false
	}
	
	def canCapturePiece(attackerPlayer: Int, piece: Piece): Boolean = {
		for (p <- pieces) {
			if (!piecesTaken.contains(p) 
					&& PlayerManager.playerColorToNumber(p.color) == attackerPlayer) {
				if (canMove(piece.position, p)) {
					return true
				}
			}
		}
		return false
	}

	def kingOfPlayer(playerIndex: Int): Piece = {
		var res = null
		for (p <- pieces) {
			if (p.isInstanceOf[King]) {
				val n = PlayerManager.playerColorToNumber(p.color)
				if (n == playerIndex)
					return p
			}
		}
		return res
	}

	def isCheckMateSituation(): Boolean = {
		val defender = chessModel.playerManager.getNextPlayer
		for (p <- pieces) {
			val n = PlayerManager.playerColorToNumber(p.color)
			if (n == defender) {
				val mvtInfo = new MovementInfo(p.position, null, p, board, null)
				val possibleMoves = p.moveBehavior.possibleMoves(mvtInfo)
				for (i <- 0 to possibleMoves.length - 1) {
					for (j <- 0 to possibleMoves(i).length - 1) {
						val dest = new Position(i, j)
						val pieceTaken = board.squares(dest.x)(dest.y)
						val oldPos = p.position
						if (possibleMoves(i)(j) == 1) {
							if(pieceTaken != null)
								piecesTaken = pieceTaken :: piecesTaken
							board.squares(dest.x)(dest.y) = p
							board.squares(p.position.x)(p.position.y) = null
							p.position = new Position(dest.x, dest.y)
							var flag = false
							if (!isCheckSituation(defender)) {
								flag = true
							}
							board.squares(dest.x)(dest.y) = pieceTaken
							if(pieceTaken != null)
								piecesTaken = piecesTaken.tail
							board.squares(oldPos.x)(oldPos.y) = p
							p.position = oldPos
							if (flag)
								return false
						}
					}
				}

			}
		}
		return true
	}
}