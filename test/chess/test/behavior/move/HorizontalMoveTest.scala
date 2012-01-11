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
import chess.behavior.move.HorizontalMove
import chess.entity.ChessBoard
import chess.entity.Rook
import chess.entity.Piece
import chess.entity.Pawn
import chess.entity.Color._

@RunWith(classOf[JUnitRunner])
class HorizontalMoveTest extends Spec with BeforeAndAfter {

	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _
	var piece: Piece = _
	
	before {
		movement = new MoveBehavior with HorizontalMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		src = new Position(3, 3)
		piece = new Pawn()
		piece.color = White
	}

	describe("HorizontalMove") {

		it("should reject move on the same position") {
			dst = new Position(3, 3)
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

		it("should accept move two squares on the right") {
			dst = new Position(5, 3)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}

		it("should accept move two squares on the left") {
			dst = new Position(1, 3)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}

		it("should reject if there is a piece on the way") {
			dst = new Position(7, 3)
			val pieceOnTheWay = new Rook()
			chessBoard.squares(5)(3) = pieceOnTheWay
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}

		it("should reject move two squares on the top") {
			dst = new Position(3, 5)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}

		it("should return correct possible moves") {
			dst = new Position(4, 3);
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			val output = movement.possibleMoves(mvtInfo)
			assert(output(4)(3) == 1)
		}
	}
}
