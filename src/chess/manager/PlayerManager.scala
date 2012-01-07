package chess.manager
import chess.ChessModel
import scala.collection.mutable.ListBuffer
import chess.entity.Player
import chess.entity.Color._
import scala.xml.XML
import chess.entity.Piece

object PlayerManager {
	var MAX_PLAYER_NUMBER = 6

	def buildPlayer(playerNumber: Int): Player = {
		val color = playerNumberToColor(playerNumber)
		val name = playerNumber.toString
		return new Player(name, color)
	}

	def playerNumberToColor(playerNumber: Int): Color = {
		var color: Color = null
		playerNumber match {
			case 0 => color = White
			case 1 => color = Black
		}
		return color
	}

	def playerColorToNumber(color: Color): Int = {
		var index = 0
		color match {
			case White => index = 0
			case Black => index = 1
		}
		return index
	}

}

class PlayerManager(val chessModel: ChessModel) {
	var players = new ListBuffer[Player]
	var currentPlayerIndex = 0
	loadConfig(chessModel.config)

	def loadConfig(configFile: String) {
		val config = XML.loadFile(configFile)
		for (val entry <- config \\ "player-config" \ "player-direction") {
			val playerNumber = (entry \ "@player").text.toInt
			add(PlayerManager.buildPlayer(playerNumber))
		}
	}

	def add(p: Player) {
		if (players.size == PlayerManager.MAX_PLAYER_NUMBER) {
			return ;
		}
		players += p;
	}

	def setNextPlayer(): Unit = {
		if (currentPlayerIndex == players.size - 1)
			currentPlayerIndex = 0
		else
			currentPlayerIndex += 1
		chessModel.fireChangeCurrentPlayer
	}

	def isPlayerTurn(piece: Piece): Boolean = {
		var index = PlayerManager.playerColorToNumber(piece.color)
		if (currentPlayerIndex == index)
			return true
		else return false
	}
}