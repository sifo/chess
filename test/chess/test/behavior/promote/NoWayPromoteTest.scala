package chess.test.behavior.promote
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
import chess.behavior.promote.PromoteBehavior
import chess.behavior.promote.NoWayPromote

@RunWith(classOf[JUnitRunner])
class NoWayPromoteTest extends Spec with BeforeAndAfter {
	
	var promote: PromoteBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _

	before {
		promote = new PromoteBehavior with NoWayPromote
		chessBoard = new ChessBoard(new Dimension(8, 8))
		src = new Position(4, 0)
	}
	
	describe("NoWayPromoteTest") {
		
		it("should always refuse") {
			assert(true)
		}
	}
}