package edu.kit.informatik.matchthree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

public abstract class RotateSquare implements Move {
    /**
     * The square formed by the given position that represents the left top corner
     */
    protected final Set<Position> square;
    /**
     * The selected position to apply this move
     */
    protected final Position selectedPosition;

    /**
     * Creates a new instance
     * @param selectedPosition the selected position to apply this
     */
    protected RotateSquare(final Position selectedPosition) {
        this.selectedPosition = selectedPosition;
        final Set<Position> square = new HashSet<Position>();
        this.square = square;
        square.add(selectedPosition);
        square.add(selectedPosition.plus(1, 0));
        square.add(selectedPosition.plus(1, 1));
        square.add(selectedPosition.plus(0, 1));
    }

    @Override
    public boolean canBeApplied(final Board board) {
        for (Position p: square) {
            if (!board.containsPosition(p))
                return false;
        }
        return true;
    }

    @Override
    public void apply(final Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("invalid move");
        for (Replace replace: getReplaceMoves(board)) {
            replace.apply(board);
        }
    }
    
    @Override
    public Set<Position> getAffectedPositions(final Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("invalid move");
        return square.stream().collect(Collectors.toSet());
    }
    
    /**
     * Generates a list of replacement moves
     * @param board the board to apply the replacements on
     * @return a list of replacement moves
     */
    public abstract List<Replace> getReplaceMoves(Board board);
}
