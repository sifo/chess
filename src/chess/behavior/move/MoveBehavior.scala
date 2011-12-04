package chess.behavior.move;
import chess.entity.Position

trait MoveBehavior {
	def canMove(init: Position, dest: Position): Boolean = {
		false
	}
}