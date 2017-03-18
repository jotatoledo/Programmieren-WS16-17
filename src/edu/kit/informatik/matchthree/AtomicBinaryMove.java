package edu.kit.informatik.matchthree;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public abstract class AtomicBinaryMove implements Move {
	protected final Position startPosition;
	protected final Position affectedPosition;
	
	protected AtomicBinaryMove(final Position startPosition){
		this.startPosition = startPosition;
		this.affectedPosition = this.getAffectedPosition();
	}
	
	@Override
	public boolean canBeApplied(final Board board) {
		return board.containsPosition(startPosition) && board.containsPosition(affectedPosition);
	}
	
	@Override
	public void apply(final Board board) {
		if(!canBeApplied(board)) throw new BoardDimensionException("the move cant be applied");
		board.swapTokens(startPosition, affectedPosition);
	}
	
	abstract protected Position getAffectedPosition();
}
