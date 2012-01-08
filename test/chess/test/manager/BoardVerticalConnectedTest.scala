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
import chess.Main
import chess.entity.Knight
import chess.entity.MovementInfo

@RunWith(classOf[JUnitRunner])
class BoardVerticalConnectedTest extends Spec with BeforeAndAfter {

	var boardManager: BoardManager = _
	var chessModel: ChessModel = _
	var bishop: Piece = _
	var knight: Piece = _

	before {
		chessModel = new ChessModel("res/empty-chess-config.xml")
		bishop = new Bishop()
		bishop.color = White
		knight = new Knight()
		knight.color = White
		bishop.position = new Position(2, 2)
		knight.position = new Position(6, 0)
		chessModel.boardManager.isVerticalConnected = true
		boardManager = chessModel.boardManager
		boardManager.board.squares(2)(2) = bishop
		boardManager.board.squares(6)(0) = knight
	}

	/*
	 * Echecs cylindriques vertical :
	 * Les x correspondent aux positions possibles du fou B.
	 * Les o correspondent aux positions possibles du cavalier K.
	 * 
	 * 8  . . . . o x . x
	 * 7  . . . . . o x o 
	 * 6  . . . . . x . x 
	 * 5  x . . . x . . .
	 * 4  . x . x . . . .
	 * 3  . . B . . o . o 
	 * 2  . x . x o . . .
	 * 1  x . . . x . K . 
	 * 
	 *    a b c d e f g h 
	 */
	
	describe("BoardManagerHorizontalConnected") {

		it("should accept bishop move horizontaly") {
			assert(boardManager.canMove(new Position(0, 0), bishop))
			assert(boardManager.canMove(new Position(1, 1), bishop))
			assert(boardManager.canMove(new Position(3, 3), bishop))
			assert(boardManager.canMove(new Position(4, 4), bishop))
			assert(boardManager.canMove(new Position(5, 5), bishop))
			assert(boardManager.canMove(new Position(6, 6), bishop))
			assert(boardManager.canMove(new Position(7, 7), bishop))
			
			assert(boardManager.canMove(new Position(0, 4), bishop))
			assert(boardManager.canMove(new Position(1, 3), bishop))
			assert(boardManager.canMove(new Position(3, 1), bishop))
			assert(boardManager.canMove(new Position(4, 0), bishop))
			
			assert(boardManager.canMove(new Position(5, 7), bishop))
			assert(boardManager.canMove(new Position(6, 6), bishop))
			assert(boardManager.canMove(new Position(7, 5), bishop))
		}

		it("should accept knight move horizontaly") {
			assert(boardManager.canMove(new Position(4, 1), knight))
			assert(boardManager.canMove(new Position(5, 2), knight))
			assert(boardManager.canMove(new Position(7, 2), knight))
			
			assert(boardManager.canMove(new Position(4, 7), knight))
			assert(boardManager.canMove(new Position(5, 6), knight))
			assert(boardManager.canMove(new Position(7, 6), knight))
		}
	}
}