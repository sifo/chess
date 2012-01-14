package chess
import chess.manager.BoardManager
import chess.manager.PlayerManager
import scala.collection.mutable.ListBuffer
import chess.ui.events.DrawBoardEvent
import chess.ui.events.CantPutYourselfInCheckEvent
import chess.ui.events.CantMovePieceEvent
import chess.ui.events.CheckEvent
import chess.ui.events.CheckMateEvent
import chess.ui.events.PatEvent
import chess.ui.events.NotPlayerTurnEvent
import chess.ui.events.NoPieceAtPositionEvent

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
		fireClose()
	}
	
	def fireBoard() : Unit = {
		for(l <- listeners)
			l.drawBoard(new DrawBoardEvent(boardManager.board))
	}
	
	def fireClose() : Unit = {
		for(l <- listeners)
			l.close()
	}
	
	def fireImpossibleMovement() : Unit = {
		for(l <- listeners)
			l.cantMovePiece(new CantMovePieceEvent())
	}
	
	def fireNotPlayerTurn() : Unit = {
		for(l <- listeners)
			l.notPlayerTurn(new NotPlayerTurnEvent())
	}
	
	def firePlayerCantPutHimselfInCheck() : Unit = {
		for(l <- listeners)
			l.cantPutYourselfInCheck(new CantPutYourselfInCheckEvent())
	}
	
	def fireCheck() : Unit = {
		for(l <- listeners)
			l.check(new CheckEvent())
	}
	
	def fireCheckMate() : Unit = {
		for(l <- listeners)
			l.checkmate(new CheckMateEvent())
	}
	
	def firePat() : Unit = {
		for(l <- listeners)
			l.pat(new PatEvent())
	}
	
	def fireNoPieceAtPosition() : Unit = {
		for(l <- listeners)
			l.noPieceAtPosition(new NoPieceAtPositionEvent())
	}
	
	def fireChangeCurrentPlayer() : Unit = {
		
	}
	
	def start() {
		fireBoard()
	}
	
	def newGame() {
		boardManager = new BoardManager(this)
		playerManager = new PlayerManager(this)
		start()
	}
}