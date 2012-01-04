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
	loadConfig("res/standard-chess-config.xml")

	def loadConfig(configFile: String) {
		history = new ChessHistory
		val config = XML.loadFile(configFile)
		val is3DBoard = (config \\ "three-dimensional-board" \ "@enabled").text.toBoolean
		val isHorizontalConnected = (config \\ "horizontal-connected-board" \ "@enabled").text.toBoolean
		val isVerticalConnected = (config \\ "vertical-connected-board" \ "@enabled").text.toBoolean
		val width = (config \\ "board-dimension" \ "@width").text.toInt
		val length = (config \\ "board-dimension" \ "@length").text.toInt
		var dim: Dimension = null
		if (is3DBoard) {
			val height = (config \\ "board-dimension" \ "@height").text.toInt
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

	def move(pos: Position, piece: Piece) {
		var mvtInfo = new MovementInfo(piece.position, pos, piece, board, history)
		if (piece.canMove(mvtInfo)) {
			var action = new Action(piece.position, pos, piece)
			history.addAction(action)
			board.squares(pos.x)(pos.y) = piece
			board.squares(piece.position.x)(piece.position.y) = null
			piece.position = new Position(pos.x, pos.y)
			chessModel.playerManager.setNextPlayer
		}
	}
}