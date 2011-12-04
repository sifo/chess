package chess.entity

class Position(var x: Int, var y: Int) {
	
	def getX = x
	
	def getY = y
	
	def setX(newX :Int) = x = newX
	
	def setY(newY :Int) = y = newY
}