package chess.manager;
import chess.entity.ChessBoard
import chess.entity.Piece
import chess.entity.Position

class BoardManager {
	var board = new ChessBoard(8)
	
	def move(pos :Position, piece: Piece){
		if(piece.canMove(null)) {
			// change piece position
			piece.setPosition(new Position(pos.x, pos.y))
			
			// remove piece on new Position, if any
			// board.removePieceAt(Position)
			
			// change position on board
			// board.movePieceAt(Piece, Position)
		}
	}
}