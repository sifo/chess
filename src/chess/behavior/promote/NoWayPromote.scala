package chess.behavior.promote
import chess.history.Action

trait NoWayPromote extends PromoteBehavior {
	
	override def canPromote(action: Action): Boolean = {
		false
	}
}