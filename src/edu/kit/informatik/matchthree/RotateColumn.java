package edu.kit.informatik.matchthree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * Encapsulates common logic for a rotate column move
 * @author JoseNote
 *
 */
public abstract class RotateColumn implements Move {
    /**
     * A column index
     */
    protected final int columnIndex;
    
    /**
     * Creates a new instance
     * @param columnIndex the selected column index
     */
    protected RotateColumn(final int columnIndex) {
        this.columnIndex = columnIndex;
    }
    
    @Override
    public boolean canBeApplied(Board board) {
        return columnIndex >= 0 && columnIndex <= board.getColumnCount() - 1;
    }

    @Override
    public void apply(Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("invalid move");
        for (Replace r: getReplaceMoves(board)) {
            r.apply(board);
        }
    }
    
    @Override
    public Set<Position> getAffectedPositions(Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("invalid move");
        final Set<Position> affected = new HashSet<Position>();

        for (int rowIndex = 0; rowIndex < board.getRowCount(); rowIndex++) {
            affected.add(Position.at(columnIndex, rowIndex));
        }
        return affected;
    }

    /**
     * FIXME add doc
     * @param board FIXME add doc
     * @return FIXME add doc
     */
    protected abstract List<Replace> getReplaceMoves(Board board);
}
