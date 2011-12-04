package chess.behavior.promote

trait NoWayPromote extends PromoteBehavior {
	
	override def canPromote(): Boolean = {
		false
	}
}