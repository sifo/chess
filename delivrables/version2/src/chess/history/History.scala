package chess.history


trait History {
	def addAction(action: Action)
	def getAction(indice: Int) : Action
	def getLastAction() : Action
}