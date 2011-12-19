package chess.entity

class Position(var x: Int, var y: Int) {
	
	def equals(position : Position) : Boolean = {
		position.x == x && position.y == y
	}
		
}