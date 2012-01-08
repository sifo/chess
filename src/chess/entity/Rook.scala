package chess.entity
import chess.behavior.move.HorizontalMove
import chess.behavior.move.VerticalMove
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.MoveBehavior
import chess.behavior.promote.PromoteBehavior

class Rook extends Piece {
	moveBehavior = new MoveBehavior with HorizontalMove with VerticalMove
	promoteBehavior = new PromoteBehavior with NoWayPromote
	
	override def name() = "Rook";
}