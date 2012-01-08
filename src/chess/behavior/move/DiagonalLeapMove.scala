package chess.behavior.move;
import chess.entity.Position
import chess.history.Action
import chess.entity.MovementInfo
import chess.entity.ChessBoard

trait DiagonalLeapMove extends MoveBehavior {

	override def possibleMoves(mvtInfo: MovementInfo): Array[Array[Int]] = {
		var output = Array.fill[Int](mvtInfo.chessBoard.dimension.height, mvtInfo.chessBoard.dimension.width)(0);

		/*
     * Fills the array with 1 where the DiagonalMove can be performed.
     * Is the piece would move out of the board, the exception is 
     * caught to do nothing
     */
		if (mvtInfo.src.x + 1 < mvtInfo.chessBoard.dimension.height
			&& mvtInfo.src.y + 1 < mvtInfo.chessBoard.dimension.width)
			output(mvtInfo.src.x + 1)(mvtInfo.src.y + 1) = 1;
		if (mvtInfo.src.x + 1 < mvtInfo.chessBoard.dimension.height
			&& mvtInfo.src.y - 1 >= 0)
			output(mvtInfo.src.x + 1)(mvtInfo.src.y - 1) = 1;
		if (mvtInfo.src.x - 1 >= 0
			&& mvtInfo.src.y + 1 < mvtInfo.chessBoard.dimension.width)
			output(mvtInfo.src.x - 1)(mvtInfo.src.y + 1) = 1;
		if (mvtInfo.src.x - 1 >= 0
			&& mvtInfo.src.y - 1 >= 0)
			output(mvtInfo.src.x - 1)(mvtInfo.src.y - 1) = 1;

		output;
	}

	private def numberOfPieceOnPath(
		board: ChessBoard, pos: Position, dest: Position): Int = {
		var min = Math.min(pos.x, dest.x) + 1
		var max = Math.max(pos.x, dest.x) - 1
		var res = 0
		var y = pos.y
		var decal = if (pos.y < dest.y) 1 else -1
		for (i <- min to max) {
			y = y + decal
			if (board.squares(i)(y) != null)
				res = res + 1
		}
		return res
	}

	override def canMove(mvtInfo: MovementInfo): Boolean = {
		val dest = mvtInfo.dst
		val pos = mvtInfo.src
		val board = mvtInfo.chessBoard
		if (Math.abs(pos.x - dest.x) == Math.abs(pos.y - dest.y)) {
			if (numberOfPieceOnPath(mvtInfo.chessBoard, pos, dest) == 1) {
				return true
			}
		}
		return super.canMove(mvtInfo)
	}
}