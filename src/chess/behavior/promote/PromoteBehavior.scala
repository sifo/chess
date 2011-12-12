package chess.behavior.promote
import chess.history.Action

trait PromoteBehavior {
	def canPromote(action: Action): Boolean = {
		false
	}
}