package chess.entity
import chess.behavior.move.SimpleMove
import chess.behavior.promote.StandardPromote

class Pawn extends Piece {
	override val moveBehavior = new SimpleMove {}
	override var promoteBehavior = new StandardPromote {}
}