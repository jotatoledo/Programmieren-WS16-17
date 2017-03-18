package edu.kit.informatik.matchthree;

import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class FlipDown implements Move {
	private final Position startPosition;
	private final Position positionBelow;
	
	public FlipDown(final Position startPosition){
		this.startPosition = startPosition;
		this.positionBelow = new Position(startPosition.x,startPosition.y - 1);
	}
	
	@Override
	public boolean canBeApplied(final Board board) {
		return board.containsPosition(startPosition) && board.containsPosition(positionBelow);
	}

	@Override
	public void apply(Board board) {
		// FIXME Auto-generated method stub
		
	}

	@Override
	public Move reverse() {
		// FIXME Auto-generated method stub
		return null;
	}

	@Override
	public Set<Position> getAffectedPositions(Board board) {
		// FIXME Auto-generated method stub
		return null;
	}

}
