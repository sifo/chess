package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo

trait HorizontalMove extends MoveBehavior {
	
	override def canMove(movementInfo: MovementInfo): Boolean = {
		val dest = movementInfo.dst
		val pos = movementInfo.src
		val dim = movementInfo.chessBoard.dimension
		if (!dim.isInBounds(dest) || pos.equals(dest)) {
			return super.canMove(movementInfo)
		}
		if (dest.y == pos.y && dest.x != pos.x) {
			return true
		}
		super.canMove(movementInfo)
	}
}