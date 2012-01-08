package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.ChessBoard

trait HorizontalMove extends MoveBehavior {

	override def canMove(movementInfo: MovementInfo): Boolean = {
		val dest = movementInfo.dst
		val pos = movementInfo.src
		val board = movementInfo.chessBoard
		if (dest.y == pos.y && dest.x != pos.x) {
			if (!isBlockedByPiece(board, pos, dest)) {
				return true
			}
		}
		super.canMove(movementInfo)
	}

	private def isBlockedByPiece(
		board: ChessBoard, pos: Position, dest: Position): Boolean = {
		var min = Math.min(pos.x, dest.x) + 1
		var max = Math.max(pos.x, dest.x) - 1
		for(i <- min to max) {
			if(board.squares(i)(pos.y) != null)
				return true
		}
		return false
	}
}