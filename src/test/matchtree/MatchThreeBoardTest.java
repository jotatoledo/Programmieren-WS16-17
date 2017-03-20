package test.matchtree;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

public class MatchThreeBoardTest {
    private final Board setIntInt;
    private final Board setString;
    
    public MatchThreeBoardTest() {
        setIntInt = new MatchThreeBoard(Token.set("AB"), 2, 3);
        setString = new MatchThreeBoard(Token.set("AY+*"), "A AA;++  ; *A*;Y  Y");
    }
    
    @Test
    public void testMatchThreeBoardSetOfTokenIntInt() {
        assertTrue(setIntInt != null);
    }

    @Test
    public void testMatchThreeBoardSetOfTokenString() {
        assertTrue(setString != null);
    }

    @Test
    public void testGetAllValidTokens() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetColumnCount() {
        assertTrue(setIntInt.getColumnCount() == 2);
        assertTrue(setString.getColumnCount() == 4);
    }

    @Test
    public void testGetRowCount() {
        assertTrue(setIntInt.getRowCount() == 3);
        assertTrue(setString.getRowCount() == 4);
    }

    @Test
    public void testGetTokenAt() {
        assertTrue(setIntInt.getTokenAt(Position.at(0, 0)) == null);
        assertTrue(setString.getTokenAt(Position.at(0, 0)).equals(new Token("A")));
        
        assertTrue(setString.getTokenAt(Position.at(1, 3)) == null);
    }

    @Test
    public void testSetTokenAt() {
        fail("Not yet implemented");
    }

    @Test
    public void testContainsPosition() {
        assertTrue(!setIntInt.containsPosition(Position.at(10, 10)));
        assertTrue(setIntInt.containsPosition(Position.at(0, 0)));
        assertTrue(!setIntInt.containsPosition(Position.at(2, 3)));
        assertTrue(setIntInt.containsPosition(Position.at(1, 2)));
    }

    @Test
    public void testMoveTokensToBottom() {
        Board t = new MatchThreeBoard(Token.set("AY+*"), "A AA;++  ; *A*;Y  Y");
        Set<Position> aff = t.moveTokensToBottom();
        assertEquals("    ;A  A;++A*;Y*AY", t.toTokenString());
        t = new MatchThreeBoard(Token.set("AY+*"), "A   ;    ;    ;    ");
        aff = t.moveTokensToBottom();
        assertEquals("    ;    ;    ;A   ", t.toTokenString());
    }

    @Test
    public void testSwapTokens() {
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveTokensAt() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetFillingStrategy() {
        fail("Not yet implemented");
    }

    @Test
    public void testFillWithTokens() {
        fail("Not yet implemented");
    }

    @Test
    public void testToTokenString() {
        assertEquals(setIntInt.toTokenString(), "  ;  ;  ");
        assertEquals(setString.toTokenString(), "A AA;++  ; *A*;Y  Y");
    }

}
