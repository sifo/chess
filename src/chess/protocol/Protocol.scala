package chess.protocol;
import chess.event.Event

trait Protocol {
	def translateIn (s : String) : Event
	
	def translateOut (e : Event) : String
}