package chess.entity
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.AnySimpleMove
import chess.behavior.move.MoveBehavior
import chess.behavior.promote.PromoteBehavior

class Queen extends Piece {
	moveBehavior = new MoveBehavior with AnySimpleMove
	promoteBehavior = new PromoteBehavior with NoWayPromote
}