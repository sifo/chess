package chess.test.behavior.move
import org.junit.Assert.assertTrue
import org.junit.Test
import chess.behavior.move.AnySimpleMove
import chess.entity.Position

class AnySimpleMoveTest {
	
	@Test 
	def move() {
		val movement = new AnySimpleMove {}
		val initPost = new Position(3, 3)
		val destPost = new Position(4, 3)
		assertTrue(movement.canMove(initPost, destPost));
	}
}