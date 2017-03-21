package edu.kit.informatik.matchthree;

import java.util.ArrayList;
import java.util.List;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public class RotateColumnUp extends RotateColumn {
    /**
     * FIXME add doc
     * @param columnIndex FIXME add doc
     */
    public RotateColumnUp(final int columnIndex) {
        super(columnIndex);
    }

    @Override
    public Move reverse() {
        return new RotateColumnDown(columnIndex);
    }
    
    @Override
    protected List<Replace> getReplaceMoves(final Board board) {
        final List<Replace> moves = new ArrayList<Replace>();
        Position into;
        Position from;
        
        into = Position.at(columnIndex, board.getRowCount() - 1);
        from = Position.at(columnIndex, 0);
        moves.add(new Replace(into, board.getTokenAt(from)));
        for (int rowIndex = 0; rowIndex < board.getRowCount() - 1; rowIndex++) {
            into = Position.at(columnIndex, rowIndex);
            from = into.plus(0, 1);
            moves.add(new Replace(into, board.getTokenAt(from)));
        }
        return moves;
    }
}
