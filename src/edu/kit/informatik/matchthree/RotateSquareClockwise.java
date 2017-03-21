package edu.kit.informatik.matchthree;

import java.util.ArrayList;
import java.util.List;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * Encapsulates the logic for the movement rotate square clockwise
 * @author JoseNote
 *
 */
public final class RotateSquareClockwise extends RotateSquare {
    /**
     * Creates a new instance
     * @param selectedPosition the selected position, that represents the left top corner of a 2x2 square
     */
    public RotateSquareClockwise(final Position selectedPosition) {
        super(selectedPosition);
    }

    @Override
    public Move reverse() {
        return new RotateSquareCounterClockwise(selectedPosition);
    }
    
    @Override
    public List<Replace> getReplaceMoves(final Board board) {
        final List<Replace> moves = new ArrayList<Replace>();
        
        moves.add(new Replace(selectedPosition, board.getTokenAt(selectedPosition.plus(0, 1))));
        moves.add(new Replace(selectedPosition.plus(1, 0), board.getTokenAt(selectedPosition)));
        moves.add(new Replace(selectedPosition.plus(1, 1), board.getTokenAt(selectedPosition.plus(1, 0))));
        moves.add(new Replace(selectedPosition.plus(0, 1), board.getTokenAt(selectedPosition.plus(1, 1))));
        return moves;
    }
}
