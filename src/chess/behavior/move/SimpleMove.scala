package chess.behavior.move
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.Color._
import chess.entity.Pawn
import chess.history.Action
import scala.xml.XML

trait SimpleMove extends MoveBehavior {
	var lastMvt: Action = _
	
	private def getDirection(configFile: String, posit_x: Int, posit_y: Int): String = {
		val config = XML.loadFile(configFile)
		for (val entry <- config \\ "board-pieces" \ "piece-setup") {
			val x = (entry \ "@x").text.toInt
			val y = (entry \ "@y").text.toInt
			if(x == posit_x && y == posit_y) {
				val playerNumber = (entry \ "@player").text.toInt
				for (val i <- config \\ "player-config" \ "player-direction") {
					if((i \ "@player").text.toInt == playerNumber)
					  return (i \ "@direction").text
				}
			}
		}
		"north"
	}
  
	override def possibleMoves(mvtInfo: MovementInfo): Array[Array[Int]] = {
	  var output = Array.fill[Int](mvtInfo.chessBoard.dimension.height, mvtInfo.chessBoard.dimension.width)(0);
	  val src = mvtInfo.src
	  val direction = getDirection("res/standard-chess-config.xml", src.x, src.y)
	  lastMvt = mvtInfo.history.getLastAction()
	  //lastMvt = new Action(null, null, null)
	  
	  if ((src.y + 1 < mvtInfo.chessBoard.dimension.width) && (direction == "north"))
	    output(src.x)(src.y + 1) = 1
	    
	  if ((src.y - 1 >= 0) && (direction == "south"))
	    output(src.x)(src.y - 1) = 1
	  
	  if ((src.y == 1) && (direction == "north"))
	    output(src.x)(3) = 1
	  
	  if ((src.y == 6) && (direction == "south"))
	    output(src.x)(4) = 1
	  
	  /*
	   ************ PRISE EN PASSANT
	   * */
	  
	  if(lastMvt != null) {
		  if ((lastMvt.piece.isInstanceOf[Pawn]) && (direction == "north")) {
		    if ((lastMvt.src.y == 6) && (lastMvt.dst.y == 4)) {
		      if ((src.y == 4) && ((src.x + 1 == lastMvt.src.x) || (src.x - 1 == lastMvt.src.x)))
		          output(lastMvt.src.x)(5) = 1
		    }
		  }
		  
		  if ((lastMvt.piece.isInstanceOf[Pawn]) && (direction == "south")) {
		    if ((lastMvt.src.y == 1) && (lastMvt.dst.y == 3)) {
		      if ((src.y == 3) && ((src.x + 1 == lastMvt.src.x) || (src.x - 1 == lastMvt.src.x)))
		          output(lastMvt.src.x)(2) = 1
		    }
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