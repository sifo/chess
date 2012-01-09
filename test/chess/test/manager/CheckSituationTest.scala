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

@RunWith(classOf[JUnitRunner])
class CheckSituationTest extends Spec with BeforeAndAfter {

	var boardManager: BoardManager = _
	var chessModel: ChessModel = _
	var rookWhite: Piece = _
	var rookBlack: Piece = _
	var kingWhite: Piece = _
	var kingBlack: Piece = _

	before {
		chessModel = new ChessModel("res/empty-chess-config.xml")
		rookWhite = new Rook()
		rookWhite.color = White
		kingWhite = new King()
		kingWhite.color = White
		kingBlack = new King()
		kingBlack.color = Black
		rookBlack = new Rook()
		rookBlack.color = Black
		rookWhite.position = new Position(3, 1)
		kingWhite.position = new Position(4, 0)
		kingBlack.position = new Position(2, 5)
		rookBlack.position = new Position(5, 4)
		boardManager = chessModel.boardManager
		boardManager.board.squares(3)(1) = rookWhite
		boardManager.board.squares(4)(0) = kingWhite
		boardManager.board.squares(2)(5) = kingBlack
		boardManager.board.squares(5)(4) = rookBlack
		boardManager.pieces = rookWhite :: boardManager.pieces
		boardManager.pieces = kingWhite :: boardManager.pieces
		boardManager.pieces = kingBlack :: boardManager.pieces
		boardManager.pieces = rookBlack :: boardManager.pieces
	}

	/*
	 * k roi noir, R tour blanche, K roi blanc, r tour noire.
	 * On bouge R en c2, et on regarde si le jeu repère si le roi
	 * noir est en échec.
	 * 
	 * 8  . . . . . . . .
	 * 7  . . . . . . . . 
	 * 6  . . k . . . . . 
	 * 5  . . . . . r . .
	 * 4  . . . . . . . .
	 * 3  . . . . . . . . 
	 * 2  . . . R . . . .
	 * 1  . . . . K . . . 
	 * 
	 *    a b c d e f g h 
	 */

	describe("CheckSituation") {

		it("should change isCheck to true if there is a check situation") {
			assert(boardManager.isCheck == false)
			boardManager.move(new Position(2, 1), rookWhite)
			assert(boardManager.isCheck == true)
		}

		it("should refuse move which not remove check situation") {
			val dest = new Position(5, 6)
			boardManager.move(new Position(2, 1), rookWhite)
			boardManager.move(dest, rookBlack)
			assert(boardManager.board.squares(dest.x)(dest.y) != rookBlack)
			assert(chessModel.playerManager.currentPlayerIndex == 1)
		}

		it("should accept move which block check situation") {
			val dest = new Position(2, 4)
			boardManager.move(new Position(2, 1), rookWhite)
			boardManager.move(dest, rookBlack)
			assert(boardManager.board.squares(dest.x)(dest.y) == rookBlack)
			assert(chessModel.playerManager.currentPlayerIndex == 0)
		}

		it("should accept move which remove check situation") {
			val dest = new Position(3, 5)
			boardManager.move(new Position(2, 1), rookWhite)
			boardManager.move(dest, kingBlack)
			assert(boardManager.board.squares(dest.x)(dest.y) == kingBlack)
			assert(chessModel.playerManager.currentPlayerIndex == 0)
		}
	}
}