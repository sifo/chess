package chess.protocol;
import chess.event.Event

object Protocol {
	def translateIn (s : String) : Event = {
	  new Event;
	}
	
	def translateOut (e : Event) : String = {
	  new String;
	}
}