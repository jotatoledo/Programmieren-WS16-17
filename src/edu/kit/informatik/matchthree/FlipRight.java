package edu.kit.informatik.matchthree;

import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class FlipRight implements Move{
	private final Position startPosition;
	private final Position positionToTheRight;
	
	public FlipRight(final Position startPosition){
		this.startPosition = startPosition;
		this.positionToTheRight = new Position(startPosition.x + 1, startPosition.y);
	}
	
	@Override
	public boolean canBeApplied(final Board board) {
		return board.containsPosition(startPosition) && board.containsPosition(positionToTheRight);
	}

	@Override
	public void apply(final Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Move reverse() {
		return new FlipLeft(startPosition);
	}

	@Override
	public Set<Position> getAffectedPositions(final Board board) {
		// TODO Auto-generated method stub
		return null;
	}

}
