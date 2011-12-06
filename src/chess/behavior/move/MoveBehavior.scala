package chess.behavior.move;
import chess.entity.Position

trait MoveBehavior {
  //Dimension permet de passer la dimension du plateau, inconnue autrement
  //puisqu'on utilise un trait.
  def canMove(init: Position, dest: Position, dimension : Int): Boolean = {
    false
  }

  def possibleMoves(init: Position, dimension: Int): Array[Array[Int]];

  def threateningMoves(init: Position, dimension: Int): Array[Array[Int]] =
    possibleMoves(init, dimension);
}