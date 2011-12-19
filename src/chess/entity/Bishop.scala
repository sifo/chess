package chess.entity
import chess.behavior.move.DiagonalMove

class Bishop extends Piece {
	override var moveBehavior = new DiagonalMove {}
	override var promoteBehavior = null
}