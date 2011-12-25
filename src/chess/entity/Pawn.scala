package chess.entity
import chess.behavior.move.SimpleMove
import chess.behavior.promote.StandardPromote
import chess.behavior.promote.PromoteBehavior
import chess.behavior.move.MoveBehavior

class Pawn extends Piece {
	moveBehavior = new MoveBehavior with SimpleMove
	promoteBehavior = new PromoteBehavior with StandardPromote
}