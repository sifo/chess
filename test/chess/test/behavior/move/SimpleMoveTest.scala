package chess.test.behavior.move
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
import chess.entity.Color._

@RunWith(classOf[JUnitRunner])
class SimpleMoveTest extends Spec with BeforeAndAfter {
	
	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _
	var chessHistory : ChessHistory = _
	var action : Action = _
	var pawnWhite : Pawn = _

	before {
		movement   = new MoveBehavior with SimpleMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		pawnWhite = new Pawn()
		pawnWhite.color = White
		pawnWhite.position = new Position(3, 1)
	}
	
	describe("SimpleMove") {
		
		it("should reject move on the same position") {
			dst = new Position(3, 1)
			chessHistory = new ChessHistory
			mvtInfo = new MovementInfo(pawnWhite.position, dst, pawnWhite, chessBoard, chessHistory)
			assert(!movement.canMove(mvtInfo))
		}
		
		it("should reject move more than two squares") {
			dst = new Position(3, 4)
			chessHistory = new ChessHistory
			mvtInfo = new MovementInfo(pawnWhite.position, dst, pawnWhite, chessBoard, chessHistory)
			assert(!movement.canMove(mvtInfo))
		}
		
//		it("should reject move more than two squares") {
//			dst = new Position(3, 4)
//			chessHistory = new ChessHistory
//			mvtInfo = new MovementInfo(pawnWhite.position, dst, pawnWhite, chessBoard, chessHistory)
//			assert(!movement.canMove(mvtInfo))
//		}
		
		
		it("should accept one square move") {
			dst = new Position(3, 2)
			chessHistory = new ChessHistory
			mvtInfo = new MovementInfo(pawnWhite.position, dst, pawnWhite, chessBoard, chessHistory)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept two squares move") {
			dst = new Position(3, 3)
			chessHistory = new ChessHistory
			mvtInfo = new MovementInfo(pawnWhite.position, dst, pawnWhite, chessBoard, chessHistory)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept en passant") {
			chessHistory = new ChessHistory
			val blackPawn = new Pawn
			blackPawn.color = Black
			blackPawn.position = new Position(5, 4)
			action = new Action(new Position(5, 6), new Position(5, 4), blackPawn)
			chessHistory.addAction(action)
			dst = new Position(5, 5)
			pawnWhite.position = new Position(4, 4)
			mvtInfo = new MovementInfo(pawnWhite.position, dst, pawnWhite, chessBoard, chessHistory)
			assert(movement.canMove(mvtInfo))
		}
	}
}