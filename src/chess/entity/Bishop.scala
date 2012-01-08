package chess.entity
import chess.behavior.move.DiagonalMove
import chess.behavior.move.MoveBehavior

class Bishop extends Piece {
	moveBehavior = new MoveBehavior with DiagonalMove
	promoteBehavior = null
	
	override def name() = "Bishop";
}