package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.ChessBoard

trait VerticalMove extends MoveBehavior {
	
	override def canMove(movementInfo: MovementInfo): Boolean = {
		val dest = movementInfo.dst
		val pos = movementInfo.src
		val board = movementInfo.chessBoard
		if (dest.x == pos.x && dest.y != pos.y) {
			if (!isBlockedByPiece(board, pos, dest)) {
				return true
			}
		}
		super.canMove(movementInfo)
	}
	
	private def isBlockedByPiece(
		board: ChessBoard, pos: Position, dest: Position): Boolean = {
		var min = Math.min(pos.y, dest.y) + 1
		var max = Math.max(pos.y, dest.y) - 1
		for(i <- min to max) {
			if(board.squares(pos.x)(i) != null)
				return true
		}
		return false
	}
}