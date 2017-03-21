package edu.kit.informatik.matchthree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public abstract class RotateRow implements Move {
    /**
     * The selected index of the row to rotate
     */
    protected final int rowIndex;
    
    /**
     * Creates a new instance
     * @param rowIndex the selected row index
     */
    public RotateRow(final int rowIndex) {
        this.rowIndex = rowIndex;
    }
    
    @Override
    public boolean canBeApplied(final Board board) {
        return rowIndex >= 0 && rowIndex <= board.getRowCount() - 1;
    }
    
    @Override
    public Set<Position> getAffectedPositions(final Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("invalid move");
        final Set<Position> affected = new HashSet<Position>();

        for (int colIndex = 0; colIndex < board.getColumnCount(); colIndex++) {
            affected.add(Position.at(colIndex, rowIndex));
        }
        return affected;
    }
    
    @Override
    public void apply(final Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("invalid move");
        for (Replace r: getReplaceMoves(board)) {
            r.apply(board);
        }
    }
    
    /**
     * FIXME add doc
     * @param board FIXME add doc
     * @return FIXME add doc
     */
    protected abstract List<Replace> getReplaceMoves(Board board);
}
