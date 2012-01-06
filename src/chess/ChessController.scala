package chess
import chess.ui.TextView

class ChessController(var chessModel : ChessModel) {
	val view = new TextView(this)
	
	def addListenersToModel()= chessModel.addChessListener(view)
	
	def displayViews() = view.display

	def closeViews() = view.close
	
	def notifyQuitGame() = System.exit(0)
	
	def notifyNewGame() = chessModel.newGame
}