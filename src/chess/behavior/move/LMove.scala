package chess.behavior.move;
import chess.entity.Position

trait LMove extends MoveBehavior {

  abstract override def possibleMoves(init: Position, dimension: Int): Array[Array[Int]] = {
    var output = Array.fill[Int](dimension, dimension)(1);

    /*
     * Fills the array with 1 where the LMove can be performed.
     * Is the piece would move out of the board, the exception is caught to do nothing
     */
    if (init.x + 1 < dimension && init.y + 2 < dimension)
      output(init.x + 1)(init.y + 2) = 1;
    if (init.x + 1 < dimension && init.y - 2 >= 0)
      output(init.x + 1)(init.y - 2) = 1;
    if (init.x - 1 >= 0 && init.y + 2 < dimension)
      output(init.x - 1)(init.y + 2) = 1;
    if (init.x - 1 >= 0 && init.y - 2 >= 0)
      output(init.x - 1)(init.y - 2) = 1;
    if (init.x + 2 < dimension && init.y + 1 < dimension)
      output(init.x + 2)(init.y + 1) = 1;
    if (init.x - 2 >= 0 && init.y + 1 < dimension)
      output(init.x - 2)(init.y + 1) = 1;
    if (init.x + 2 < dimension && init.y - 1 >= 0)
      output(init.x + 2)(init.y - 1) = 1;
    if (init.x - 2 >= 0 && init.y - 1 >= 0)
      output(init.x - 2)(init.y - 1) = 1;

    output;
  }

  abstract override def canMove(pos: Position, dest: Position, dimension: Int): Boolean = {
    possibleMoves(pos, dimension)(dest.x)(dest.y) == 1;
  }
}