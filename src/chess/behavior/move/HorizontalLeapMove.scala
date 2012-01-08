package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.ChessBoard

trait HorizontalLeapMove extends MoveBehavior {

	override def canMove(movementInfo: MovementInfo): Boolean = {
		val dest = movementInfo.dst
		val pos = movementInfo.src
		val board = movementInfo.chessBoard
		if (dest.y == pos.y && dest.x != pos.x) {
			if (numberOfPieceOnPath(board, pos, dest) == 1) {
				return true
			}
		}
		super.canMove(movementInfo)
	}

	private def numberOfPieceOnPath(
		board: ChessBoard, pos: Position, dest: Position): Int = {
		var min = Math.min(pos.x, dest.x) + 1
		var max = Math.max(pos.x, dest.x) - 1
		var res = 0
		for (i <- min to max) {
			if (board.squares(i)(pos.y) != null)
				res = res + 1
		}
		return res
	}
}