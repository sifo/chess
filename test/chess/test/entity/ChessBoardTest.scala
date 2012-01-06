package chess.test
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.entity.ChessBoard
import chess.entity.Dimension

@RunWith(classOf[JUnitRunner])
class ChessBoardTest extends Spec with BeforeAndAfter {
	
	var chessBoard: ChessBoard = _

	before {
		chessBoard = new ChessBoard(new Dimension(8, 8))
	}
	
	describe("ChessBoard") {
		
		it("should accept 1") {
			assert(true)
		}
	}
}