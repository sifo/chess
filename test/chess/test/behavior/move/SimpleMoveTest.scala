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
import chess.entity.Pawn
import chess.history.ChessHistory
import chess.history.Action


@RunWith(classOf[JUnitRunner])
class SimpleMoveTest extends Spec with BeforeAndAfter {
	
	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var src_2: Position = _
	var src_3: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _
	var chessHistory : ChessHistory = _
	var action : Action = _
	var pawn : Pawn = _

	before {
		movement   = new MoveBehavior with SimpleMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		src   = new Position(4, 1)
		src_2 = new Position(4, 2)
		src_3 = new Position(4, 4)
	}
	
	describe("SimpleMove") {
		
		it("should reject move on the same position") {
			dst = new Position(4, 1)
			chessHistory = new ChessHistory
			mvtInfo = new MovementInfo(src, dst, null, chessBoard, chessHistory)
			assert(!movement.canMove(mvtInfo))
		}
		
		it("should reject move more than one square (outside the second column)") {
			dst = new Position(4, 4)
			chessHistory = new ChessHistory
			mvtInfo = new MovementInfo(src_2, dst, null, chessBoard, chessHistory)
			assert(!movement.canMove(mvtInfo))
		}
		
		
		it("should accept simple move") {
			dst = new Position(4, 2)
			chessHistory = new ChessHistory
			mvtInfo = new MovementInfo(src, dst, null, chessBoard, chessHistory)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept move more than one square (inside the second column)") {
			dst = new Position(4, 3)
			chessHistory = new ChessHistory
			mvtInfo = new MovementInfo(src, dst, null, chessBoard, chessHistory)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept") {
			chessHistory = new ChessHistory
			pawn = new Pawn
			action = new Action(new Position(5, 6), new Position(5, 4), pawn)
			chessHistory.addAction(action)
			dst = new Position(5, 5)
			
			mvtInfo = new MovementInfo(src_3, dst, pawn, chessBoard, chessHistory)
			assert(movement.canMove(mvtInfo))
		}
	}
}