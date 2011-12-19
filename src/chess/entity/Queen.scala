package chess.entity
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.AnySimpleMove

class Queen extends Piece {
	override var moveBehavior = new AnySimpleMove {} 
	override var promoteBehavior = new NoWayPromote {}
}