package test.java.tessellation;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import main.java.tessellation.PositionInBoard;

/**
 * Unit test for {@linkplain PositionInBoard}
 * @author JoseNote
 * @version 1.00
 */
public class PositionInBoardTest {
    public PositionInBoardTest() {
        
    }

    @Test
    public void testCalculateBoardPosition() {
        assertTrue(PositionInBoard.calculateBoardPosition(0) == PositionInBoard.UPPER_LEFT_CORNER);
        assertTrue(PositionInBoard.calculateBoardPosition(2) == PositionInBoard.DOWN_LEFT_CORNER);
        assertTrue(PositionInBoard.calculateBoardPosition(9) == PositionInBoard.UPPER_RIGHT_CORNER);
        assertTrue(PositionInBoard.calculateBoardPosition(11) == PositionInBoard.DOWN_RIGH_CORNER);
        
        assertTrue(PositionInBoard.calculateBoardPosition(4) == PositionInBoard.INTERNAL);
        assertTrue(PositionInBoard.calculateBoardPosition(7) == PositionInBoard.INTERNAL);
        
        assertTrue(PositionInBoard.calculateBoardPosition(3) == PositionInBoard.UPPER_SIDE);
        assertTrue(PositionInBoard.calculateBoardPosition(6) == PositionInBoard.UPPER_SIDE);
        
        assertTrue(PositionInBoard.calculateBoardPosition(5) == PositionInBoard.DOWN_SIDE);
        assertTrue(PositionInBoard.calculateBoardPosition(8) == PositionInBoard.DOWN_SIDE);
        
        assertTrue(PositionInBoard.calculateBoardPosition(1) == PositionInBoard.LEFT_SIDE);
        
        assertTrue(PositionInBoard.calculateBoardPosition(10) == PositionInBoard.RIGHT_SIDE);
    }

    @Test
    public void testToString() {
        assertThat(PositionInBoard.UPPER_LEFT_CORNER.toString(), is("UPPER_LEFT_CORNER"));
    }
}
