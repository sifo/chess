package chess.ui
import chess.ChessListener
import chess.ChessView
import chess.ChessController
import chess.ui.events.MoveEvent
import chess.ui.events.TellCantMovePieceEvent
import chess.ui.events.PlacePieceOnBoardEvent
import chess.ui.events.DrawBoardEvent

class TextView(chessController: ChessController) extends ChessView(chessController){
	def display() {
		
	}
	def close() {
		
	}
	
	def drawBoard(event : MoveEvent) : Unit = {
		
	}
	
	def drawBoard(event : DrawBoardEvent) : Unit = {
		
	}
	
	def tellCantMovePiece(event : TellCantMovePieceEvent) : Unit = {
		
	}
	
	def placePieceOnTile(event : PlacePieceOnBoardEvent) : Unit = {
		
	}
}