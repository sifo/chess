package chess.entity
import chess.behavior.promote.PromoteBehavior
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.MoveBehavior
import chess.behavior.move.LMove
import chess.behavior.move.DiagonalMove

class Princess extends Piece {
	moveBehavior = new MoveBehavior with LMove with DiagonalMove
	promoteBehavior = new PromoteBehavior with NoWayPromote
}