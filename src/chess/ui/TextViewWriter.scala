package chess.ui
import chess.entity.Position
import chess.ui.events.MoveEvent
import chess.ChessController

object TextViewWriter {
	val PROMPT = ">"
	val UNKOWN_COMMAND = "Commande invalide."
}

class TextViewWriter(var chessController : ChessController) extends Thread {
	
	override def run() = {
		var ok = true
		while (ok) {
			//print(TextViewWriter.PROMPT + " ")
			processInput(readLine())

		}
	}

	def processInput(input: String) = {
		val MovePattern = """(\d+) (\d+) (\d+) (\d+)""".r
		val SimpleMovePattern = """(\d)(\d)(\d)(\d)""".r
		input match {
			case MovePattern(a, b, c, d) => {
				val src = new Position(a.toInt, b.toInt)
				val dst = new Position(c.toInt, d.toInt)
				val movement = new MoveEvent(src, dst)
				chessController.notifyMovement(movement)
			}
			case SimpleMovePattern(a, b, c, d) => {
				val src = new Position(a.toInt, b.toInt)
				val dst = new Position(c.toInt, d.toInt)
				val movement = new MoveEvent(src, dst)
				chessController.notifyMovement(movement)
			}
			case "exit" => chessController.notifyQuitGame()
			case _ => {
				println(TextViewWriter.UNKOWN_COMMAND)
			}
		}
	}

}