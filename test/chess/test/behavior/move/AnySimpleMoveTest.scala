package chess.test.behavior.move
import chess.behavior.move.AnySimpleMove
import chess.entity.Position
import chess.entity.MovementInfo
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.behavior.move.MoveBehavior
import chess.entity.ChessBoard
import chess.entity.Dimension
import chess.entity.Piece
import chess.entity.Pawn
import chess.entity.Color._

@RunWith(classOf[JUnitRunner])
class AnySimpleMoveTest extends Spec with BeforeAndAfter {
	
	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _
	var piece: Piece = _
	
	before {
		movement = new MoveBehavior with AnySimpleMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		src = new Position(3, 3)
		piece = new Pawn()
		piece.color = White
	}
	
	describe("AnySimpleMove") {
		
		it("should reject move on the same position") {
			dst = new Position(3, 3)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}
		
		it("should reject move on a square more than one square away") {
			dst = new Position(5, 5)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}
		
		it("should reject move on a square out of bounds") {
			dst = new Position(10, 10)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo));
		}
		
		it("should accept move to the square on the right") {
			dst = new Position(4, 3)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept move to the square on the upper right") {
			dst = new Position(4, 4)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept move to the square on the lower left") {
			dst = new Position(2, 2)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}
	}
}