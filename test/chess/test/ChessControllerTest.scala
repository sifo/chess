package chess.test
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.ChessController
import chess.ChessModel

@RunWith(classOf[JUnitRunner])
class ChessControllerTest extends Spec with BeforeAndAfter {
	
	var controller: ChessController = _
	
	before {
		controller = new ChessController(new ChessModel)
	}
	
	describe("ChessController") {
		
		it("should add listeners to model") {
			controller.addListenersToModel
			assert(controller.chessModel.listeners(0) == controller.view)
		}
	}
}