package chess.behavior.move;
import chess.entity.Position

trait VerticalMove extends MoveBehavior {
	
	abstract override def canMove(pos: Position, dest: Position): Boolean = {
		super.canMove(pos, dest) 
	}
}