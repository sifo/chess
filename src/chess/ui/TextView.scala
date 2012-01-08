package chess.ui
import chess.ChessListener
import chess.ChessView
import chess.ChessController
import chess.ui.events.MoveEvent
import chess.ui.events.TellCantMovePieceEvent
import chess.ui.events.PlacePieceOnBoardEvent
import chess.ui.events.DrawBoardEvent
import chess.entity._

class TextView(chessController: ChessController) extends ChessView(chessController) {
  var outputIndicator = ">";
  var inputIndicator = "<";
  var localBoard: Array[Array[String]] = null;

  def display() {

    for (i <- 0 until localBoard.length) {
      for (j <- 0 until localBoard(i).length) {
        print(localBoard(i)(j));
      }
      println("");
    }

  }
  def close() {
    exit(0);
  }

  def drawBoard(event: MoveEvent): Unit = {
		  localBoard(event.dst.x)(event.dst.y) = localBoard(event.src.x)(event.src.y);
		  
		  if (event.src.x % 2 == 0) {
            if (event.src.y % 2 == 0)
              localBoard(event.src.x)(event.src.y) = "B";
            else
              localBoard(event.src.x)(event.src.y) = "W";
          } else {
            if (event.src.y % 2 == 0)
              localBoard(event.src.x)(event.src.y) = "W";
            else
              localBoard(event.src.x)(event.src.y) = "B";
          }
		  
		  display();
  }

  def drawBoard(event: DrawBoardEvent): Unit = {
    localBoard = Array.fill[String](event.dimension.height, event.dimension.width)("");

    for (i <- 0 until event.dimension.height) {
      for (j <- 0 until event.dimension.width) {
        if (localBoard(i)(j) == "") {
          if (i % 2 == 0) {
            if (j % 2 == 0)
              localBoard(i)(j) = "B";
            else
              localBoard(i)(j) = "W";
          } else {
            if (j % 2 == 0)
              localBoard(i)(j) = "W";
            else
              localBoard(i)(j) = "B";
          }
        }
      }
    }

    display();
  }

  def tellCantMovePiece(event: TellCantMovePieceEvent): Unit = {
    display();
    println(outputIndicator + outputIndicator + event.msg);
  }

  def placePieceOnTile(event: PlacePieceOnBoardEvent): Unit = {
    //Pas propre, il vaudrait mieux avoir dans Piece un champ
    //contenant une chaine de caracteres la representant. Un simple
    //test sur la couleur suffirait, et on n'aurait pas besoin d'utiliser
    //isInstanceOf

    localBoard(event.position.x)(event.position.y) = {
      if (event.actualPiece.isInstanceOf[Bishop]) {
        if (event.actualPiece.color == Color.Black) {
          //Ceci est à corriger selon l'implémentation de Color
          //pour différencier sur l'affichage les pièces noires et blanches
          "I";
        } else {
          "i";
        }
      } else if (event.actualPiece.isInstanceOf[Knight]) {
        if (event.actualPiece.color == Color.Black) {
          //Ceci est à corriger selon l'implémentation de Color
          //pour différencier sur l'affichage les pièces noires et blanches
          "G";
        } else {
          "g";
        }
      } else if (event.actualPiece.isInstanceOf[King]) {
        if (event.actualPiece.color == Color.Black) {
          //Ceci est à corriger selon l'implémentation de Color
          //pour différencier sur l'affichage les pièces noires et blanches
          "K";
        } else {
          "k";
        }
      } else if (event.actualPiece.isInstanceOf[Pawn]) {
        if (event.actualPiece.color == Color.Black) {
          //Ceci est à corriger selon l'implémentation de Color
          //pour différencier sur l'affichage les pièces noires et blanches
          "P";
        } else {
          "p";
        }
      } else if (event.actualPiece.isInstanceOf[Queen]) {
        if (event.actualPiece.color == Color.Black) {
          //Ceci est à corriger selon l'implémentation de Color
          //pour différencier sur l'affichage les pièces noires et blanches
          "Q";
        } else {
          "q";
        }
      } else if (event.actualPiece.isInstanceOf[Rook]) {
        if (event.actualPiece.color == Color.Black) {
          //Ceci est à corriger selon l'implémentation de Color
          //pour différencier sur l'affichage les pièces noires et blanches
          "R";
        } else {
          "r";
        }
      } else {
        localBoard(event.position.x)(event.position.y)
      }
    }
  }
}