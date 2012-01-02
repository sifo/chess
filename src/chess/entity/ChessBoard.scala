package chess.entity;

//Contents: Board interface
//			ChessBoard implementing Board interface
//			->Board is a Factory
//			Squares are an array in the Board

abstract class Board {
  /**
   * Adds a Piece to a position
   * Returns true if the Piece could be placed, false otherwise
   */
  def add(p: Piece, pos: Position): Boolean;

  /**
   * Removes a Piece from a position
   * Returns success
   */
  def remove(p: Position): Boolean;

  /**
   * Returns true if the move was successful, false otherwise
   */
  def move(src: Position, dst: Position): Boolean;

  /**
   * Returns the Piece at given Position
   */
  def getPiece(pos: Position): Piece;

  /**
   * Returns the winner, 0 if no one has won yet.
   */
  //Utile ? Si l'état de la partie est déterminé dans BoardManager,
  //cette méthode est inutile, puisque le manager peut accéder aux
  //pieces via la méthode getPiece(), et demander leurs mouvements
  //possibles.
  def doWeHaveAWinner(): Int;
}

object Board {

  def apply() = {
    new ChessBoard(new Dimension(8, 8))
  }
}

class ChessBoard(dimension: Dimension) extends Board {
  var squares = Array.ofDim[Piece](dimension.width, dimension.height);
  var winner: Int = 0;

  override def add(p: Piece, pos: Position): Boolean = {
    if (squares(pos.x)(pos.y) != null) {
      squares(pos.x).update(pos.y, p);
      true;
    } else {
      false;
    }
  }

  override def remove(pos: Position): Boolean = {
    squares(pos.x).update(pos.y, null);
    true;
  }

  override def move(src: Position, dst: Position) = {
    if (!squares(src.x)(src.y).isInstanceOf[Piece]) {
      false;
    } else {
    	val p = squares(src.x)(src.y)
    	val m = new MovementInfo(p.getPosition, dst, p, dimension, null)
    	p.canMove(m);
    }
  }

  override def getPiece(pos: Position) : Piece = {
    squares(pos.x)(pos.y)
  }
  
  override def doWeHaveAWinner() = {
    winner;
  }
  
  def isOnBoard(pos : Position) : Boolean = {
  	false
  }
}