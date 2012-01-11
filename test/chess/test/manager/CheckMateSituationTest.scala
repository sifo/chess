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
class CheckMateSituationTest extends Spec with BeforeAndAfter {

	var boardManager: BoardManager = _
	var chessModel: ChessModel = _

	before {
		chessModel = new ChessModel("res/empty-chess-config.xml")
		boardManager = chessModel.boardManager
	}

	describe("CheckMateSituation") {

		/*
		 * k roi noir, R tour blanche, K roi blanc, Q reine blanche.
		 * On bouge R en c2, et on regarde si le jeu repère si le roi
		 * noir est en échec.
		 * 
		 * 8  . . . . k . . .
		 * 7  . R . . . . . . 
		 * 6  Q . . . . . . . 
		 * 5  . . . . . . . .
		 * 4  . . . . . . . .
		 * 3  . . . . . . . . 
		 * 2  . . . . . . . .
		 * 1  . . . . K . . . 
		 * 
		 *    a b c d e f g h 
		 */
		it("should see checkmate situation") {
			val rookWhite = new Rook()
			rookWhite.color = White
			val kingWhite = new King()
			kingWhite.color = White
			val kingBlack = new King()
			kingBlack.color = Black
			val queenWhite = new Queen()
			queenWhite.color = White
			rookWhite.position = new Position(1, 6)
			kingWhite.position = new Position(4, 0)
			queenWhite.position = new Position(0, 5)
			kingBlack.position = new Position(4, 7)
			boardManager.board.squares(1)(6) = rookWhite
			boardManager.board.squares(4)(0) = kingWhite
			boardManager.board.squares(0)(5) = queenWhite
			boardManager.board.squares(4)(7) = kingBlack
			boardManager.pieces = rookWhite :: boardManager.pieces
			boardManager.pieces = kingWhite :: boardManager.pieces
			boardManager.pieces = kingBlack :: boardManager.pieces
			boardManager.pieces = queenWhite :: boardManager.pieces
			boardManager.move(new Position(0, 7), queenWhite)
			assert(boardManager.isCheckMate)
		}

		/*
		 * k roi noir, R tour blanche, K roi blanc, Q reine blanche, b fou noir.
		 * On bouge R en c2, et on regarde si le jeu repère si le roi
		 * noir est en échec.
		 * 
		 * 8  . . . . k . . .
		 * 7  . . . . . . . R 
		 * 6  Q . . . . . . . 
		 * 5  . . . b . . . .
		 * 4  . . . . . . . .
		 * 3  . . . . . . . . 
		 * 2  . . . . . . . .
		 * 1  . . . . K . . . 
		 * 
		 *    a b c d e f g h 
		 */
		it("should see solution to check situation") {
			val rookWhite = new Rook()
			rookWhite.color = White
			val kingWhite = new King()
			kingWhite.color = White
			val kingBlack = new King()
			kingBlack.color = Black
			val queenWhite = new Queen()
			queenWhite.color = White
			val bishopBlack = new Bishop()
			bishopBlack.color = Black
			rookWhite.position = new Position(7, 6)
			kingWhite.position = new Position(4, 0)
			queenWhite.position = new Position(0, 5)
			kingBlack.position = new Position(4, 7)
			bishopBlack.position = new Position(3, 4)
			boardManager.board.squares(7)(6) = rookWhite
			boardManager.board.squares(4)(0) = kingWhite
			boardManager.board.squares(0)(5) = queenWhite
			boardManager.board.squares(4)(7) = kingBlack
			boardManager.board.squares(3)(4) = bishopBlack
			boardManager.pieces = rookWhite :: boardManager.pieces
			boardManager.pieces = kingWhite :: boardManager.pieces
			boardManager.pieces = kingBlack :: boardManager.pieces
			boardManager.pieces = queenWhite :: boardManager.pieces
			boardManager.pieces = bishopBlack :: boardManager.pieces
			boardManager.move(new Position(0, 7), queenWhite)
			assert(!boardManager.isCheckMate)
		}

		/*
		 * k roi noir, R tour blanche, K roi blanc, Q reine blanche, r tour noire.
		 * On bouge R en c2, et on regarde si le jeu repère si le roi
		 * noir est en échec.
		 * 
		 * 8  . . . . k . . .
		 * 7  . . . . . . . Q 
		 * 6  . . . . . . . . 
		 * 5  . . . . . C . .
		 * 4  . . . . . . . .
		 * 3  . . . . . . . . 
		 * 2  . . . . . . . .
		 * 1  . . . . K . . . 
		 * 
		 *    a b c d e f g h 
		 */
		it("should see checkmate situation with defensive piece") {
			val knightWhite = new Knight()
			knightWhite.color = White
			val kingWhite = new King()
			kingWhite.color = White
			val kingBlack = new King()
			kingBlack.color = Black
			val queenWhite = new Queen()
			queenWhite.color = White
			knightWhite.position = new Position(5, 4)
			kingWhite.position = new Position(4, 0)
			queenWhite.position = new Position(7, 6)
			kingBlack.position = new Position(4, 7)
			boardManager.board.squares(5)(4) = knightWhite
			boardManager.board.squares(4)(0) = kingWhite
			boardManager.board.squares(7)(6) = queenWhite
			boardManager.board.squares(4)(7) = kingBlack
			boardManager.pieces = knightWhite :: boardManager.pieces
			boardManager.pieces = kingWhite :: boardManager.pieces
			boardManager.pieces = kingBlack :: boardManager.pieces
			boardManager.pieces = queenWhite :: boardManager.pieces
			boardManager.move(new Position(4, 6), queenWhite)
			assert(boardManager.isCheckMate)
		}

		/*
		 * k roi noir, R tour blanche, K roi blanc, Q reine blanche, r tour noire.
		 * On bouge R en c2, et on regarde si le jeu repère si le roi
		 * noir est en échec.
		 * 
		 * 8  . . . . k . . .
		 * 7  . . . . . . . R 
		 * 6  Q . . . . . . . 
		 * 5  . . . r . . . .
		 * 4  . . . . . . . .
		 * 3  . . . . . . . . 
		 * 2  . . . . . . . .
		 * 1  . . . . K . . . 
		 * 
		 *    a b c d e f g h 
		 */
		it("should see solution with blocking piece") {
			val rookWhite = new Rook()
			rookWhite.color = White
			val kingWhite = new King()
			kingWhite.color = White
			val kingBlack = new King()
			kingBlack.color = Black
			val queenWhite = new Queen()
			queenWhite.color = White
			val rookBlack = new Rook()
			rookBlack.color = Black
			rookWhite.position = new Position(7, 6)
			kingWhite.position = new Position(4, 0)
			queenWhite.position = new Position(0, 5)
			kingBlack.position = new Position(4, 7)
			rookBlack.position = new Position(3, 4)
			boardManager.board.squares(7)(6) = rookWhite
			boardManager.board.squares(4)(0) = kingWhite
			boardManager.board.squares(0)(5) = queenWhite
			boardManager.board.squares(4)(7) = kingBlack
			boardManager.board.squares(3)(4) = rookBlack
			boardManager.pieces = rookWhite :: boardManager.pieces
			boardManager.pieces = kingWhite :: boardManager.pieces
			boardManager.pieces = kingBlack :: boardManager.pieces
			boardManager.pieces = queenWhite :: boardManager.pieces
			boardManager.pieces = rookBlack :: boardManager.pieces
			boardManager.move(new Position(0, 7), queenWhite)
			assert(!boardManager.isCheckMate)
		}
	}
}