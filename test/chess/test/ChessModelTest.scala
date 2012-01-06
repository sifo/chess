package chess.test
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.ChessController
import chess.ChessModel
import chess.ChessListener
import chess.ui.TextView

@RunWith(classOf[JUnitRunner])
class ChessModelTest extends Spec with BeforeAndAfter {
	
	var chessModel: ChessModel = _
	var chessController: ChessController = _
	var chessListener: ChessListener = _
	
	before {
		chessModel = new ChessModel()
		chessController = new ChessController(chessModel)
		chessListener = new TextView(chessController)
	}
	
	describe("ChessModel") {
		
		it("should be able to add listener to model") {
			chessModel.addChessListener(chessListener)
			assert(chessModel.listeners.contains(chessListener))
		}
		
		it("should be able to remove listener to model") {
			chessModel.removeChessListener(chessListener)
			assert(!chessModel.listeners.contains(chessListener))
		}
	}
}