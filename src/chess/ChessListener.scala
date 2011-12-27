package chess
import chess.ui.events.MoveEvent
import chess.ui.events.TellCantMovePieceEvent
import chess.ui.events.PlacePieceOnBoardEvent
import chess.ui.events.DrawBoardEvent

trait ChessListener {
	
	def drawBoard(event : MoveEvent) : Unit
	
	def drawBoard(event : DrawBoardEvent) : Unit
	
	def tellCantMovePiece(event : TellCantMovePieceEvent) : Unit
	
	def placePieceOnTile(event : PlacePieceOnBoardEvent) : Unit
}