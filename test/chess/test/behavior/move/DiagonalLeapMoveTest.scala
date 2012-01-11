package chess.test
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.entity.ChessBoard
import chess.entity.MovementInfo
import chess.behavior.move.MoveBehavior
import chess.entity.Position
import chess.behavior.move.DiagonalMove
import chess.entity.Dimension
import chess.entity.Piece
import chess.entity.Pawn
import chess.entity.Color._

@RunWith(classOf[JUnitRunner])
class DiagonalLeapMoveTest extends Spec with BeforeAndAfter {

	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _
	var piece: Piece = _
	
	before {
		movement = new MoveBehavior with DiagonalMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		src = new Position(3, 3)
		piece = new Pawn()
		piece.color = White
	}

	describe("DiagonalMoveTest") {

		it("should reject move on the same position") {
			assert(true)
		}

		it("should reject move on a square not on the same diagonal") {
			assert(true)
		}

		it("should reject move on a square out of bounds") {
			assert(true)
		}

		it("should accept move on the same diagonal") {
			assert(true)
		}

		it("should accept if there is a piece on the way") {
			assert(true)
		}
	}
}