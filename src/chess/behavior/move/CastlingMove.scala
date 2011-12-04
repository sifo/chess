package chess.behavior.move;
import chess.entity.Position

trait CastlingMove extends MoveBehavior {
	
	abstract override def canMove(pos: Position, dest: Position): Boolean = {
		false
	}	
}