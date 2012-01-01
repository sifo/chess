package chess.manager
import chess.entity.ChessBoard
import chess.entity.Piece
import chess.entity.Position
import chess.ChessModel
import chess.entity.MovementInfo
import chess.history.ChessHistory
import chess.entity.Dimension
import chess.history.Action

class BoardManager(val chessModel : ChessModel) {
	var board = new ChessBoard(8)
	var history = new ChessHistory
	
	def move(pos :Position, piece: Piece){
		var mvtInfo = new MovementInfo(piece.position, pos, piece, new Dimension(8, 8), history)
		if(piece.canMove(mvtInfo)) {
			var action = new Action(piece.position, pos, piece)
			history.addAction(action)
			board.squares(pos.x)(pos.y) = piece
			board.squares(piece.position.x)(piece.position.y) = null
			piece.setPosition(new Position(pos.x, pos.y))
		}
	}
}