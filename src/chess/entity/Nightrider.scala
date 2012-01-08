package chess.entity
import chess.behavior.move.MoveBehavior
import chess.behavior.move.SuperLMove
import chess.behavior.promote.PromoteBehavior
import chess.behavior.promote.NoWayPromote

class Nightrider extends Piece {
	moveBehavior = new MoveBehavior with SuperLMove
	promoteBehavior = new PromoteBehavior with NoWayPromote
	
	override def name() = "Nightrider";
}