package edu.kit.informatik.matchthree;

import java.util.ArrayList;
import java.util.List;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class RotateRowRight extends RotateRow {
    /**
     * Creates a new instance
     * @param rowIndex the selected row index
     */
    public RotateRowRight(final int rowIndex) {
        super(rowIndex);
    }

    @Override
    public Move reverse() {
        return new RotateRowLeft(rowIndex);
    }
    
    @Override
    protected List<Replace> getReplaceMoves(final Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("invalid move");
        final List<Replace> moves = new ArrayList<Replace>();
        Position into;
        Position from;
        
        into = Position.at(0, rowIndex);
        from = Position.at(board.getColumnCount() - 1, rowIndex);
        moves.add(new Replace(into, board.getTokenAt(from)));
        for (int colIndex = 1; colIndex < board.getColumnCount(); colIndex++) {
            into = Position.at(colIndex, rowIndex);
            from = into.plus(-1, 0);
            moves.add(new Replace(into, board.getTokenAt(from)));
        }
        return moves;
    }
}
