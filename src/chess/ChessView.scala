package chess

abstract class ChessView(val chessController: ChessController) extends ChessListener {

	def display() : Unit
	def close() : Unit
}