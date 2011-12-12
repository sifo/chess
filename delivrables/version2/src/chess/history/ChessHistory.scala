package chess.history

class ChessHistory extends History {
  private var listeActions = List[Action]()
  
  def addAction(action: Action) {
    listeActions = action :: listeActions
  }
  
  def getAction(indice: Int) : Action = {
    if ((indice + 1) > listeActions.length) getLastInternal(listeActions)
    else getActionInternal(listeActions, indice)
  }
  
  def getLastAction() : Action = {
      getLastInternal(listeActions)
  }
  
  def getLastInternal(liste: List[Action]) : Action = liste match {
    case (a :: Nil) => a
    case (a1 :: a2 :: xs) => getLastInternal(a2 :: xs)
    case _ => null
  }
  
  def getActionInternal(liste: List[Action], i: Int) : Action = (liste, i) match {
    case (a :: xs, 0) => a
    case (a :: xs, i) => getActionInternal(xs, i-1)
  }
}