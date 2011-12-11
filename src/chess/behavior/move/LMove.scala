package chess.behavior.move;
import chess.entity.Position
import chess.history.Action

trait LMove extends MoveBehavior {

  abstract override def possibleMoves(a: Action): Array[Array[Int]] = {
    var output = Array.fill[Int](a.dim.getHeight().toInt, a.dim.getWidth().toInt)(0);

    /*
     * Fills the array with 1 where the LMove can be performed.
     * Is the piece would move out of the board, the exception is caught to do nothing
     */
    if (a.src.x + 1 < a.dim.getHeight().toInt
      && a.src.y + 2 < a.dim.getWidth().toInt)
      output(a.src.x + 1)(a.src.y + 2) = 1;
    if (a.src.x + 1 < a.dim.getHeight().toInt
      && a.src.y - 2 >= 0)
      output(a.src.x + 1)(a.src.y - 2) = 1;
    if (a.src.x - 1 >= 0
      && a.src.y + 2 < a.dim.getWidth().toInt)
      output(a.src.x - 1)(a.src.y + 2) = 1;
    if (a.src.x - 1 >= 0
      && a.src.y - 2 >= 0)
      output(a.src.x - 1)(a.src.y - 2) = 1;
    if (a.src.x + 2 < a.dim.getHeight().toInt
      && a.src.y + 1 < a.dim.getWidth().toInt)
      output(a.src.x + 2)(a.src.y + 1) = 1;
    if (a.src.x - 2 >= 0
      && a.src.y + 1 < a.dim.getWidth().toInt)
      output(a.src.x - 2)(a.src.y + 1) = 1;
    if (a.src.x + 2 < a.dim.getHeight().toInt
      && a.src.y - 1 >= 0)
      output(a.src.x + 2)(a.src.y - 1) = 1;
    if (a.src.x - 2 >= 0
      && a.src.y - 1 >= 0)
      output(a.src.x - 2)(a.src.y - 1) = 1;

    output;
  }

  abstract override def canMove(a: Action): Boolean = {
    possibleMoves(a)(a.dst.x)(a.dst.y) == 1;
  }
}