package chess.behavior.move;
import chess.entity.Position
import chess.history.Action
import chess.entity.MovementInfo

trait DiagonalMove extends MoveBehavior {

  override def possibleMoves(mvtInfo: MovementInfo): Array[Array[Int]] = {
    var output = Array.fill[Int](mvtInfo.chessBoard.dimension.height, mvtInfo.chessBoard.dimension.width)(0);

    /*
     * Fills the array with 1 where the DiagonalMove can be performed.
     * Is the piece would move out of the board, the exception is 
     * caught to do nothing
     */
    if (mvtInfo.src.x + 1 < mvtInfo.chessBoard.dimension.height
      && mvtInfo.src.y + 1 < mvtInfo.chessBoard.dimension.width)
      output(mvtInfo.src.x + 1)(mvtInfo.src.y + 1) = 1;
    if (mvtInfo.src.x + 1 < mvtInfo.chessBoard.dimension.height
      && mvtInfo.src.y - 1 >= 0)
      output(mvtInfo.src.x + 1)(mvtInfo.src.y - 1) = 1;
    if (mvtInfo.src.x - 1 >= 0
      && mvtInfo.src.y + 1 < mvtInfo.chessBoard.dimension.width)
      output(mvtInfo.src.x - 1)(mvtInfo.src.y + 1) = 1;
    if (mvtInfo.src.x - 1 >= 0
      && mvtInfo.src.y - 1 >= 0)
      output(mvtInfo.src.x - 1)(mvtInfo.src.y - 1) = 1;

    output;
  }
	override def canMove(mvtInfo: MovementInfo): Boolean = {
		if(possibleMoves(mvtInfo)(mvtInfo.dst.x)(mvtInfo.dst.y) == 1)
			return true
		return super.canMove(mvtInfo)
	}
}