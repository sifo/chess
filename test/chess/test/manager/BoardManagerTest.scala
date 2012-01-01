package chess.test.manager
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.entity.Player
import chess.entity.Color._
import chess.manager.BoardManager
import chess.ChessModel
import chess.entity.Position
import chess.entity.Piece
import chess.entity.Rook
import chess.entity.Pawn
import chess.history.Action

@RunWith(classOf[JUnitRunner])
class BoardManagerTest extends Spec with BeforeAndAfter {

	var boardManager: BoardManager = _
	var chessModel: ChessModel = _
	var pos: Position = _
	var movingPiece: Piece = _
	var attackedPiece: Piece = _
	var action: Action = _

	before {
		chessModel = new ChessModel()
		pos = new Position(0, 1)
		movingPiece = new Rook()
		attackedPiece = new Pawn()
		movingPiece.position = new Position(0, 0)
		attackedPiece.position = new Position(0, 1)
		boardManager = new BoardManager(chessModel)
		boardManager.board.squares(0)(0) = movingPiece
		boardManager.move(pos, movingPiece)
		action = boardManager.history.getLastAction()
	}

	describe("BoardManager") {
		
		it("should change position on piece after a move") {
			assert(movingPiece.position.equals(pos))
		}

		it("should remove piece on last position on board after a move") {
			assert(boardManager.board.squares(0)(0) == null)
		}

		it("should put piece on final position on board after a move") {
			assert(boardManager.board.squares(0)(1) == movingPiece)
		}
		
		it("should remove attacked piece on destination position if any") {
			assert(boardManager.board.squares(0)(1) != attackedPiece)
		}
		
		it("should add action to history with initial position") {
			assert(action.src.equals(new Position(0, 0)))
		}
		
		it("should add action to history with final position") {
			assert(action.dst.equals(new Position(0, 1)))
		}
		
		it("should add action to history with piece") {
			assert(action.piece == movingPiece)
		}
	}
}