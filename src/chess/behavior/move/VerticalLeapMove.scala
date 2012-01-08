package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.ChessBoard

trait VerticalLeapMove extends MoveBehavior {

	override def canMove(movementInfo: MovementInfo): Boolean = {
		val dest = movementInfo.dst
		val pos = movementInfo.src
		val board = movementInfo.chessBoard
		if (dest.x == pos.x && dest.y != pos.y) {
			if (numberOfPieceOnPath(board, pos, dest) == 1) {
				return true
			}
		}
		super.canMove(movementInfo)
	}

	private def numberOfPieceOnPath(
		board: ChessBoard, pos: Position, dest: Position): Int = {
		var min = Math.min(pos.y, dest.y) + 1
		var max = Math.max(pos.y, dest.y) - 1
		var res = 0
		for (i <- min to max) {
			if (board.squares(pos.x)(i) != null)
				res = res + 1
		}
		return res
	}
}