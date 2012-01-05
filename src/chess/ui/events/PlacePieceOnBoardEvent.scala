package chess.ui.events
import chess.entity.Piece
import chess.entity.Position

class PlacePieceOnBoardEvent(var actualPiece: Piece, var position: Position) extends Event {

}