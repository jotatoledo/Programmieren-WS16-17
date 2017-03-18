package edu.kit.informatik.matchthree;

import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class FlipLeft implements Move{
	private final Position startPosition;
	private final Position positionToTheLeft;
	
	public FlipLeft(final Position startPosition){
		this.startPosition = startPosition;
		this.positionToTheLeft = new Position(startPosition.x - 1, startPosition.y);
	}
	
	@Override
	public boolean canBeApplied(final Board board) {
		return board.containsPosition(startPosition) && board.containsPosition(positionToTheLeft);
	}

	@Override
	public void apply(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Move reverse() {
		return new FlipRight(startPosition);
	}

	@Override
	public Set<Position> getAffectedPositions(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

}
