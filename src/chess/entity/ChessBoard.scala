package chess.entity;

//Contents: Board interface
//			ChessBoard implementing Board interface
//			->Board is a Factory
//			Squares are an array in the Board

abstract class Board {
  /**
   * Returns true if the move was successful, false otherwise
   */
  def move(xSrc: Int, ySrc: Int, xDst: Int, yDst: Int): Boolean;

  /**
   * Returns the winner, 0 if no one has won yet.
   */
  def doWeHaveAWinner(): Int;
}

object Board {

  def apply() = {
    new ChessBoard(8);
  }
}

class ChessBoard(dimension: Int) extends Board {
  var squares = Array.ofDim[Int](dimension, dimension);
  var pieces = Array.ofDim[Piece](dimension, dimension);
  var winner: Int = 0;

  override def move(xSrc: Int, ySrc: Int, xDst: Int, yDst: Int) = {
    true;
  }

  override def doWeHaveAWinner() = {
    winner;
  }

}