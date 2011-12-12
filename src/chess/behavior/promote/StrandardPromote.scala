package chess.behavior.promote

import chess.history.Action
import chess.entity.Pawn 

trait StandardPromote extends PromoteBehavior {
  
	override def canPromote(action: Action): Boolean = {
	  if(action.piece.isInstanceOf[Pawn]) true else false
	}
}