package chess.test.manager
import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfter
import org.scalatest.Spec
import chess.entity.Color._
import chess.entity.Bishop
import chess.entity.Pawn
import chess.entity.Piece
import chess.entity.Position
import chess.entity.Rook
import chess.history.Action
import chess.manager.BoardManager
import chess.ChessModel
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BoardManagerTest extends Spec with BeforeAndAfter {

	var boardManager: BoardManager = _
	var chessModel: ChessModel = _
	var pos: Position = _
	var movingPiece: Piece = _
	var attackedPiece: Piece = _
	var action: Action = _
	var playerIndexBeforeMove: Int = _

	before {
		chessModel = new ChessModel()
		pos = new Position(0, 1)
		movingPiece = new Rook()
		attackedPiece = new Pawn()
		movingPiece.position = new Position(0, 0)
		attackedPiece.position = new Position(0, 1)
		boardManager = new BoardManager(chessModel)
		boardManager.board.squares(0)(0) = movingPiece
		playerIndexBeforeMove = chessModel.playerManager.currentPlayerIndex
		boardManager.move(pos, movingPiece)
		action = boardManager.history.getLastAction()
	}

	describe("BoardManager movement") {

		it("should change current player") {
			if (playerIndexBeforeMove != chessModel.playerManager.players.size - 1)
				assert(playerIndexBeforeMove + 1 == chessModel.playerManager.currentPlayerIndex)
			else
				assert(0 == chessModel.playerManager.currentPlayerIndex)
		}

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

		it("should be able to create board with standard setup for pieces") {
			val rook = boardManager.board.squares(7)(0)
			val pawn = boardManager.board.squares(7)(6)
			assert(rook.isInstanceOf[Rook])
			assert(pawn.isInstanceOf[Pawn])
			assert(rook.color == White)
			assert(pawn.color == Black)
		}
	}

	describe("BoardManager build piece") {

		it("should assign player 1 with color white") {
			val piece = BoardManager.buildPiece("bishop", 1, null)
			assert(piece.color == White)
		}

		it("should assign player 2 with color black") {
			val piece = BoardManager.buildPiece("bishop", 2, null)
			assert(piece.color == Black)
		}

		it("should create piece with correct position") {
			val position = new Position(1, 2)
			val piece = BoardManager.buildPiece("bishop", 1, position)
			assert(piece.position.equals(position))
		}

		it("should be able to create piece of type bishop") {
			val piece = BoardManager.buildPiece("bishop", 1, null)
			assert(piece.isInstanceOf[Bishop])
		}

		it("should be able to create piece of type pawn") {
			val piece = BoardManager.buildPiece("pawn", 1, null)
			assert(piece.isInstanceOf[Pawn])
		}
	}
}