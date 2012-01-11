package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo

abstract class MoveBehavior {
  /**
   * Returns whether the piece can move to the given position from its
   * initial position.
   */
  //méthode abstraite, ce trait ne devrait jamais être utilisé directement
  def canMove(movementInformation: MovementInfo): Boolean = false

  /**
   * Returns an array containing 0s everywhere, and 1s where the piece can
   * move, starting from the given position, on a board of given dimension
   */
  def possibleMoves(movementInformation: MovementInfo): Array[Array[Int]] = null

  /**
   * Returns an array of threatening moves. For most of the pieces in a chess
   * game, it is the same as possibleMoves(), except for the Pawn.
   */
  def threateningMoves(movementInformation: MovementInfo): Array[Array[Int]] =
    possibleMoves(movementInformation)
    
  def respectPrecondition(mvtInfo: MovementInfo): Boolean = {
	if (!mvtInfo.chessBoard.dimension.isInBounds(mvtInfo.src)
	|| !mvtInfo.chessBoard.dimension.isInBounds(mvtInfo.dst)
	|| mvtInfo.src.equals(mvtInfo.dst))
		return false
	val pieceDst = mvtInfo.chessBoard.squares(mvtInfo.dst.x)(mvtInfo.dst.y)
	if (pieceDst != null && mvtInfo.piece.color == pieceDst.color) {
		return false
	}
	return true
  }
}