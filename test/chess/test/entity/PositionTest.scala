package chess.test.entity
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import chess.entity.Position

class PositionTest {
	
	@Test
	def equals() {
		val p1 = new Position(3, 3)
		val p2 = new Position(3, 3)
		val p3 = new Position(4, 4)
		val p4 = new Position(3, 5)
		assertTrue(p1.equals(p2))
		assertFalse(p1.equals(p3))
		assertFalse(p1.equals(p4))
	}
}