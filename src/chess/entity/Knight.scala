package chess.entity
import chess.behavior.move.LMove
import chess.behavior.move.MoveBehavior

class Knight extends Piece {
	moveBehavior = new MoveBehavior with LMove
	promoteBehavior = null
}