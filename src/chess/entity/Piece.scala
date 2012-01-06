package chess.entity

import chess.behavior.promote.PromoteBehavior
import chess.behavior.move.MoveBehavior
import chess.history.Action
import chess.entity.Color._

abstract class Piece {
	var moveBehavior   : MoveBehavior    = null
	var promoteBehavior: PromoteBehavior = null
	var position: Position = new Position(-1, -1)
	var board : Dimension = null
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
}