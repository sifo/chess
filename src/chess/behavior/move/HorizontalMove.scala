package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo

trait HorizontalMove extends MoveBehavior {
	
	override def canMove(movementInfo : MovementInfo): Boolean = {
		super.canMove(movementInfo)
	}
}