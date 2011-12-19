package chess.entity
import chess.behavior.move.LMove

class Knight extends Piece {
	override var moveBehavior = new LMove {}
	override var promoteBehavior = null
}