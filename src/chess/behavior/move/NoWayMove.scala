package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo

trait NoWayMove extends MoveBehavior {
	
	override def canMove(movementInfo : MovementInfo): Boolean = {
		false || super.canMove(movementInfo)
	}	
}