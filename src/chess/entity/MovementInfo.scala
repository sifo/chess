package chess.entity
import chess.history.History

class MovementInfo(
	var src : Position,
	var dst : Position,
	var piece : Piece,
	var chessBoard : ChessBoard,
	var history : History
) {

}