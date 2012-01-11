package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.ChessBoard

trait VerticalLeapMove extends MoveBehavior {
	
	override def possibleMoves(mvtInfo: MovementInfo): Array[Array[Int]] = {
		var output = Array.fill[Int](mvtInfo.chessBoard.dimension.width, mvtInfo.chessBoard.dimension.height)(0);

		var y = mvtInfo.src.y + 1
		var x = mvtInfo.src.x
		var continueLoop = true
		while (continueLoop
			&& y < mvtInfo.chessBoard.dimension.height) {
			val pieceDst = mvtInfo.chessBoard.squares(x)(y)			
			if (pieceDst != null && mvtInfo.piece.color == pieceDst.color) { }
			else if (numberOfPieceOnPath(mvtInfo.chessBoard, mvtInfo.src, new Position(x, y)) == 0)
				output(x)(y) = 1
			else
				continueLoop = false
			y += 1
		}

		continueLoop = true
		y = mvtInfo.src.y - 1
		x = mvtInfo.src.x
		while (continueLoop
			&& y >= 0) {
			val pieceDst = mvtInfo.chessBoard.squares(x)(y)			
			if (pieceDst != null && mvtInfo.piece.color == pieceDst.color) { }
			else if (numberOfPieceOnPath(mvtInfo.chessBoard, mvtInfo.src, new Position(x, y)) == 0)
				output(x)(y) = 1
			else
				continueLoop = false
			y -= 1
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
		if (dest.x == pos.x && dest.y != pos.y) {
			if (numberOfPieceOnPath(board, pos, dest) == 1) {
				return true
			}
		}
		super.canMove(mvtInfo)
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