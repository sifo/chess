package chess.behavior.move;
import chess.entity.Position

trait MoveBehavior {
  /**
   * Returns whether the piece can move to the given position from its
   * initial position.
   */
  //Dimension permet de passer la dimension du plateau, inconnue autrement
  //puisqu'on utilise un trait.
  def canMove(init: Position, dest: Position, dimension : Int): Boolean = {
    false
  }

  /**
   * Returns an array containing 0s everywhere, and 1s where the piece can
   * move, starting from the given position, on a board of given dimension
   */
  def possibleMoves(init: Position, dimension: Int): Array[Array[Int]];

  /**
   * Returns an array of threatening moves. For most of the pieces in a chess
   * game, it is the same as possibleMoves(), except for the Pawn.
   */
  def threateningMoves(init: Position, dimension: Int): Array[Array[Int]] =
    possibleMoves(init, dimension);
}