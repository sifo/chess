package chess.behavior.move
import chess.entity.Piece
import chess.entity.Position
import chess.entity.MovementInfo

trait AnySimpleMove extends MoveBehavior {

	override def canMove(mvtInfo: MovementInfo): Boolean = {
		val dest = mvtInfo.dst
		val pos = mvtInfo.src
		val board = mvtInfo.chessBoard		
		if (!respectPrecondition(mvtInfo)) {
			return false
		}
		if ((dest.x == pos.x && Math.abs(dest.y - pos.y) == 1)
			|| (dest.y == pos.y && Math.abs(dest.x - pos.x) == 1)
			|| (Math.abs(dest.x - pos.x) == 1 && Math.abs(dest.y - pos.y) == 1)) {
			return true
		}
		super.canMove(mvtInfo)
	}

	override def possibleMoves(mvtInfo: MovementInfo): Array[Array[Int]] = {
		var output = Array.fill[Int](mvtInfo.chessBoard.dimension.width, mvtInfo.chessBoard.dimension.height)(0);

		/*
     * Fills the array with 1 where the DiagonalMove can be performed.
     * Is the piece would move out of the board, the exception is 
     * caught to do nothing
     */
		if (mvtInfo.src.x + 1 < mvtInfo.chessBoard.dimension.width
			&& mvtInfo.src.y + 1 < mvtInfo.chessBoard.dimension.height)
			output(mvtInfo.src.x + 1)(mvtInfo.src.y + 1) = 1;
		if (mvtInfo.src.x + 1 < mvtInfo.chessBoard.dimension.width
			&& mvtInfo.src.y - 1 >= 0)
			output(mvtInfo.src.x + 1)(mvtInfo.src.y - 1) = 1;
		if (mvtInfo.src.x - 1 >= 0
			&& mvtInfo.src.y + 1 < mvtInfo.chessBoard.dimension.height)
			output(mvtInfo.src.x - 1)(mvtInfo.src.y + 1) = 1;
		if (mvtInfo.src.x - 1 >= 0
			&& mvtInfo.src.y - 1 >= 0)
			output(mvtInfo.src.x - 1)(mvtInfo.src.y - 1) = 1;
		if (mvtInfo.src.x + 1 < mvtInfo.chessBoard.dimension.width)
			output(mvtInfo.src.x + 1)(mvtInfo.src.y) = 1;
		if (mvtInfo.src.y - 1 >= 0)
			output(mvtInfo.src.x)(mvtInfo.src.y - 1) = 1;
		if (mvtInfo.src.x - 1 >= 0)
			output(mvtInfo.src.x - 1)(mvtInfo.src.y) = 1;
		if (mvtInfo.src.y + 1 < mvtInfo.chessBoard.dimension.height)
			output(mvtInfo.src.x)(mvtInfo.src.y + 1) = 1;
		output;
	}
}