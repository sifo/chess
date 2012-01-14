package chess
import chess.ui.events.MoveEvent
import chess.ui.events.PlacePieceOnBoardEvent
import chess.ui.events.DrawBoardEvent
import chess.ui.events.CantPutYourselfInCheckEvent
import chess.ui.events.CantMovePieceEvent
import chess.ui.events.CheckEvent
import chess.ui.events.CheckMateEvent
import chess.ui.events.PatEvent
import chess.ui.events.NotPlayerTurnEvent
import chess.ui.events.NoPieceAtPositionEvent

trait ChessListener {
	
	def drawBoard(event : DrawBoardEvent) : Unit
	
	def cantMovePiece(event : CantMovePieceEvent) : Unit
	
	def notPlayerTurn(event : NotPlayerTurnEvent) : Unit
	
	def noPieceAtPosition(event : NoPieceAtPositionEvent) : Unit
	
	def cantPutYourselfInCheck(event : CantPutYourselfInCheckEvent) : Unit
	
	def check(event : CheckEvent) : Unit
	
	def checkmate(event : CheckMateEvent) : Unit
	
	def pat(event : PatEvent) : Unit
	
	def close() : Unit
}