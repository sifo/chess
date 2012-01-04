package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo

trait VerticalMove extends MoveBehavior {
	
	override def canMove(movementInfo: MovementInfo): Boolean = {
		val dest = movementInfo.dst
		val pos = movementInfo.src
		val chessBoard = movementInfo.chessBoard
		if (!chessBoard.dimension.isInBounds(dest) || pos.equals(dest)) {
			return super.canMove(movementInfo)
		}
		if (dest.x == pos.x && dest.y != pos.y) {
			return true
		}
		super.canMove(movementInfo)
	}
}