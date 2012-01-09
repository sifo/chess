package chess.test
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

@RunWith(classOf[JUnitRunner])
class LMoveTest extends Spec with BeforeAndAfter {

  var movement: MoveBehavior = _
  var chessBoard: ChessBoard = _
  var src: Position = _
  var dst: Position = _
  var mvtInfo: MovementInfo = _

  before {
    movement = new MoveBehavior with LMove
    chessBoard = new ChessBoard(new Dimension(8, 8))
    src = new Position(4, 0)
  }

  describe("LMoveTest") {

    it("should refuse if source and destination are the same") {
      dst = new Position(4, 0);
      mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
      assert(!movement.canMove(mvtInfo))
    }

    it("should refuse if destination is not a LMove") {
      dst = new Position(6, 0);
      mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
      assert(!movement.canMove(mvtInfo))
    }

    it("should reject move on a square out of bounds") {
      dst = new Position(10, 10)
      mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
      assert(!movement.canMove(mvtInfo));
    }

    it("should accept LMove") {
      dst = new Position(5,2)
      mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
      assert(movement.canMove(mvtInfo));
    }

    it("should accept rotated LMove") {
      dst = new Position(3,1)
      mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
      assert(movement.canMove(mvtInfo));
    }
  }
}