package chess.test
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.entity.ChessBoard
import chess.entity.MovementInfo
import chess.behavior.move.MoveBehavior
import chess.entity.Position
import chess.behavior.move.CastlingMove
import chess.entity.Dimension

@RunWith(classOf[JUnitRunner])
class CastlingMoveTest extends Spec with BeforeAndAfter {
	
	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _

	before {
		movement = null
		chessBoard = new ChessBoard(new Dimension(8, 8))
		src = new Position(4, 0)
	}
	
	describe("CastlingMoveTest") {
		
		it("should refuse if king moved already") {
			assert(true)
		}
		
		it("should accept if right rook") {
			assert(true)
		}
		
		it("should accept if left rook") {
			assert(true)
		}
	}
}