package chess
import chess.manager.BoardManager
import chess.manager.PlayerManager
import scala.collection.mutable.ListBuffer

class ChessModel(var config: String) extends Game {

	var boardManager = new BoardManager(this)
	var playerManager = new PlayerManager(this)
	val listeners = new ListBuffer[ChessListener]

	def addChessListener(chessListener : ChessListener) : Unit = {
		listeners += chessListener
	}
	
	def removeChessListener(chessListener : ChessListener) : Unit = {
		listeners -= chessListener
	}

	def save() {
		
	}
	
	def quit() {
		
	}
	
	def fireBoard() : Unit = {
		
	}
	
	def fireImpossibleMovement() : Unit = {
		
	}
	
	def fireCheckMate() : Unit = {
		
	}
	
	def firePat() : Unit = {
		
	}
	
	def fireChangeCurrentPlayer() : Unit = {
		
	}
	
	def start() {
		//fireConfigDialog()
	}
	
	def newGame() {
		start()
	}
	
	def play() {
		boardManager = new BoardManager(this)
		playerManager = new PlayerManager(this)
		fireBoard()
		//boardManager.putPieceOnBoard
	}
}