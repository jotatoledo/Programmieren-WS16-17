package edu.kit.informatik.matchthree;

import java.util.Set;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

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
    
    public boolean canBeApplied(final Board board) {
        return board.containsPosition(selectedPosition);
    }
    
    public void apply(final Board board) {
        board.setTokenAt(selectedPosition, newToken);
    }
    
    public Set<Position> getAffectedPositions(final Board board) {
        // TODO Auto-generated method stub
        return null;
    }    
}
