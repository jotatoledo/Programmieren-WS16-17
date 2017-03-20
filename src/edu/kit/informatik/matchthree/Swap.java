package edu.kit.informatik.matchthree;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class Swap implements Move  {
    private final Position selectedPosition;
    private final Position objectivePosition;
    
    /**
     * FIXME add doc
     * @param selectedPosition FIXME add doc
     * @param objectivePosition FIXME add doc
     */
    public Swap(final Position selectedPosition, final Position objectivePosition) {
        this.selectedPosition = selectedPosition;
        this.objectivePosition = objectivePosition;
    }

    @Override
    public boolean canBeApplied(final Board board) {
        return board.containsPosition(selectedPosition) && board.containsPosition(objectivePosition);
    }

    @Override
    public void apply(final Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("the move cant be applied");
        board.swapTokens(selectedPosition, objectivePosition);
    }
    
    @Override
    public Set<Position> getAffectedPositions(Board board) {
        final Set<Position> affected = new HashSet<Position>();
        
        affected.add(selectedPosition);
        affected.add(objectivePosition);
        return affected;
    }

    @Override
    public Move reverse() {
        return new Swap(objectivePosition, selectedPosition);
    }
}
