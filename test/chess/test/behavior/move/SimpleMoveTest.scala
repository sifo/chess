package chess.test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.BeforeAndAfter
import org.scalatest.Spec
import chess.behavior.move.SimpleMove
import chess.behavior.move.MoveBehavior
import chess.entity.ChessBoard
import chess.entity.Dimension
import chess.entity.MovementInfo
import chess.entity.Position

@RunWith(classOf[JUnitRunner])
class SimpleMoveTest extends Spec with BeforeAndAfter {
	
	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _

	before {
		movement = new MoveBehavior with SimpleMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		src = new Position(4, 0)
	}
	
	describe("SimpleMove") {
		
		it("should refuse ") {
			assert(true)
		}
		
		it("should accept 1") {
			assert(true)
		}
		
		it("should accept 2") {
			assert(true)
		}
	}
}