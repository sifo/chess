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
    println("----------------------------------Called");
    
    localBoard = Array.fill[String](event.dimension.height, event.dimension.width)("");

    for (i <- 0 until event.dimension.height) {
      for (j <- 0 until event.dimension.width) {
        if (localBoard(i)(j) == "") {
          if (i % 2 == 0) {
            if (j % 2 == 0)
              localBoard(i)(j) = "@";
            else
              localBoard(i)(j) = "Ø";
          } else {
            if (j % 2 == 0)
              localBoard(i)(j) = "Ø";
            else
              localBoard(i)(j) = "@";
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

    localBoard(event.position.x)(event.position.y) = {
              if (event.actualPiece.color == Color.Black) {
          //Ceci est à corriger selon l'implémentation de Color
          //pour différencier sur l'affichage les pièces noires et blanches
          event.actualPiece.significantLetter().toUpperCase();
        } else {
          event.actualPiece.significantLetter().toLowerCase();
        }
    }
  }
}