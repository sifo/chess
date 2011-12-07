package chess.entity
import chess.behavior.move.CastlingMove
import chess.behavior.move.AnySimpleMove
import chess.behavior.promote.NoWayPromote

class King extends Piece {
	var moveBehavior = new AnySimpleMove with CastlingMove {} 
	var promoteBehavior = new NoWayPromote {}
	var position
	var boardDimension
	var color
	
	
	override def canMove(init: Position): Boolean = {
		moveBehavior.canMove(init, getPosition, boardDimension)
	}

	override def canPromote = promoteBehavior.canPromote
	override def getMoveBehavior = moveBehavior
	override def getPromoteBehavior = promoteBehavior
	override def getPosition = position
	override def getColor = color
	
	override def setMoveBehavior(mb: MoveBehavior) = moveBehavior = mb
	override def setPromoteBehavior(pb: PromoteBehavior) = promoteBehavior = pb
	override def setPosition(pos: Position) = position = pos
	override def setBoardDimension(dim : Int) = boardDimension = dim
	override def setColor(c : Int) = color = c
}