package edu.kit.informatik.matchthree;

import java.util.ArrayList;
import java.util.List;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class RotateRowLeft extends RotateRow {
    /**
     * Creates a new instance
     * @param rowIndex the selected row index
     */
    public RotateRowLeft(final int rowIndex) {
        super(rowIndex);
    }

    @Override
    public Move reverse() {
        return new RotateRowRight(rowIndex);
    }
    
    @Override
    protected List<Replace> getReplaceMoves(final Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("invalid move");
        final List<Replace> moves = new ArrayList<Replace>();
        Position p;
        
        p = Position.at(board.getColumnCount() - 1, rowIndex);
        moves.add(new Replace(p, board.getTokenAt(Position.at(0, rowIndex))));
        for (int colIndex = 0; colIndex < board.getColumnCount() - 1; colIndex++) {
            p = Position.at(colIndex, rowIndex);
            moves.add(new Replace(p, board.getTokenAt(p.plus(1, 0))));
        }
        return moves; 
    }

}
