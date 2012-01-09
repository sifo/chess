package chess.ui
import scala.util.matching.Regex
import chess.ChessListener
import chess.ChessView
import chess.ChessController
import chess.ui.events.MoveEvent
import chess.ui.events.TellCantMovePieceEvent
import chess.ui.events.PlacePieceOnBoardEvent
import chess.ui.events.DrawBoardEvent
import chess.entity._
import java.util.regex.Pattern
import chess.ui.events.MoveEvent

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
    for (i <- 0 until localBoard.length) {
      print("--");
    }
    println("");
  }
  def close() {
    exit(0);
  }

  def drawBoard(event: MoveEvent): Unit = {
    localBoard(event.dst.x)(event.dst.y) = localBoard(event.src.x)(event.src.y);

    if (event.src.x % 2 == 0) {
      if (event.src.y % 2 == 0)
        localBoard(event.src.x)(event.src.y) = "@ ";
      else
        localBoard(event.src.x)(event.src.y) = "Ø ";
    } else {
      if (event.src.y % 2 == 0)
        localBoard(event.src.x)(event.src.y) = "Ø ";
      else
        localBoard(event.src.x)(event.src.y) = "@ ";
    }

    // display();
  }

  def drawBoard(event: DrawBoardEvent): Unit = {
    localBoard = Array.fill[String](event.dimension.height, event.dimension.width)("");

    for (i <- 0 until event.dimension.height) {
      for (j <- 0 until event.dimension.width) {
        if (localBoard(i)(j) == "") {
          if (i % 2 == 0) {
            if (j % 2 == 0)
              localBoard(i)(j) = "@ ";
            else
              localBoard(i)(j) = "Ø ";
          } else {
            if (j % 2 == 0)
              localBoard(i)(j) = "Ø ";
            else
              localBoard(i)(j) = "@ ";
          }
        }
      }
    }

    //display();
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

  def processUserInput(input: String): MoveEvent = {
    var pattern = Pattern.compile("([A-Z]|[a-z])(\\d)([A-Z]|[a-z])(\\d)").matcher(input);
    if (pattern.matches()) {
      new MoveEvent(
        new Position(
          pattern.group(1).toUpperCase().charAt(0).toInt - 65,
          pattern.group(2).toInt - 1),
        new Position(
          pattern.group(3).toUpperCase().charAt(0).toInt - 65,
          pattern.group(4).toInt - 1));
    } else {
      null
    }
  }
}