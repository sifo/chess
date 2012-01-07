package chess.history


trait History {
	def addAction(action: Action)
	def changeStat(action: Action)			// Changer l'état des pièces, utilisée dans CastlingMove
	def getAction(indice: Int) : Action
	def getLastAction() : Action
}