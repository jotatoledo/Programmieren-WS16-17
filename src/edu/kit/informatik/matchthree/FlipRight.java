package edu.kit.informatik.matchthree;

import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public final class FlipRight extends AtomicBinaryMove {	
	public FlipRight(Position startPosition) {
		super(startPosition);
	}
	
	@Override
	protected Position getAffectedPosition() {
		return new Position(startPosition.x + 1, startPosition.y);
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
