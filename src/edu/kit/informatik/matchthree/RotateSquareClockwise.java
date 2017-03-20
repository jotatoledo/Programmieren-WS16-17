package edu.kit.informatik.matchthree;

import java.util.ArrayList;
import java.util.List;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public final class RotateSquareClockwise extends RotateSquare {
    /**
     * FIXME add doc
     * @param selectedPosition FIXME add doc
     */
    public RotateSquareClockwise(final Position selectedPosition) {
        super(selectedPosition);
    }

    @Override
    public Move reverse() {
        return new RotateSquareCounterClockwise(selectedPosition);
    }

    @Override
    public List<Replace> generateChainedReplaces(final Board board) {
        List<Replace> moves = new ArrayList<Replace>();
        
        moves.add(new Replace(selectedPosition, board.getTokenAt(selectedPosition.plus(0, 1))));
        moves.add(new Replace(selectedPosition.plus(1, 0), board.getTokenAt(selectedPosition)));
        moves.add(new Replace(selectedPosition.plus(1, 1), board.getTokenAt(selectedPosition.plus(1, 0))));
        moves.add(new Replace(selectedPosition.plus(0, 1), board.getTokenAt(selectedPosition.plus(1, 1))));
        return moves;
    }
}
