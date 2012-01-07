package chess.behavior.move
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.Color._
import chess.entity.Pawn

trait SimpleMove extends MoveBehavior {
  
	override def possibleMoves(mvtInfo: MovementInfo): Array[Array[Int]] = {
	  var output = Array.fill[Int](mvtInfo.chessBoard.dimension.height, mvtInfo.chessBoard.dimension.width)(0);
	  val action = mvtInfo.history.getLastAction()
	  
	  if ((mvtInfo.src.y + 1 < mvtInfo.chessBoard.dimension.width) && (mvtInfo.piece.color == White))
	    output(mvtInfo.src.x)(mvtInfo.src.y + 1) = 1
	    
	  if ((mvtInfo.src.y - 1 >= 0) && (mvtInfo.piece.color == Black))
	    output(mvtInfo.src.x)(mvtInfo.src.y - 1) = 1
	  
	  if ((mvtInfo.src.y == 1) && (mvtInfo.piece.color == White))
	    output(mvtInfo.src.x)(3) = 1
	  
	  if ((mvtInfo.src.y == 6) && (mvtInfo.piece.color == Black))
	    output(mvtInfo.src.x)(4) = 1
	  
	  if ((action.piece.isInstanceOf[Pawn]) && (mvtInfo.piece.color == White)) {
	    if ((action.src.y == 6) && (action.dst.y == 4)) {
	      if ((mvtInfo.src.y == 4) && ((mvtInfo.src.x + 1 == action.src.x) || (mvtInfo.src.x - 1 == action.src.x)))
	          output(action.src.x)(5) = 1
	    }
	  }
	  
	  if ((action.piece.isInstanceOf[Pawn]) && (mvtInfo.piece.color == White)) {
	    if ((action.src.y == 6) && (action.dst.y == 4)) {
	      if ((mvtInfo.src.y == 4) && ((mvtInfo.src.x + 1 == action.src.x) || (mvtInfo.src.x - 1 == action.src.x)))
	          output(action.src.x)(5) = 1
	    }
	  }
	  
	  output;
	}
	
	override def canMove(mvtInfo: MovementInfo): Boolean = {
		if (possibleMoves(mvtInfo)(mvtInfo.dst.x)(mvtInfo.dst.y) == 1) {
			return true
		}
		return super.canMove(mvtInfo)
	}
}