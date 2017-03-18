package edu.kit.informatik.matchthree;

import java.util.ArrayList;
import java.util.List;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public abstract class ChainedMove implements Move {
	private final Position selectedPosition;
	private final List<AtomicBinaryMove> moves;
	
	protected ChainedMove(final Position selectedPosition){
		this.selectedPosition = selectedPosition;
		this.moves = new ArrayList<AtomicBinaryMove>();
	}
	
	@Override
	public boolean canBeApplied(final Board board) {
		return board.containsPosition(selectedPosition) && validMoves(board);
	}
	
	private boolean validMoves(final Board board){
		for(Move move: moves){
			if(!move.canBeApplied(board))
				return false;
		}
		return true;
	}
}
