package chess.test
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.behavior.move.MoveBehavior
import chess.behavior.move.CastlingMove
import chess.entity.ChessBoard
import chess.entity.MovementInfo
import chess.entity.Position
import chess.entity.Dimension
import chess.entity.Piece
import chess.entity.Color._
import chess.history.ChessHistory

@RunWith(classOf[JUnitRunner])
class CastlingMoveTest extends Spec with BeforeAndAfter {
	
	var movement: MoveBehavior = _
	var chessBoard: ChessBoard = _
	var src: Position = _
	var dst: Position = _
	var mvtInfo: MovementInfo = _
	var chessHistory : ChessHistory = _
	var piece : Piece = _

	before {
		movement = new MoveBehavior with CastlingMove
		chessBoard = new ChessBoard(new Dimension(8, 8))
		src = new Position(4, 0)
	}
	
	describe("CastlingMoveTest") {
		
		it("should refuse if king moved already") {
			chessHistory = new ChessHistory
			chessHistory.kingWhiteInit = false
			dst = new Position(6, 0)
			piece = new Piece ; piece.color = White 
			
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, chessHistory)
			assert(!movement.canMove(mvtInfo))
		}
		
		it("should refuse small Castling if right rook moved already") {
			chessHistory = new ChessHistory
			chessHistory.getrookRWhiteStat = false
			dst = new Position(6, 0)
			piece = new Piece ; piece.color = White
			
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, chessHistory)
			assert(!movement.canMove(mvtInfo))
		}
		
		it("should refuse big Castling if left rook moved already") {
			chessHistory = new ChessHistory
			chessHistory.getrookLWhiteStat = false
			dst = new Position(2, 0)
			piece = new Piece ; piece.color = White
			
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, chessHistory)
			assert(!movement.canMove(mvtInfo))
		}
		
		it("should accept small Castling if rigtht rook not already moved") {
			chessHistory = new ChessHistory
			chessHistory.getrookLWhiteStat = false
			dst = new Position(6, 0)
			piece = new Piece ; piece.color = White
			
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, chessHistory)
			assert(movement.canMove(mvtInfo))
		}
		
		it("should accept if left rook") {
			chessHistory = new ChessHistory
			chessHistory.getrookRWhiteStat = false
			dst = new Position(2, 0)
			piece = new Piece ; piece.color = White
			
			mvtInfo = new MovementInfo(src, dst, piece, chessBoard, chessHistory)
			assert(movement.canMove(mvtInfo))
		}
	}
}