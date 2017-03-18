package edu.kit.informatik.matchthree;

import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public final class FlipLeft extends AtomicBinaryMove {	
	public FlipLeft(final Position startPosition) {
		super(startPosition);
	}
	
	@Override
	protected Position getObjectivePosition() {
		return new Position(selectedPosition.x - 1, selectedPosition.y);
	}

	@Override
	public Move reverse() {
		return new FlipRight(selectedPosition);
	}

	@Override
	public Set<Position> getAffectedPositions(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

}
