package edu.kit.informatik.matchthree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class RotateColumnUp implements Move {
    private final int columnIndex;

    /**
     * FIXME add doc
     * @param columnIndex FIXME add doc
     */
    public RotateColumnUp(final int columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public boolean canBeApplied(Board board) {
        return columnIndex >= 0 && columnIndex <= board.getColumnCount() - 1;
    }

    @Override
    public void apply(Board board) {
        for (Replace r: getReplaceMoves(board)) {
            r.apply(board);
        }
    }

    @Override
    public Move reverse() {
        return new RotateColumnDown(columnIndex);
    }

    @Override
    public Set<Position> getAffectedPositions(Board board) {
        final Set<Position> affected = new HashSet<Position>();

        for (int rowIndex = 0; rowIndex < board.getRowCount(); rowIndex++) {
            affected.add(Position.at(columnIndex, rowIndex));
        }
        return affected;
    }
    
    private List<Replace> getReplaceMoves(final Board board) {
        final List<Replace> moves = new ArrayList<Replace>();
        Position p;
        
        p = Position.at(columnIndex, board.getRowCount() - 1);
        moves.add(new Replace(p, board.getTokenAt(Position.at(columnIndex, 0))));
        for (int rowIndex = 0; rowIndex < board.getRowCount() - 1; rowIndex++) {
            p = Position.at(columnIndex, rowIndex);
            moves.add(new Replace(p, board.getTokenAt(p.plus(0, 1))));
        }
        return moves;
    }
}
