package chess.behavior.move
import chess.entity.Piece
import chess.entity.Position
import chess.entity.MovementInfo

trait AnySimpleMove extends MoveBehavior {

	abstract override def canMove(movementInfo : MovementInfo): Boolean = {
		super.canMove(movementInfo)
	}
}