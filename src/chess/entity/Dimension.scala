package chess.entity

class Dimension(var width : Int, var height : Int) {
	
	def isInBounds(pos : Position) : Boolean = {
		return pos.x < width && pos.x >= 0 && pos.y < height && pos.y >= 0
	}
}