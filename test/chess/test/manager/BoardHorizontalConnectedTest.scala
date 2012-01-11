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
class BoardHorizontalConnectedTest extends Spec with BeforeAndAfter {

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
		bishop.position = new Position(2, 0)
		knight.position = new Position(7, 1)
		chessModel.boardManager.isHorizontalConnected = true
		boardManager = chessModel.boardManager
		boardManager.board.squares(2)(0) = bishop
		boardManager.board.squares(7)(1) = knight
	}

	/*
	 * Echecs cylindriques horizontal :
	 * Les x correspondent aux positions possibles du fou B.
	 * Les o correspondent aux positions possibles du cavalier K.
	 * 
	 * 8  . x . x . . . .
	 * 7  x . . . x . . . 
	 * 6  . . . . . x . x 
	 * 5  . . . . . . x .
	 * 4  o . . . . x o x
	 * 3  x o . . x o . . 
	 * 2  . x . x . . . K
	 * 1  . o B . . o . . 
	 * 
	 *    a b c d e f g h 
	 */
	
	describe("BoardManagerHorizontalConnected") {

		it("should accept bishop move") {
			assert(boardManager.canMove(new Position(1, 1), bishop))
			assert(boardManager.canMove(new Position(0, 2), bishop))
			
			assert(boardManager.canMove(new Position(3, 1), bishop))
			assert(boardManager.canMove(new Position(4, 2), bishop))
			assert(boardManager.canMove(new Position(5, 3), bishop))
			assert(boardManager.canMove(new Position(6, 4), bishop))
			assert(boardManager.canMove(new Position(7, 5), bishop))
			assert(boardManager.canMove(new Position(7, 3), bishop))
			
			assert(boardManager.canMove(new Position(5, 5), bishop))
			assert(boardManager.canMove(new Position(4, 6), bishop))
			assert(boardManager.canMove(new Position(3, 7), bishop))
			
			assert(boardManager.canMove(new Position(0, 6), bishop))
			assert(boardManager.canMove(new Position(1, 7), bishop))
		}

//		it("should accept knight move classic move") {
//			assert(boardManager.canMove(new Position(5, 0), knight))
//			assert(boardManager.canMove(new Position(5, 2), knight))
//			assert(boardManager.canMove(new Position(6, 3), knight))
//			assert(boardManager.canMove(new Position(0, 3), knight))
//			assert(boardManager.canMove(new Position(1, 0), knight))
//			assert(boardManager.canMove(new Position(1, 2), knight))
//		}
	}
}