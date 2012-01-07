package chess.entity
import chess.behavior.move.LMove
import chess.behavior.move.MoveBehavior
import chess.behavior.promote.PromoteBehavior
import chess.behavior.promote.NoWayPromote

class Knight extends Piece {
	moveBehavior = new MoveBehavior with LMove
	promoteBehavior = new PromoteBehavior with NoWayPromote
}