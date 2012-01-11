package chess.behavior.move;
import chess.entity.Position
import chess.entity.MovementInfo

trait SuperLMove extends MoveBehavior {

	override def canMove(mvtInfo: MovementInfo): Boolean = {
        if (!respectPrecondition(mvtInfo)) {
	      return false
	    }
		val pos = mvtInfo.src
		val dest = mvtInfo.dst
		val deltaX = Math.abs(pos.x - dest.x)
		val deltaY = Math.abs(pos.y - dest.y)
		val ratio = if (deltaX != 0) deltaY / deltaX else 0
		val ratioInv = if (deltaY != 0) deltaX / deltaY else 0
		if (ratio == 2 || ratioInv == 2) {
			if (!isBlockedByPiece(deltaX, deltaY, mvtInfo))
				return true
		}
		return super.canMove(mvtInfo)
	}

	private def isBlockedByPiece(deltaX: Int, deltaY: Int, mvtInfo : MovementInfo): Boolean = {
		val max = Math.max(deltaX, deltaY)
		val stepToPosition = max / 2
		
		// On cherche pour chaque pas du nightrider comment évolue x et y
		
		// D'abord comment évolue x et y sans prendre en compte le sens
		var x = 1
		var y = 2
		if(deltaX > deltaY) {
			x = 2
			y = 1
		}
		
		// Puis comment évolue x et y en prenant en compte le sens
		val pos = mvtInfo.src
		val dest = mvtInfo.dst
		var maxY = Math.max(pos.y, dest.y)
		var maxX = Math.max(pos.x, dest.x)
		if(maxX == pos.x)
			x = x * (-1)
		if(maxY == pos.y)
			y = y * (-1)
			
		// Puis on parcourt les cases qui sont sur le chemin pour voir s'il y a une pièce
		for(i <- 1 to stepToPosition - 1) {
			pos.x = pos.x + x
			pos.y = pos.y + y
			if(mvtInfo.chessBoard.squares(pos.x)(pos.y) != null) {
				return true
			}
		}
		return false
	}
}