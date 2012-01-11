package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.ChessBoard

trait HorizontalLeapMove extends MoveBehavior {

	override def possibleMoves(mvtInfo: MovementInfo): Array[Array[Int]] = {
		var output = Array.fill[Int](mvtInfo.chessBoard.dimension.width, mvtInfo.chessBoard.dimension.height)(0);

		var continueLoop = true
		var y = mvtInfo.src.y
		var x = mvtInfo.src.x + 1
		while (continueLoop &&
			x < mvtInfo.chessBoard.dimension.width) {
			val pieceDst = mvtInfo.chessBoard.squares(x)(y)			
			if (pieceDst != null && mvtInfo.piece.color == pieceDst.color) { }
			else if (numberOfPieceOnPath(mvtInfo.chessBoard, mvtInfo.src, new Position(x, y)) == 0)
				output(x)(y) = 1
			else
				continueLoop = false
			x += 1
		}

		continueLoop = true
		y = mvtInfo.src.y
		x = mvtInfo.src.x - 1
		while (continueLoop && x >= 0) {
			val pieceDst = mvtInfo.chessBoard.squares(x)(y)			
			if (pieceDst != null && mvtInfo.piece.color == pieceDst.color) { }
			else if (numberOfPieceOnPath(mvtInfo.chessBoard, mvtInfo.src, new Position(x, y)) == 0)
				output(x)(y) = 1
			else
				continueLoop = false
			x -= 1
		}
		return output
	}

	override def canMove(mvtInfo: MovementInfo): Boolean = {
		val dest = mvtInfo.dst
		val pos = mvtInfo.src
		val board = mvtInfo.chessBoard
		if (!respectPrecondition(mvtInfo)) {
			return false
		}
		if (dest.y == pos.y && dest.x != pos.x) {
			if (numberOfPieceOnPath(board, pos, dest) == 1) {
				return true
			}
		}
		super.canMove(mvtInfo)
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