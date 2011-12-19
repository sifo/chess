package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo

trait LMove extends MoveBehavior {

  abstract override def possibleMoves(mvtInfo: MovementInfo): Array[Array[Int]] = {
    var output = Array.fill[Int](mvtInfo.boardDim.getHeight().toInt, mvtInfo.boardDim.getWidth().toInt)(0);

    /*
     * Fills the array with 1 where the LMove can be performed.
     * Is the piece would move out of the board, the exception is caught to do nothing
     */
    if (mvtInfo.src.x + 1 < mvtInfo.boardDim.getHeight().toInt
      && mvtInfo.src.y + 2 < mvtInfo.boardDim.getWidth().toInt)
      output(mvtInfo.src.x + 1)(mvtInfo.src.y + 2) = 1;
    if (mvtInfo.src.x + 1 < mvtInfo.boardDim.getHeight().toInt
      && mvtInfo.src.y - 2 >= 0)
      output(mvtInfo.src.x + 1)(mvtInfo.src.y - 2) = 1;
    if (mvtInfo.src.x - 1 >= 0
      && mvtInfo.src.y + 2 < mvtInfo.boardDim.getWidth().toInt)
      output(mvtInfo.src.x - 1)(mvtInfo.src.y + 2) = 1;
    if (mvtInfo.src.x - 1 >= 0
      && mvtInfo.src.y - 2 >= 0)
      output(mvtInfo.src.x - 1)(mvtInfo.src.y - 2) = 1;
    if (mvtInfo.src.x + 2 < mvtInfo.boardDim.getHeight().toInt
      && mvtInfo.src.y + 1 < mvtInfo.boardDim.getWidth().toInt)
      output(mvtInfo.src.x + 2)(mvtInfo.src.y + 1) = 1;
    if (mvtInfo.src.x - 2 >= 0
      && mvtInfo.src.y + 1 < mvtInfo.boardDim.getWidth().toInt)
      output(mvtInfo.src.x - 2)(mvtInfo.src.y + 1) = 1;
    if (mvtInfo.src.x + 2 < mvtInfo.boardDim.getHeight().toInt
      && mvtInfo.src.y - 1 >= 0)
      output(mvtInfo.src.x + 2)(mvtInfo.src.y - 1) = 1;
    if (mvtInfo.src.x - 2 >= 0
      && mvtInfo.src.y - 1 >= 0)
      output(mvtInfo.src.x - 2)(mvtInfo.src.y - 1) = 1;

    output;
  }

  abstract override def canMove(mvtInfo: MovementInfo): Boolean = {
    possibleMoves(mvtInfo)(mvtInfo.dst.x)(mvtInfo.dst.y) == 1;
  }
}