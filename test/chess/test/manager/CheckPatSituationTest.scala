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
import chess.entity.MovementInfo
import chess.entity.King
import chess.entity.Queen
import chess.entity.Knight

@RunWith(classOf[JUnitRunner])
class CheckPatSituationTest extends Spec with BeforeAndAfter {

	var boardManager: BoardManager = _
	var chessModel: ChessModel = _

	before {
		chessModel = new ChessModel("res/empty-chess-config.xml")
		boardManager = chessModel.boardManager
	}

	describe("CheckPatSituation") {

		/*
		 * k roi noir,  K roi blanc, Q reine blanche.
		 * prochain coup : b5 b6 
		 * 
		 * 8  k . . . . . . .
		 * 7  . . . . . . . . 
		 * 6  . . . . . . . . 
		 * 5  . Q K . . . . .
		 * 4  . . . . . . . .
		 * 3  . . . . . . . . 
		 * 2  . . . . . . . .
		 * 1  . . . . . . . . 
		 * 
		 *    a b c d e f g h 
		 */
		it("should see pat situation") {
			val kingWhite = new King()
			kingWhite.color = White
			val kingBlack = new King()
			kingBlack.color = Black
			val queenWhite = new Queen()
			queenWhite.color = White
			kingWhite.position = new Position(2, 4)
			queenWhite.position = new Position(1, 4)
			kingBlack.position = new Position(0, 7)
			boardManager.board.squares(2)(4) = kingWhite
			boardManager.board.squares(1)(4) = queenWhite
			boardManager.board.squares(0)(7) = kingBlack
			boardManager.pieces = kingWhite :: boardManager.pieces
			boardManager.pieces = queenWhite :: boardManager.pieces
			boardManager.pieces = kingBlack :: boardManager.pieces
			boardManager.move(new Position(1, 5), queenWhite)
			assert(boardManager.isPat)
		}
		
		
		/*
		 * k roi noir,  K roi blanc, P pion blanc, C cavalier.
		 * prochain coup : c4 C5 
		 * 
		 * 8  k . . . . . . .
		 * 7  . . . . . . . . 
		 * 6  P . C . . . . . 
		 * 5  . . . . . . . .
		 * 4  . . K . . . . .
		 * 3  . . . . . . . . 
		 * 2  . . . . . . . .
		 * 1  . . . . . . . . 
		 * 
		 *    a b c d e f g h 
		 */
		it("should see pat situation with knight") {
			val pawnWhite = new Pawn()
			pawnWhite.color = White
			val kingWhite = new King()
			kingWhite.color = White
			val knightWhite = new Knight()
			knightWhite.color = White
			val kingBlack = new King()
			kingBlack.color = Black
			pawnWhite.position = new Position(0, 5)
			kingWhite.position = new Position(2, 3)
			knightWhite.position = new Position(2, 5)
			kingBlack.position = new Position(0, 7)
			boardManager.board.squares(0)(5) = pawnWhite
			boardManager.board.squares(2)(3) = kingWhite
			boardManager.board.squares(2)(5) = knightWhite
			boardManager.board.squares(0)(7) = kingBlack
			boardManager.pieces = pawnWhite :: boardManager.pieces
			boardManager.pieces = kingWhite :: boardManager.pieces
			boardManager.pieces = knightWhite :: boardManager.pieces
			boardManager.pieces = kingBlack :: boardManager.pieces
			boardManager.move(new Position(2, 4), kingWhite)
			assert(boardManager.isPat)
		}

	}
}