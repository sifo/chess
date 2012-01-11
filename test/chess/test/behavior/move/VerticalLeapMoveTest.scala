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
import chess.entity.Rook
import chess.entity.Pawn
import chess.behavior.move.VerticalLeapMove
import chess.entity.Piece
import chess.entity.Color._

@RunWith(classOf[JUnitRunner])
class VerticalLeapMoveTest extends Spec with BeforeAndAfter {

	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _
	var piece: Piece = _
	
	before {
		movement = new MoveBehavior with VerticalLeapMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		chessBoard.squares(3)(6) = new Pawn()
		chessBoard.squares(3)(2) = new Pawn()
		src = new Position(3, 3)
		piece = new Pawn()
		piece.color = White
	}

	describe("VerticalLeapMove") {

		it("should reject move on the same position") {
			dst = new Position(3, 3)
			chessBoard.squares(3)(6) = new Pawn()
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}

		it("should reject move on a square not on the same line and row") {
			dst = new Position(5, 5)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}

		it("should reject move on a square out of bounds") {
			dst = new Position(10, 10)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo));
		}

		it("should accept move four squares on the top after leaping") {
			dst = new Position(3, 7)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}

		it("should accept move two squares on the bottom after leaping") {
			dst = new Position(3, 1)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}

		it("should reject if there is no piece on the way") {
			dst = new Position(3, 5)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}

		it("should reject move two squares on the right") {
			dst = new Position(5, 3)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}
	}
}
