package edu.kit.informatik.matchthree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * Encapsulates the logic for a swap movement
 * @author JoseNote
 *
 */
public class Swap implements Move  {
    private final Position firstPosition;
    private final Position secondPosition;
    
    /**
     * Creates a new instance
     * @param firstPosition one of the selected positions to apply the swap
     * @param secondPosition the other position selected to apply the swap
     * @throws NullPointerException if any of the given positions is null
     */
    public Swap(final Position firstPosition, final Position secondPosition) {
        Objects.requireNonNull(firstPosition, "first position is null");
        Objects.requireNonNull(secondPosition, "second position is null");
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    @Override
    public boolean canBeApplied(final Board board) {
        Objects.requireNonNull(board, "the board cant be null");
        return board.containsPosition(firstPosition) && board.containsPosition(secondPosition);
    }

    @Override
    public void apply(final Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("the move cant be applied");
        board.swapTokens(firstPosition, secondPosition);
    }
    
    @Override
    public Set<Position> getAffectedPositions(Board board) {
        if (!canBeApplied(board))
            throw new BoardDimensionException("invalid move");
        return new HashSet<Position>(Arrays.asList(firstPosition, secondPosition));
    }

    @Override
    public Move reverse() {
        return new Swap(secondPosition, firstPosition);
    }
}
