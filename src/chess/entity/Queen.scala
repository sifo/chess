package chess.entity
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.SimpleMove

class Queen extends Piece {
	var moveBehavior = new SimpleMove
	var promoteBehavior = new NoWayPromote
}