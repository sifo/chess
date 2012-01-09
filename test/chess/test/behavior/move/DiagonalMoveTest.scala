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

@RunWith(classOf[JUnitRunner])
class DiagonalMoveTest extends Spec with BeforeAndAfter {

  var movement: MoveBehavior = _
  var chessBoard: ChessBoard = _
  var src: Position = _
  var dst: Position = _
  var mvtInfo: MovementInfo = _

  before {
    movement = new MoveBehavior with DiagonalMove
    chessBoard = new ChessBoard(new Dimension(8, 8))
    src = new Position(3, 3)
  }

  describe("DiagonalMoveTest") {

    it("should reject move on the same position") {
      dst = new Position(3, 3);
      mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
      assert(!movement.canMove(mvtInfo))
    }

    it("should reject move on a square not on the same diagonal") {
      dst = new Position(5, 4);
      mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
      assert(!movement.canMove(mvtInfo))
    }

    it("should reject move on a square out of bounds") {
      dst = new Position(10, 10);
      mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
      assert(!movement.canMove(mvtInfo))
    }

    it("should accept move on the same diagonal") {
      dst = new Position(4, 4);
      mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
      assert(movement.canMove(mvtInfo))
    }

    it("should reject if there is a piece on the way") {
      dst = new Position(5, 5)
      val pieceOnTheWay = new Rook()
      chessBoard.squares(4)(4) = pieceOnTheWay
      mvtInfo = new MovementInfo(src, dst, null, chessBoard, null)
      assert(!movement.canMove(mvtInfo))
    }
  }
}