package edu.kit.informatik.matchthree;

import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public final class FlipDown extends AtomicBinaryMove {	
	public FlipDown(final Position startPosition) {
		super(startPosition);
	}
	
	@Override
	protected Position getObjectivePosition() {
		return new Position(selectedPosition.x, selectedPosition.y - 1);
	}

	@Override
	public Move reverse() {
		return new FlipUp(selectedPosition);
	}

	@Override
	public Set<Position> getAffectedPositions(Board board) {
		// FIXME Auto-generated method stub
		return null;
	}

}
