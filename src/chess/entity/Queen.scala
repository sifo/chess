package chess.entity
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.AnySimpleMove
import chess.behavior.move.MoveBehavior
import chess.behavior.promote.PromoteBehavior
import chess.behavior.move.HorizontalMove
import chess.behavior.move.VerticalMove
import chess.behavior.move.DiagonalMove

class Queen extends Piece {
  moveBehavior = new MoveBehavior with HorizontalMove with VerticalMove with DiagonalMove
  promoteBehavior = new PromoteBehavior with NoWayPromote

  override def name() = "Queen";
}