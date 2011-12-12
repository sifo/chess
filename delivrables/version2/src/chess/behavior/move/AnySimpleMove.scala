package chess.behavior.move
import chess.entity.Piece
import chess.entity.Position

trait AnySimpleMove extends MoveBehavior {

	abstract override def canMove(pos: Position, dest: Position, dimension: Int): Boolean = {
		false
	}
}