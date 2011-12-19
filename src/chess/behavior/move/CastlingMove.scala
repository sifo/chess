package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo

trait CastlingMove extends MoveBehavior {
	
	abstract override def canMove(movementInfo : MovementInfo): Boolean = {
		super.canMove(movementInfo)
	}
}