package test.matchtree;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.RandomStrategy;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

public class MatchThreeBoardTest {
    private final Board setIntInt;
    private final Board setString;
    private final Board e22;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public MatchThreeBoardTest() {
        setIntInt = new MatchThreeBoard(Token.set("AB"), 2, 3);
        setString = new MatchThreeBoard(Token.set("AY+*"), "A AA;++  ; *A*;Y  Y");
        e22 = new MatchThreeBoard(Token.set("AXO*"), "O*O;***;O*O;O*O");
    }
    
    @Test
    public void testMatchThreeBoardSetOfTokenIntInt() {
        assertTrue(setIntInt != null);
    }

    @Test
    public void testMatchThreeBoardSetOfTokenString() {
        assertTrue(setString != null);
        assertTrue(e22 != null);
    }

    @Test
    public void testGetAllValidTokens() {
        assertEquals(Token.set("AB"), setIntInt.getAllValidTokens());
        assertEquals(Token.set("AY+*"), setString.getAllValidTokens());
        assertEquals(Token.set("AXO*"), e22.getAllValidTokens());
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
        Position p;
        Board b = new MatchThreeBoard(e22.getAllValidTokens(), e22.toTokenString());
        p = Position.at(0, 0);
        b.setTokenAt(p, null);
        assertEquals(null, b.getTokenAt(p));
        p = Position.at(0, 1);
        b.setTokenAt(p, new Token("A"));
        assertEquals(new Token("A"), b.getTokenAt(p));
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
        Board b = new MatchThreeBoard(e22.getAllValidTokens(), e22.toTokenString());
        Position p1 = Position.at(0, 0);
        Position p2 = Position.at(0, 1);
        assertEquals(new Token("O"), b.getTokenAt(p1));
        assertEquals(new Token("*"), b.getTokenAt(p2));
        b.swapTokens(p1, p2);
        assertEquals(new Token("O"), b.getTokenAt(p2));
        assertEquals(new Token("*"), b.getTokenAt(p1));
        b.swapTokens(p1, p2);
        assertEquals(new Token("O"), b.getTokenAt(p1));
        assertEquals(new Token("*"), b.getTokenAt(p2));
    }

    @Test
    public void testRemoveTokensAt() {
        Board b = new MatchThreeBoard(e22.getAllValidTokens(), e22.toTokenString());
        Set<Position> positionToRemove = new HashSet<Position>(Arrays.asList(Position.at(0, 0), 
                Position.at(1, 1), Position.at(2, 2)));
        b.removeTokensAt(positionToRemove);
        positionToRemove.forEach(p -> assertTrue(b.getTokenAt(p) == null));
    }

    @Test
    public void testSetFillingStrategy() throws NullPointerException {
        Board b = new MatchThreeBoard(setIntInt.getAllValidTokens(), setIntInt.toTokenString());
        b.setFillingStrategy(new RandomStrategy());
        thrown.expect(NullPointerException.class);
        e22.setFillingStrategy(null);
    }

    @Test
    public void testFillWithTokens() {
        Board b = new MatchThreeBoard(setIntInt.getAllValidTokens(), setIntInt.toTokenString());
        b.setFillingStrategy(new RandomStrategy());
        b.fillWithTokens();
        for (int row = 0; row < b.getRowCount(); row++) {
            for (int col = 0; col < b.getColumnCount(); col++) {
                if (b.getTokenAt(Position.at(col, row)) == null)
                    fail("at least one position isnt filled");
            }
        }
    }

    @Test
    public void testToTokenString() {
        assertEquals(setIntInt.toTokenString(), "  ;  ;  ");
        assertEquals(setString.toTokenString(), "A AA;++  ; *A*;Y  Y");
    }

}
