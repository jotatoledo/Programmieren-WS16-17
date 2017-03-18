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
	protected Position getAffectedPosition() {
		return new Position(startPosition.x, startPosition.y - 1);
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
