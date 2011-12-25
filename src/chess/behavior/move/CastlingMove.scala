package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo

trait CastlingMove extends MoveBehavior {
	
	override def canMove(movementInfo : MovementInfo): Boolean = {
		super.canMove(movementInfo)
	}
}