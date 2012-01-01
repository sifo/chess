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

@RunWith(classOf[JUnitRunner])
class PlayerManagerTest extends Spec with BeforeAndAfter {
	
	var playerManager: PlayerManager = _
	var chessModel: ChessModel = _
	
	before {
		chessModel = new ChessModel()
		playerManager = new PlayerManager(chessModel)
	}
	
	describe("PlayerManager") {
		
		it("should accept more players in game") {
			var player = new Player("name", Black)
			assert(!playerManager.players.contains(player))
			playerManager.add(player)
			assert(playerManager.players.contains(player))
		}
		
		it("should increment currentPlayerIndex after setting next player") {
			playerManager.currentPlayerIndex = 0
			playerManager.setNextPlayer()
			assert(playerManager.currentPlayerIndex == 1)
		}
		
		it("should put currentPlayerIndex at 0 if the index was on last element") {
			playerManager.currentPlayerIndex = playerManager.players.size - 1
			playerManager.setNextPlayer()
			assert(playerManager.currentPlayerIndex == 0)
		}
	}
}