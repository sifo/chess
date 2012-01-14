package chess.test.entity
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.history.ChessHistory
import chess.history.Action
import chess.entity.Pawn
import chess.entity.Position

@RunWith(classOf[JUnitRunner])
class ChessHistoryTest extends Spec with BeforeAndAfter {
	
	var chessHistory: ChessHistory = _
	var action: Action = _

	before {
		chessHistory = new ChessHistory()
	}
	
	describe("ChessHistory") {
		
		it("should add action") {
			val src = new Position(2, 2)
			val dst = new Position(2, 1)
			val piece = new Pawn
			val action = new Action(src, dst, piece)
			chessHistory.addAction(action)
			assert(chessHistory.listeActions.contains(action))
		}
	}
}