package chess.behavior.move
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.Color._
import chess.entity.Pawn
import chess.history.Action
import scala.xml.XML
import chess.manager.PlayerManager
import chess.entity.Piece
import chess.history.ChessHistory

trait SimpleMove extends MoveBehavior {

	override def canMove(mvtInfo: MovementInfo): Boolean = {
		if (!respectPrecondition(mvtInfo)) {
			return false
		}
		val piece = mvtInfo.chessBoard.squares(mvtInfo.dst.x)(mvtInfo.dst.y)
		if (possibleMoves(mvtInfo)(mvtInfo.dst.x)(mvtInfo.dst.y) == 1) {
			if(mvtInfo.dst.x != mvtInfo.src.x
				&& mvtInfo.dst.y != mvtInfo.src.y 
				&& piece != null && piece.color != mvtInfo.piece.color) {
					return true
			} else {
				return true
			}
		}
		return super.canMove(mvtInfo)
	}

	override def possibleMoves(mvtInfo: MovementInfo): Array[Array[Int]] = {
		var output = Array.fill[Int](mvtInfo.chessBoard.dimension.width, mvtInfo.chessBoard.dimension.height)(0)
		val src = mvtInfo.src
		val direction = getDirection("res/standard-chess-config.xml", mvtInfo.piece.color)
		val firstMove = isFirstMove(mvtInfo.piece, mvtInfo.history)
		val lastMvt = mvtInfo.history.getLastAction()

		if (direction == "north") {
			if (src.y + 1 < mvtInfo.chessBoard.dimension.height
					&& mvtInfo.chessBoard.squares(src.x)(src.y + 1) == null)
				output(src.x)(src.y + 1) = 1
			if (firstMove && src.y + 2 < mvtInfo.chessBoard.dimension.height
					&& mvtInfo.chessBoard.squares(src.x)(src.y + 2) == null)
				output(src.x)(src.y + 2) = 1
			if(src.x + 1 < mvtInfo.chessBoard.dimension.width
					&& src.y + 1 < mvtInfo.chessBoard.dimension.height) {
				output(src.x + 1)(src.y + 1) = 1
			}
			if(src.x - 1 >= 0
				&& src.y + 1 < mvtInfo.chessBoard.dimension.height) {
					output(src.x - 1)(src.y + 1) = 1
			}
		}

		if (direction == "south") {
			if (src.y - 1 >= 0
					&& mvtInfo.chessBoard.squares(src.x)(src.y - 1) == null)
				output(src.x)(src.y - 1) = 1
			if (firstMove && src.y - 2 >= 0
					&& mvtInfo.chessBoard.squares(src.x)(src.y - 2) == null)
				output(src.x)(src.y - 2) = 1
			if(src.x + 1 < mvtInfo.chessBoard.dimension.width
					&& src.y - 1 >= 0) {
					output(src.x + 1)(src.y - 1) = 1
			}
			if(src.x - 1 >= 0 && src.y - 1 >= 0) {
					output(src.x - 1)(src.y - 1) = 1
			}
		}

		if (direction == "west") {
			if (src.x - 1 >= 0
					&& mvtInfo.chessBoard.squares(src.x - 1)(src.y) == null)
				output(src.x - 1)(src.y) = 1
			if (firstMove && src.x - 2 >= 0
					&& mvtInfo.chessBoard.squares(src.x)(src.x - 2) == null)
				output(src.x - 2)(src.y) = 1
			if(src.x - 1 >= 0
					&& src.y + 1 < mvtInfo.chessBoard.dimension.height) {
					output(src.x - 1)(src.y + 1) = 1
			}
			if(src.x - 1 >= 0 && src.y - 1 >= 0) {
					output(src.x - 1)(src.y - 1) = 1
			}
		}

		if (direction == "east") {
			if (src.x + 1 < mvtInfo.chessBoard.dimension.width
					&& mvtInfo.chessBoard.squares(src.x + 1)(src.y) == null)
				output(src.x + 1)(src.y) = 1
			if (firstMove && src.x + 2 < mvtInfo.chessBoard.dimension.width
					&& mvtInfo.chessBoard.squares(src.x + 2)(src.y) == null)
				output(src.x + 2)(src.y) = 1
			if(src.x + 1 < mvtInfo.chessBoard.dimension.width
					&& src.y + 1 < mvtInfo.chessBoard.dimension.height) {
					output(src.x + 1)(src.y + 1) = 1
			}
			if(src.x + 1 < mvtInfo.chessBoard.dimension.width
					&& src.y - 1 >= 0) {
					output(src.x + 1)(src.y - 1) = 1
			}
		}

		// Prise en passant
		if (lastMvt != null) {
			val piece = lastMvt.piece
			if (piece.isInstanceOf[Pawn]) {
				if (Math.abs(lastMvt.src.y - lastMvt.dst.y) == 2) {
					if (Math.abs(piece.position.x - mvtInfo.piece.position.x) == 1
						&& piece.position.y == mvtInfo.piece.position.y) {
						if(direction == "north")
							output(piece.position.x)(piece.position.y + 1) = 1
						if(direction == "south")
							output(piece.position.x)(piece.position.y - 1) = 1
					}
				} else if (Math.abs(lastMvt.src.x - lastMvt.dst.x) == 2
						&& piece.position.x == mvtInfo.piece.position.x) {
						if(direction == "east")
							output(piece.position.x + 1)(piece.position.y) = 1
						if(direction == "west")
							output(piece.position.x - 1)(piece.position.y) = 1
				}
			}
		}

		output
	}

	private def isFirstMove(piece: Piece, history: ChessHistory): Boolean = {
		val list = history.listeActions
		for (h <- list) {
			if (h.piece == piece) {
				return false
			}
		}
		return true
	}

	private def getDirection(configFile: String, color: Color): String = {
		val playerNumber = PlayerManager.playerColorToNumber(color)
		val config = XML.loadFile(configFile)
		for (val entry <- config \\ "player-config" \ "player-direction") {
			val n = (entry \ "@player").text.toInt
			if (playerNumber == n) {
				return (entry \ "@direction").text
			}
		}
		return "north"
	}

}