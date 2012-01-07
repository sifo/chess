package chess

object Main {
	def main(args: Array[String]) {
		var config = if(!args.isEmpty) args(0) else "res/standard-chess-config.xml"
		val chessModel = new ChessModel(config)
		val chessController = new ChessController(chessModel)
		chessController.displayViews()
		chessModel.start()
	}
}