package chess.behavior.promote
import chess.entity.MovementInfo

trait NoWayPromote extends PromoteBehavior {
	
	abstract override def canPromote(movementInfo : MovementInfo): Boolean = {
		false
	}
}