package chess.behavior.move;

trait LMove extends MoveBehavior {
  override def possibleMoves(x: Int, y: Int, dimension: Int): Array[Array[Int]] = {
    var output = Array.fill[Int](dimension, dimension)(1);

    /*
     * Fills the array with 1 where the LMove can be performed.
     * Is the piece would move out of the board, the exception is caught to do nothing
     */
    try {
      output(x + 1)(y + 2) = 1;
      output(x + 1)(y - 2) = 1;
      output(x - 1)(y + 2) = 1;
      output(x - 1)(y - 2) = 1;
      output(x + 2)(y + 1) = 1;
      output(x - 2)(y + 1) = 1;
      output(x + 2)(y - 1) = 1;
      output(x - 2)(y - 1) = 1;
    } catch {
      case ex: ArrayIndexOutOfBoundsException =>
    }

    output;
  }
}