package chess.behavior.promote
import chess.entity.MovementInfo

abstract class PromoteBehavior {
	def canPromote(movementInfo : MovementInfo): Boolean = {
		false
	}
}