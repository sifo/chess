package chess.behavior.promote
import chess.entity.MovementInfo

trait PromoteBehavior {
	def canPromote(movementInfo : MovementInfo): Boolean = {
		false
	}
}