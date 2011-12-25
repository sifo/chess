package chess.entity
import chess.history.History

class MovementInfo(
	var src : Position,
	var dst : Position,
	var piece : Piece,
	var boardDim : Dimension,
	var history : History
) {

}