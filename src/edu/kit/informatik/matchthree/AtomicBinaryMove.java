package edu.kit.informatik.matchthree;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public abstract class AtomicBinaryMove implements Move {
	protected final Position selectedPosition;
	protected final Position objectivePosition;
	
	protected AtomicBinaryMove(final Position startPosition){
		this.selectedPosition = startPosition;
		this.objectivePosition = this.getObjectivePosition();
	}
	
	@Override
	public boolean canBeApplied(final Board board) {
		return board.containsPosition(selectedPosition) && board.containsPosition(objectivePosition);
	}
	
	@Override
	public void apply(final Board board) {
		if(!canBeApplied(board)) throw new BoardDimensionException("the move cant be applied");
		board.swapTokens(selectedPosition, objectivePosition);
	}
	
	abstract protected Position getObjectivePosition();
}
