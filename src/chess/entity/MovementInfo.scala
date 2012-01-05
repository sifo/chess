package chess.entity
import chess.history.ChessHistory

class MovementInfo(
	var src : Position,
	var dst : Position,
	var piece : Piece,
	var chessBoard : ChessBoard,
	var history : ChessHistory
) {

}