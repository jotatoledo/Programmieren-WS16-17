package edu.kit.informatik.matchthree;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

/**
 * FIXME add doc
 * @author JoseNote
 *
 */
public class Replace {
    private final Position selectedPosition;
    private final Token newToken;
    
    /**
     * FIXME add doc
     * @param selectedPosition FIXME add doc
     * @param newToken FIXME add doc
     */
    public Replace(final Position selectedPosition, final Token newToken) {
        this.selectedPosition = selectedPosition;
        this.newToken = newToken;
    }
    
    /**
     * Checks if this can be applied to the given board
     * @param board a board
     * @return {@code True} if the this can be executed on the board. {@code False} otherwise
     */
    public boolean canBeApplied(final Board board) {
        return board.containsPosition(selectedPosition);
    }
    
    /**
     * Applies this to the given board
     * @param board a board
     */
    public void apply(final Board board) {
        board.setTokenAt(selectedPosition, newToken);
    }  
}
