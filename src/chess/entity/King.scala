package chess.entity

import chess.behavior.move.CastlingMove
import chess.behavior.move.AnySimpleMove
import chess.behavior.promote.NoWayPromote
import chess.behavior.move.MoveBehavior
import chess.behavior.promote.PromoteBehavior

class King extends Piece {
  moveBehavior = new MoveBehavior with AnySimpleMove with CastlingMove
  promoteBehavior = new PromoteBehavior with NoWayPromote

  override def name() = "King";
}