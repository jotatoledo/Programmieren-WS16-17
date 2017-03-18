package edu.kit.informatik.matchthree;

import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class FlipUp implements Move {
	private final Position startPosition;
	private final Position positionAbove;
	
	public FlipUp(final Position startPosition){
		this.startPosition = startPosition;
		this.positionAbove = new Position(startPosition.x,startPosition.y + 1);
	}
	
	@Override
	public boolean canBeApplied(final Board board) {
		return board.containsPosition(startPosition) && board.containsPosition(positionAbove);
	}

	@Override
	public void apply(Board board) {
		// FIXME Auto-generated method stub
		
	}

	@Override
	public Move reverse() {
		return new FlipDown(startPosition);
	}

	@Override
	public Set<Position> getAffectedPositions(Board board) {
		// FIXME Auto-generated method stub
		return null;
	}

}
