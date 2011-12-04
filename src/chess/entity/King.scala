package chess.entity
import chess.behavior.move.CastlingMove
import chess.behavior.move.AnySimpleMove
import chess.behavior.promote.NoWayPromote

class King extends Piece with NoWayPromote with CastlingMove with AnySimpleMove{

}