package chess.behavior.promote

trait StandardPromote extends PromoteBehavior {
	override def canPromote(): Boolean = {
		false
	}
}