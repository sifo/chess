package chess.test.manager
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import chess.entity.Player
import chess.entity.Color._
import chess.manager.PlayerManager
import chess.ChessModel
import chess.entity.Rook

@RunWith(classOf[JUnitRunner])
class PlayerManagerTest extends Spec with BeforeAndAfter {
	
	var playerManager: PlayerManager = _
	var chessModel: ChessModel = _
	
	before {
		chessModel = new ChessModel()
		playerManager = new PlayerManager(chessModel)
		val playerBlack = new Player("name1", Black)
		val playerWhite = new Player("name2", White)
	}
	
	describe("PlayerManager") {
		
		it("should accept more players in game") {
			val playerManagerTmp = new PlayerManager(chessModel)
			val player = new Player("name", Black)
			assert(!playerManagerTmp.players.contains(player))
			playerManagerTmp.add(player)
			assert(playerManagerTmp.players.contains(player))
		}
		
		it("should increment currentPlayerIndex after setting next player") {
			val playerManagerTmp = new PlayerManager(chessModel)
			val player = new Player("name", Black)
			playerManagerTmp.currentPlayerIndex = 0
			playerManagerTmp.setNextPlayer()
			assert(playerManagerTmp.currentPlayerIndex == 1)
		}
		
		it("should put currentPlayerIndex at 0 if the index was on last element") {
			playerManager.currentPlayerIndex = playerManager.players.size - 1
			playerManager.setNextPlayer()
			assert(playerManager.currentPlayerIndex == 0)
		}
		
		it("should not let a player play if it's not his turn") {
			playerManager.currentPlayerIndex = 0
			val piece = new Rook()
			piece.color = Black
			assert(!playerManager.isPlayerTurn(piece))
		}
		
		it("should let a player play if it's his turn") {
			playerManager.currentPlayerIndex = 0
			val piece = new Rook()
			piece.color = White
			assert(playerManager.isPlayerTurn(piece))
		}
	}
}