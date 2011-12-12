package chess.history

import chess.history.Action  	// J'arrive pas à résoudre ce problème l'hors de l'importation
								// Meme problème pour la class Color dans Piece

trait History {
	def addAction(action: Action)
	def getAction(indice: Int) : Action
	def getLastAction() : Action
}