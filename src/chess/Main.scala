package chess

object Main {
	def main(args: Array[String]) {
		val chessModel = new ChessModel();
		val chessController = new ChessController(chessModel);
		chessController.displayViews();
		chessModel.start();
	}
}