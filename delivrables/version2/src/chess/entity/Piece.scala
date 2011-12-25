package chess.entity

import chess.behavior.promote.PromoteBehavior
import chess.behavior.move.MoveBehavior
import java.awt.Dimension
import chess.history.Action


abstract class Piece {
	private var moveBehavior   : MoveBehavior    = null
	private var promoteBehavior: PromoteBehavior = null
	private var position: Position = new Position(-1, -1)
	private var boardDimension : Dimension = null
	private var color: Color = new Color()
	private var action: Action = null
	
	//Ajout pour pouvoir transmettre la dimension
	//aux canMove(). Nécessaire, sinon, risques de sortir du plateau.
	//À ajouter au constructeur

	def canMove(dest: Position): Boolean = {
	  moveBehavior.canMove(getAction)
	}

	def canPromote = promoteBehavior.canPromote(getAction)
	
	def getMoveBehavior = moveBehavior
	def getPromoteBehavior = promoteBehavior
	def getBoardDimension = boardDimension
	def getPosition = position
	def getColor = color
	def getAction = action
	

	def setMoveBehavior(mb: MoveBehavior) = moveBehavior = mb
	def setPromoteBehavior(pb: PromoteBehavior) = promoteBehavior = pb
	def setPosition(pos: Position) = position = pos
	def setBoardDimension(dim : Dimension) = boardDimension = dim
	def setColor(c : Color) = color = c
	def setAction(dest: Position) {
	  action.src   = getPosition
	  action.dst   = dest
	  action.piece = this
	  action.dim   = getBoardDimension
	}
}