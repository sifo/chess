package chess
import chess.ui.TextView
import chess.ui.events.DrawBoardEvent
import chess.ui.events.MoveEvent

class ChessController(var chessModel : ChessModel) {
	var view = new TextView(this)
	addListenersToModel()
	
	def addListenersToModel()= chessModel.addChessListener(view)
	
	def closeViews() = view.close

	def notifyMovement(moveEvent : MoveEvent) {
		val src = moveEvent.src
		if (!chessModel.boardManager.board.dimension.isInBounds(src)) {
			chessModel.fireImpossibleMovement()
			return
		}
		val piece = chessModel.boardManager.board.squares(src.x)(src.y)
		if (piece == null) {
			chessModel.fireNoPieceAtPosition()
			return
		}
		chessModel.boardManager.move(moveEvent.dst, piece)
	}
	
	def notifyQuitGame() = {
		chessModel.quit()
	}
	
	def notifyNewGame() = chessModel.newGame
}