package chess.test.behavior.move
import chess.behavior.move.AnySimpleMove
import chess.entity.Position
import chess.entity.MovementInfo
import chess.entity.Dimension
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.behavior.move.MoveBehavior
import chess.entity.ChessBoard
import chess.behavior.move.HorizontalLeapMove
import chess.entity.Rook
import chess.entity.Pawn

@RunWith(classOf[JUnitRunner])
class HorizontalLeapMoveTest extends Spec with BeforeAndAfter {

	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _

	before {
		movement = new MoveBehavior with HorizontalLeapMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		chessBoard.squares(6)(3) = new Pawn()
		chessBoard.squares(2)(3) = new Pawn()
		src = new Position(3, 3)
	}

	describe("HorizontalLeapMove") {

		it("should reject move on the same position") {
			dst = new Position(3, 3)
			mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}

		it("should reject move on a square not on the same line and row") {
			dst = new Position(5, 5)
			mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}

		it("should reject move on a square out of bounds") {
			dst = new Position(10, 10)
			mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
			assert(!movement.canMove(mvtInfo));
		}

		it("should accept move four squares on the right after leaping") {
			dst = new Position(7, 3)
			mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}

		it("should accept move two squares on the left after leaping") {
			dst = new Position(1, 3)
			mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}

		it("should reject if there is no piece on the way") {
			dst = new Position(5, 3)
			mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}

		it("should reject move two squares on the top") {
			dst = new Position(3, 5)
			mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}
	}
}
