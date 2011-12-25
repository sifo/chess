package chess.behavior.move
import chess.entity.Piece
import chess.entity.Position
import chess.entity.MovementInfo

trait AnySimpleMove extends MoveBehavior {

	override def canMove(movementInfo: MovementInfo): Boolean = {
		val dest = movementInfo.dst
		val pos = movementInfo.src
		val dim = movementInfo.boardDim
		if (!dim.isInBounds(dest) || pos.equals(dest)) {
			return super.canMove(movementInfo)
		}
		if ((dest.x == pos.x && Math.abs(dest.y - pos.y) == 1)
			|| (dest.y == pos.y && Math.abs(dest.x - pos.x) == 1)
			|| (Math.abs(dest.x - pos.x) == 1 && Math.abs(dest.y - pos.y) == 1)) {
			return true
		}
		super.canMove(movementInfo)
	}
}