package chess.behavior.promote

import chess.entity.Pawn 
import chess.entity.MovementInfo

trait StandardPromote extends PromoteBehavior {
  
	override def canPromote(movementInfo : MovementInfo): Boolean = {
	  movementInfo.piece.isInstanceOf[Pawn]
	}	
}