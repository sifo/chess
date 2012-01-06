package chess.test
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.entity.ChessBoard
import chess.entity.MovementInfo
import chess.behavior.move.MoveBehavior
import chess.entity.Position
import chess.behavior.move.LMove
import chess.entity.Dimension
import chess.behavior.move.NoWayMove

@RunWith(classOf[JUnitRunner])
class NoWayMoveTest extends Spec with BeforeAndAfter {
	
	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _

	before {
		movement = new MoveBehavior with NoWayMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		src = new Position(4, 0)
	}
	
	describe("NoWayMoveTest") {
		
		it("should always refuse") {
			assert(true)
		}
		
	}
}