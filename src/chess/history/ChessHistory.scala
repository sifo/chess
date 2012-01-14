package chess.history
import chess.entity.King
import chess.entity.Rook
import chess.entity.Color._

class ChessHistory extends History {
  var listeActions = List[Action]()
  
  private var kingWhiteInit  = true
  private var rookLWhiteInit = true
  private var rookRWhiteInit = true
  private var kingBlackInit  = true
  private var rookLBlackInit = true
  private var rookRBlackInit = true
  
  def addAction(action: Action) {
    listeActions = action :: listeActions
    changeStat(action)
  }
  
  def getAction(indice: Int) : Action = {
    if ((indice + 1) > listeActions.length) getLastInternal(listeActions)
    else getActionInternal(listeActions, indice)
  }
  
  def getLastAction() : Action = {
      getLastInternal(listeActions)
  }
  
  def getLastInternal(liste: List[Action]) : Action = liste match {
    case (a :: _) => a
    case _ => null
  }
  
  def getActionInternal(liste: List[Action], i: Int) : Action = (liste, i) match {
    case (a :: xs, 0) => a
    case (a :: xs, i) => getActionInternal(xs, i-1)
    case _			  => null
  }
  
  /*
   * Fonctions utilisées dans le CastlingMove
   * Enregistrer et récupérer l'état des pièces concernées par le CastlingMove
   *  
   */
  
  def changeStat(action: Action) {
    if(action.piece.isInstanceOf[King] && action.piece.color == White)
      kingWhiteInit = false
    else if(action.piece.isInstanceOf[Rook] && action.piece.color == White
        && action.src.x == 0
        && action.src.y == 0)
      rookLWhiteInit = false
    else if(action.piece.isInstanceOf[Rook] && action.piece.color == White
        && action.src.x == 7
        && action.src.y == 0)
      rookRWhiteInit = false
    
    else if(action.piece.isInstanceOf[King] && action.piece.color == Black)
      kingBlackInit = false
    else if(action.piece.isInstanceOf[Rook] && action.piece.color == Black
        && action.src.x == 0
        && action.src.y == 7)
      rookLBlackInit = false
    else if(action.piece.isInstanceOf[Rook] && action.piece.color == Black
        && action.src.x == 7
        && action.src.y == 7)
      rookRBlackInit = false
  }
  
  def getKingWhiteStat  = kingWhiteInit
  def getrookLWhiteStat = rookLWhiteInit
  def getrookRWhiteStat = rookRWhiteInit
  def getKingBlackStat  = kingBlackInit
  def getrookLBlackStat = rookLBlackInit
  def getrookRBlackStat = rookRBlackInit
  
  def setKingWhiteStat(stat: Boolean)  = kingWhiteInit  = stat // Fonctions
  def setRookRWhiteStat(stat: Boolean) = rookRWhiteInit = stat // utlisées
  def setRookLWhiteStat(stat: Boolean) = rookLWhiteInit = stat // dans les tests
}