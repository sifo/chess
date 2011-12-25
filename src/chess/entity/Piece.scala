package chess.entity

import chess.behavior.promote.PromoteBehavior
import chess.behavior.move.MoveBehavior
import java.awt.Dimension
import chess.history.Action
import chess.entity.Color._

abstract class Piece {
	var moveBehavior   : MoveBehavior    = null
	var promoteBehavior: PromoteBehavior = null
	var position: Position = new Position(-1, -1)
	var boardDimension : Dimension = null
	var color : Color = null
	var mvtInfo: MovementInfo = null
	
	//Ajout pour pouvoir transmettre la dimension
	//aux canMove(). Nécessaire, sinon, risques de sortir du plateau.
	//À ajouter au constructeur

	def canMove(movementInfo: MovementInfo): Boolean = {
		moveBehavior.canMove(movementInfo)
	}
	
	def canPromote(movementInfo: MovementInfo): Boolean = {
	  promoteBehavior.canPromote(movementInfo)
	}
	
	def getMoveBehavior = moveBehavior
	def getPromoteBehavior = promoteBehavior
	def getBoardDimension = boardDimension
	def getPosition = position
	def getColor = color
	def getAction = mvtInfo
	

	def setMoveBehavior(mb: MoveBehavior) = moveBehavior = mb
	def setPromoteBehavior(pb: PromoteBehavior) = promoteBehavior = pb
	def setPosition(pos: Position) = position = pos
	def setBoardDimension(dim : Dimension) = boardDimension = dim
	def setColor(c : Color) = color = c
	def setMvtInfo(dest: Position) {
	  mvtInfo.src   = getPosition
	  mvtInfo.dst   = dest
	  mvtInfo.piece = this
	  mvtInfo.boardDim = getBoardDimension
	}
}