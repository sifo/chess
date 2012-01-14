package chess.ui
import scala.util.matching.Regex
import chess.ChessListener
import chess.ChessView
import chess.ChessController
import chess.ui.events.MoveEvent
import chess.ui.events.PlacePieceOnBoardEvent
import chess.ui.events.DrawBoardEvent
import chess.entity._
import java.util.regex.Pattern
import chess.ui.events.MoveEvent
import chess.entity.Color._
import chess.ui.events.CantMovePieceEvent
import chess.ui.events.CantPutYourselfInCheckEvent
import chess.ui.events.CheckEvent
import chess.ui.events.CheckMateEvent
import chess.ui.events.PatEvent
import chess.ui.events.NotPlayerTurnEvent
import chess.ui.events.NoPieceAtPositionEvent

object TextView {
	val NOT_PLAYER_TURN = "Ce n'est pas le tour de ce joueur."
	val CANT_MOVE_PIECE = "Impossible de déplacer cette pièce."
	val CANT_GO_IN_CHECK = "Vous ne pouvez pas vous mettre en situation d'échec."
	val OUPUT_INDICATOR = "< "
	val PROMPT = "> "
	val CHECK = "Echec du roi!"
	val CHECK_MATE = "Echec et mat!"
	val PAT = "Pat!"
	val NO_PIECE_AT_POSITION = "Aucune pièce à cette position."
}

class TextView(chessController: ChessController) extends ChessView(chessController) {
  var localBoard: Array[Array[String]] = null
  var writer = new TextViewWriter(chessController)
  writer.start()

  def display() {
  	var ok = true
    for (j <- localBoard(0).length - 1 to 0 by -1) {
      print(j + "  ")
      for (i <- 0 until localBoard.length) {
        print(localBoard(i)(j) + " ")
      }
      println("")
    }
    println("")
    print("   ")
    for (i <- 0 until localBoard.length) {
      print(i + " ")
    }
    println("\n")
  }

  def close() {
    writer.stop() 
  }

  def drawBoard(event: DrawBoardEvent): Unit = {
  	val dim = event.chessBoard.dimension
  	val squares = event.chessBoard.squares
    localBoard = Array.fill[String](dim.width, dim.height)("");

    for (i <- 0 until dim.height) {
      for (j <- 0 until dim.width) {
      	val piece = squares(i)(j)
      	if (piece != null)
      		if(piece.color == White)
      		  localBoard(i)(j) = pieceToSymbol(piece).toUpperCase
      		else 
      		  localBoard(i)(j) = pieceToSymbol(piece)
      	else
      	  localBoard(i)(j) = "."
      }
    }
    println()
    display()
    print(TextView.PROMPT)
  }

  def cantMovePiece(event: CantMovePieceEvent): Unit = {
    display();
    println(TextView.OUPUT_INDICATOR + TextView.CANT_MOVE_PIECE)
    print(TextView.PROMPT)
  }
  
  def notPlayerTurn(event: NotPlayerTurnEvent): Unit = {
    display()
    println(TextView.OUPUT_INDICATOR + TextView.NOT_PLAYER_TURN)
    print(TextView.PROMPT)
  }
  
  def cantPutYourselfInCheck(event: CantPutYourselfInCheckEvent): Unit = {
    display()
    println(TextView.OUPUT_INDICATOR + TextView.CANT_GO_IN_CHECK)
    print(TextView.PROMPT)
  }
  
  def check(event: CheckEvent): Unit = {
    display()
    println(TextView.OUPUT_INDICATOR + TextView.CHECK)
    print(TextView.PROMPT)
  }
  
  def checkmate(event: CheckMateEvent): Unit = {
    display()
    println(TextView.OUPUT_INDICATOR + TextView.CHECK_MATE)
    print(TextView.PROMPT)
  }
  
  def pat(event: PatEvent): Unit = {
    display()
    println(TextView.OUPUT_INDICATOR + TextView.PAT)
    print(TextView.PROMPT)
  }
  
  def noPieceAtPosition(event: NoPieceAtPositionEvent): Unit = {
    display()
    println(TextView.OUPUT_INDICATOR + TextView.NO_PIECE_AT_POSITION)
    print(TextView.PROMPT)
  }

  def pieceToSymbol(piece : Piece): String = {
  	piece match {
  		case _:Bishop => return "b"
  		case _:Pawn => return "p"
  		case _:Knight => return "n"
  		case _:Rook => return "r"
  		case _:Queen => return "q"
  		case _:King => return "k"
  		case whatever => return "?"
  	}
  }
}