package edu.kit.informatik.matchthree;

import java.util.ArrayList;
import java.util.List;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * Encapsulates the logic for a rotate column down movement
 * @author JoseNote
 *
 */
public class RotateColumnDown extends RotateColumn {    
    /**
     * Creates a new instance
     * @param columnIndex the selected column index
     */
    public RotateColumnDown(final int columnIndex) {
        super(columnIndex);
    }

    @Override
    public Move reverse() {
        return new RotateColumnUp(columnIndex);
    }

    @Override
    protected List<Replace> getReplaceMoves(final Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("invalid move");
        final List<Replace> moves = new ArrayList<Replace>();
        Position into;
        Position from;
        
        into = Position.at(columnIndex, 0);
        from = into.plus(0, board.getRowCount() - 1);
        moves.add(new Replace(into, board.getTokenAt(from)));
        for (int rowIndex = 1; rowIndex < board.getRowCount(); rowIndex++) {
            into = Position.at(columnIndex, rowIndex);
            from = into.plus(0, -1);
            moves.add(new Replace(into, board.getTokenAt(from)));
        }
        return moves;
    }
}
