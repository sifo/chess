package chess.entity
import chess.behavior.promote.PromoteBehavior
import chess.behavior.move.MoveBehavior

abstract class Piece {
	private var moveBehavior: MoveBehavior = null
	private var promoteBehavior: PromoteBehavior = null
	private var position: Position = new Position(-1, -1)

	def canMove(init: Position): Boolean = {
		moveBehavior.canMove(init, getPosition)
	}

	def canPromote = promoteBehavior.canPromote

	def getMoveBehavior = moveBehavior

	def getPromoteBehavior = promoteBehavior
	
	def getPosition = position

	def setMoveBehavior(mb: MoveBehavior) = moveBehavior = mb

	def setPromoteBehavior(pb: PromoteBehavior) = promoteBehavior = pb
	
	def setPosition(pos: Position) = position = pos
}