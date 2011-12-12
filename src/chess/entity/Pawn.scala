package chess.entity

import chess.behavior.move.SimpleMove
import chess.behavior.promote.StandardPromote


class Pawn extends Piece {
	var moveBehavior = new SimpleMove {}
	var promoteBehavior = new StandardPromote {}
}