package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.Color._
import chess.history.ChessHistory

trait CastlingMove extends MoveBehavior {
		
	override def canMove(mvtInfo: MovementInfo): Boolean = {
	  val src    = mvtInfo.src
	  val dst    = mvtInfo.dst
	  val color  = mvtInfo.piece.color
	  
	  if((color == White) && mvtInfo.history.getKingWhiteStat) {
	    if ((src.x == 4) && (src.y == 0)) {
	      // Petit Roque
	      if((dst.x == 6) && (dst.y == 0) && mvtInfo.history.getrookRWhiteStat)
	        return true
	      // Grand Roque
	      else if ((dst.x == 2) && (dst.y == 0) && mvtInfo.history.getrookLWhiteStat)
	        return true
	    }
	  }
	  
	  else if((color == Black) && mvtInfo.history.getKingBlackStat) {
	    if ((src.x == 4) && (src.y == 7)) {
	      // Petit Roque
	      if((dst.x == 6) && (dst.y == 7) && mvtInfo.history.getrookRBlackStat)
	        return true
	      // Grand Roque
	      else if ((dst.x == 2) && (dst.y == 7) && mvtInfo.history.getrookLBlackStat)
	        return true
	    }
	  }
	  
	  return super.canMove(mvtInfo)
	}
}