package edu.kit.informatik.matchthree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class RotateRowRight implements Move {
    private final int rowIndex;
    
    /**
     * 
     * @param rowIndex
     */
    public RotateRowRight(final int rowIndex) {
        this.rowIndex = rowIndex;
    }
    
    @Override
    public boolean canBeApplied(final Board board) {
        return rowIndex >= 0 && rowIndex <= board.getRowCount() - 1;
    }

    @Override
    public void apply(final Board board) {
        // FIXME check first
        for (Replace r: getReplaceMoves(board)) {
            r.apply(board);
        }
    }

    @Override
    public Move reverse() {
        return new RotateRowLeft(rowIndex);
    }

    @Override
    public Set<Position> getAffectedPositions(final Board board) {
        final Set<Position> affected = new HashSet<Position>();

        for (int colIndex = 0; colIndex < board.getColumnCount(); colIndex++) {
            affected.add(Position.at(colIndex, rowIndex));
        }
        return affected;
    }
    
    private List<Replace> getReplaceMoves(final Board board) {
        final List<Replace> moves = new ArrayList<Replace>();
        Position p;
        
        // FIXME check valid
        p = Position.at(0, rowIndex);
        moves.add(new Replace(p, board.getTokenAt(Position.at(board.getColumnCount() - 1, rowIndex))));
        for (int colIndex = 1; colIndex < board.getColumnCount(); colIndex++) {
            p = Position.at(colIndex, rowIndex);
            moves.add(new Replace(p, board.getTokenAt(p.plus(-1, 0))));
        }
        return moves;
    }
}
