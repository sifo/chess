package chess.manager
import chess.ChessModel
import scala.collection.mutable.ListBuffer
import chess.entity.Player

object PlayerManager {
	var MAX_PLAYER_NUMBER = 2
}

class PlayerManager(val chessModel: ChessModel) {
	var players = new ListBuffer[Player]
	var currentPlayerIndex = 0

	def add(p : Player) {
		if (players.size == PlayerManager.MAX_PLAYER_NUMBER) {
			return;
		}
		players += p;
	}

	def setNextPlayer() : Unit = {
		if (currentPlayerIndex == players.size - 1)
			currentPlayerIndex = 0
		else
			currentPlayerIndex += 1
		chessModel.fireChangeCurrentPlayer
	}
}