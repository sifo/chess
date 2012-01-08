package chess.entity

import chess.behavior.promote.PromoteBehavior
import chess.behavior.move.MoveBehavior
import chess.history.Action
import chess.entity.Color._

abstract class Piece {
	var moveBehavior: MoveBehavior = null
	var promoteBehavior: PromoteBehavior = null
	var position: Position = new Position(-1, -1)
	var color: Color = null

	def canMove(movementInfo: MovementInfo): Boolean = {
		moveBehavior.canMove(movementInfo)
	}

	def canPromote(movementInfo: MovementInfo): Boolean = {
		promoteBehavior.canPromote(movementInfo)
	}

	def name(): String;

	def significantLetter(): String = name().substring(0, 1);
}