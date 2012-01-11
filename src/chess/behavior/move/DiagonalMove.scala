package chess.behavior.move;
import chess.entity.Position
import chess.history.Action
import chess.entity.MovementInfo
import chess.entity.ChessBoard

trait DiagonalMove extends MoveBehavior {

	override def possibleMoves(mvtInfo: MovementInfo): Array[Array[Int]] = {
		var output = Array.fill[Int](mvtInfo.chessBoard.dimension.width, mvtInfo.chessBoard.dimension.height)(0);

		var y = mvtInfo.src.y + 1
		var x = mvtInfo.src.x + 1
		var continueLoop = true
		while (continueLoop
			&& y < mvtInfo.chessBoard.dimension.height
			&& x < mvtInfo.chessBoard.dimension.width) {
			val pieceDst = mvtInfo.chessBoard.squares(x)(y)			
			if (pieceDst != null && mvtInfo.piece.color == pieceDst.color) { }
			else if (numberOfPieceOnPath(mvtInfo.chessBoard, mvtInfo.src, new Position(x, y)) == 0)
				output(x)(y) = 1
			else
				continueLoop = false
			x += 1
			y += 1
		}

		continueLoop = true
		y = mvtInfo.src.y + 1
		x = mvtInfo.src.x - 1
		while (continueLoop && y < mvtInfo.chessBoard.dimension.height
			&& x >= 0) {
			val pieceDst = mvtInfo.chessBoard.squares(x)(y)			
			if (pieceDst != null && mvtInfo.piece.color == pieceDst.color) { }
			else if (numberOfPieceOnPath(mvtInfo.chessBoard, mvtInfo.src, new Position(x, y)) == 0)
				output(x)(y) = 1
			else
				continueLoop = false
			x -= 1
			y += 1
		}

		continueLoop = true
		y = mvtInfo.src.y - 1
		x = mvtInfo.src.x - 1
		while (continueLoop && y >= 0 && x >= 0) {
			val pieceDst = mvtInfo.chessBoard.squares(x)(y)			
			if (pieceDst != null && mvtInfo.piece.color == pieceDst.color) { }
			else if (numberOfPieceOnPath(mvtInfo.chessBoard, mvtInfo.src, new Position(x, y)) == 0)
				output(x)(y) = 1
			else
				continueLoop = false
			x -= 1
			y -= 1
		}

		continueLoop = true
		y = mvtInfo.src.y - 1
		x = mvtInfo.src.x + 1
		while (continueLoop && y >= 0 && x < mvtInfo.chessBoard.dimension.width) {
			val pieceDst = mvtInfo.chessBoard.squares(x)(y)			
			if (pieceDst != null && mvtInfo.piece.color == pieceDst.color) { }
			else if (numberOfPieceOnPath(mvtInfo.chessBoard, mvtInfo.src, new Position(x, y)) == 0)
				output(x)(y) = 1
			else
				continueLoop = false
			x += 1
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
		if (Math.abs(pos.x - dest.x) == Math.abs(pos.y - dest.y)) {
			if (numberOfPieceOnPath(mvtInfo.chessBoard, pos, dest) == 0) {
				return true
			}
		}
		return super.canMove(mvtInfo)
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
}