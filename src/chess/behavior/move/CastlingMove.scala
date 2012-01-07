package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.Color._
import chess.history.ChessHistory

trait CastlingMove extends MoveBehavior {
		
	override def canMove(mvtInfo: MovementInfo): Boolean = {
	  
	  if((mvtInfo.piece.color == White) && mvtInfo.history.getKingWhiteStat) {
	    if ((mvtInfo.src.x == 4) && (mvtInfo.src.y == 0)) {
	      // Petit Roque
	      if((mvtInfo.dst.x == 6) && (mvtInfo.dst.y == 0) && mvtInfo.history.getrookRWhiteStat)
	        true
	      // Grand Roque
	      else if ((mvtInfo.dst.x == 2) && (mvtInfo.dst.y == 0) && mvtInfo.history.getrookLWhiteStat) {
	        true
	      }
	    }
	  }
	  
	  else if((mvtInfo.piece.color == Black) && mvtInfo.history.getKingBlackStat) {
	    if ((mvtInfo.src.x == 4) && (mvtInfo.src.y == 7)) {
	      // Petit Roque
	      if((mvtInfo.dst.x == 6) && (mvtInfo.dst.y == 7) && mvtInfo.history.getrookRBlackStat)
	        true
	      // Grand Roque
	      else if ((mvtInfo.dst.x == 2) && (mvtInfo.dst.y == 7) && mvtInfo.history.getrookLBlackStat)
	        true
	    }
	  }
	  
	  false
	}
}