package chess.entity
import chess.behavior.move.HorizontalMove
import chess.behavior.move.VerticalMove
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.MoveBehavior

class Rook {
	val moveBehavior = new HorizontalMove with VerticalMove {}
	var promoteBehavior = new NoWayPromote {}
}