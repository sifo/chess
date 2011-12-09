package chess.behavior.move;
import chess.entity.Position
import chess.history.Action

trait MoveBehavior {
  /**
   * Returns whether the piece can move to the given position from its
   * initial position.
   */
  //Dimension permet de passer la dimension du plateau, inconnue autrement
  //puisqu'on utilise un trait.
  def canMove(action: Action): Boolean = {
    false
  }

  /**
   * Returns an array containing 0s everywhere, and 1s where the piece can
   * move, starting from the given position, on a board of given dimension
   */
  def possibleMoves(action: Action): Array[Array[Int]];

  /**
   * Returns an array of threatening moves. For most of the pieces in a chess
   * game, it is the same as possibleMoves(), except for the Pawn.
   */
  def threateningMoves(action: Action): Array[Array[Int]] =
    possibleMoves(action);
}