package chess

object Main {
	def main(args: Array[String]) {
		var config = "res/standard-chess-config.xml"
		if(!args.isEmpty) {
			config = args(0)
		}
		val chessModel = new ChessModel(config);
		val chessController = new ChessController(chessModel);
		chessController.displayViews();
		chessModel.start();
	}
}