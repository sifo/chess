package chess.behavior.move;
import chess.entity.Position

trait SimpleMove extends MoveBehavior {
	
	abstract override def canMove(init: Position, dest: Position): Boolean = {
		false
	}
}