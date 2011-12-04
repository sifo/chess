package chess.entity
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.AnySimpleMove

class Queen extends Piece {
	var moveBehavior = new AnySimpleMove {} 
	var promoteBehavior = new NoWayPromote {}
}