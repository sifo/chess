package chess.test
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.history.ChessHistory

@RunWith(classOf[JUnitRunner])
class ChessHistoryTest extends Spec with BeforeAndAfter {
	
	var chessHistory: ChessHistory = _

	before {
		chessHistory = new ChessHistory()
	}
	
	describe("ChessHistory") {
		
		it("should ") {
			assert(true)
		}
	}
}