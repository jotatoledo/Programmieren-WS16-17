package edu.kit.informatik.matchthree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public abstract class RotateSquare implements Move {
    /**
     * The selected position to apply this move
     */
    protected final Position selectedPosition;

    /**
     * Creates a new instance
     * @param selectedPosition the selected position to apply this
     */
    protected RotateSquare(final Position selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @Override
    public boolean canBeApplied(final Board board) {
        return board.containsPosition(selectedPosition) && validMoves(board);
    }

    @Override
    public void apply(final Board board) {
        // FIXME check first
        for (Replace replace: generateChainedReplaces(board)) {
            replace.apply(board);
        }
    }
    
    @Override
    public Set<Position> getAffectedPositions(Board board) {
        final Set<Position> affected = new HashSet<Position>();
        
        affected.add(selectedPosition);
        affected.add(selectedPosition.plus(1, 0));
        affected.add(selectedPosition.plus(1, 1));
        affected.add(selectedPosition.plus(0, 1));
        return affected;
    }
    
    private boolean validMoves(final Board board) {
        for (Replace replace: generateChainedReplaces(board)) {
            if (!replace.canBeApplied(board))
                return false;
        }
        return true;
    }
    
    public abstract List<Replace> generateChainedReplaces(final Board board);
}
