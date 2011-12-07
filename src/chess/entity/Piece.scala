package chess.entity
import chess.behavior.promote.PromoteBehavior
import chess.behavior.move.MoveBehavior

abstract class Piece {
	private var moveBehavior   : MoveBehavior    = null
	private var promoteBehavior: PromoteBehavior = null
	private var position: Position = new Position(-1, -1)
	private var color : Int = -1	// la couleur et un INT, où le 0 == BLANC et le 1 == NOIR
	
	//Ajout pour pouvoir transmettre la dimension
	//aux canMove(). Nécessaire, sinon, risques de sortir du plateau.
	//À ajouter au constructeur
	private var boardDimension : Int = 0;

	def canMove(init: Position): Boolean = {
		moveBehavior.canMove(init, getPosition, boardDimension)
	}

	def canPromote = promoteBehavior.canPromote
	def getMoveBehavior = moveBehavior
	def getPromoteBehavior = promoteBehavior
	def getPosition = position
	def getColor = color

	def setMoveBehavior(mb: MoveBehavior) = moveBehavior = mb
	def setPromoteBehavior(pb: PromoteBehavior) = promoteBehavior = pb
	def setPosition(pos: Position) = position = pos
	def setBoardDimension(dim : Int) = boardDimension = dim
	def setColor(c : Int) = color = c
}