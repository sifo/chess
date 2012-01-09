package chess
import chess.ui.TextView
import chess.ui.events.DrawBoardEvent

class ChessController(var chessModel : ChessModel) {
	var view = new TextView(this)
	view.drawBoard(new DrawBoardEvent(chessModel.boardManager.board.dimension));
	
	def addListenersToModel()= chessModel.addChessListener(view)
	
	def displayViews() = view.display

	def closeViews() = view.close
	
	def notifyQuitGame() = System.exit(0)
	
	def notifyNewGame() = chessModel.newGame
}