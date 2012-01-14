package chess.test.behavior.move
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.entity.ChessBoard
import chess.entity.MovementInfo
import chess.behavior.move.MoveBehavior
import chess.entity.Position
import chess.behavior.move.SuperLMove
import chess.entity.Dimension
import chess.entity.Pawn
import chess.entity.Piece
import chess.entity.Color._

@RunWith(classOf[JUnitRunner])
class SuperLMoveTest extends Spec with BeforeAndAfter {
	
	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _
	var piece: Piece = _
	
	before {
		movement = new MoveBehavior with SuperLMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		chessBoard.squares(5)(2) = new Pawn()
		src = new Position(1, 0)
		piece = new Pawn()
		piece.color = White
	}
	
	describe("SuperLMoveTest") {
		
		it("should reject move on the same position") {
			dst = new Position(1, 0)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}
		
		it("should reject move on a square out of bounds") {
			dst = new Position(10, 10)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo));
		}
		
		it("should reject move out of ability") {
			dst = new Position(1, 2)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}
		
		it("should reject move if there is a piece on the way") {
			dst = new Position(7, 3)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(!movement.canMove(mvtInfo))
		}
		
		it("should accept classic knight move on the upper left") {
			dst = new Position(0, 2)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept classic knight move on the upper right") {
			dst = new Position(2, 2)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept move after 2 classic knight moves on the same direction") {
			dst = new Position(3, 4)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept move after 3 classic knight moves on the same direction") {
			dst = new Position(4, 6)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept move after 2 classic knight moves where there is a piece") {
			dst = new Position(5, 2)
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
			assert(movement.canMove(mvtInfo))
		}
		
	}
}