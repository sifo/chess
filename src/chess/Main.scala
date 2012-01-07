package chess

object Main {
	
	val DEFAULT_CONFIG = "res/standard-chess-config.xml"
		
	def main(args: Array[String]) {
		var config = if(!args.isEmpty) args(0) else DEFAULT_CONFIG
		val chessModel = new ChessModel(config)
		val chessController = new ChessController(chessModel)
		chessController.displayViews()
		chessModel.start()
	}
}