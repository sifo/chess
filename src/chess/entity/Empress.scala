package chess.entity
import chess.behavior.promote.PromoteBehavior
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.MoveBehavior
import chess.behavior.move.HorizontalMove
import chess.behavior.move.VerticalMove
import chess.behavior.move.LMove

class Empress extends Piece{
	moveBehavior = new MoveBehavior with HorizontalMove with VerticalMove with LMove
	promoteBehavior = new PromoteBehavior with NoWayPromote
	
	override def name() = "Empress";
}