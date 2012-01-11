package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo

trait NoWayMove extends MoveBehavior {

	override def canMove(mvtInfo: MovementInfo): Boolean = {
		if (!respectPrecondition(mvtInfo)) {
			return false
		}
		super.canMove(mvtInfo)
	}
}