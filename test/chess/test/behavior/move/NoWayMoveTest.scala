package chess.test.behavior.move
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
import chess.entity.Piece
import chess.entity.Pawn
import chess.entity.Color._

@RunWith(classOf[JUnitRunner])
class NoWayMoveTest extends Spec with BeforeAndAfter {
	
	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _
	var piece: Piece = _
	
	before {
		movement = new MoveBehavior with NoWayMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		src = new Position(4, 0)
		dst = new Position(4, 1)
		piece = new Pawn()
		piece.color = White
		mvtInfo = new MovementInfo(src, dst, piece, chessBoard, null)
	}
	
	describe("NoWayMoveTest") {
		
		it("should always refuse") {
			assert(!movement.canMove(mvtInfo))
		}
		
	}
}