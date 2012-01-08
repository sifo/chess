package chess.entity
import chess.behavior.move.DiagonalMove
import chess.behavior.move.MoveBehavior
import chess.behavior.promote.PromoteBehavior
import chess.behavior.promote.NoWayPromote

class Bishop extends Piece {
	
	moveBehavior = new MoveBehavior with DiagonalMove
	promoteBehavior = new PromoteBehavior with NoWayPromote
	override def name() = "Bishop";
}