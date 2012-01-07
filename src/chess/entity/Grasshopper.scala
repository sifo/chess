package chess.entity
import chess.behavior.promote.PromoteBehavior
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.MoveBehavior
import chess.behavior.move.HorizontalLeapMove
import chess.behavior.move.VerticalLeapMove
import chess.behavior.move.DiagonalLeapMove

class Grasshopper extends Piece {
	moveBehavior = new MoveBehavior 
			with HorizontalLeapMove with VerticalLeapMove with DiagonalLeapMove
	promoteBehavior = new PromoteBehavior with NoWayPromote
}