package edu.kit.informatik.matchthree;

import java.util.Objects;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

/**
 * Encapsulates the logic of a replacement movement
 * @author JoseNote
 *
 */
public class Replace {
    private final Position position;
    private final Token token;
    
    /**
     * Creates a new instance
     * @param position a position in which a new token will be inserted
     * @param token the token to insert in the given position
     * @throws NullPointerException if the given position if {@code null}
     */
    public Replace(final Position position, final Token token) {
        Objects.requireNonNull(position, "position in null");
        this.position = position;
        this.token = token;
    }
    
    /**
     * Applies this to the given board
     * @param board a board
     * @throws BoardDimensionException if the position isn't contained in the given board
     */
    public void apply(final Board board) {
        if (!board.containsPosition(position))
            throw new BoardDimensionException("invalid position");
        board.setTokenAt(position, token);
    }  
}
