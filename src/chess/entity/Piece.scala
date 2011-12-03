package chess.entity
import chess.behavior.promote.PromoteBehavior
import chess.behavior.move.MoveBehavior

abstract class Piece {
	private var moveBehavior: MoveBehavior = null
	private var promoteBehavior: PromoteBehavior = null

	def performMove = moveBehavior.move

	def performPromote = promoteBehavior.promote

	def getMoveBehavior = moveBehavior

	def getPromoteBehavior = promoteBehavior

	def setMoveBehavior(mb: MoveBehavior) = moveBehavior = mb

	def setPromoteBehavior(pb: PromoteBehavior) = promoteBehavior = pb
}