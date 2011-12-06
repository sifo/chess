package chess.behavior.move;
import chess.entity.Position

trait HorizontalMove extends MoveBehavior {
	
	abstract override def canMove(pos: Position, dest: Position, dimension : Int): Boolean = {
		super.canMove(pos, dest, dimension)
	}
}