package test.tessellation;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import edu.kit.informatik.tessellation.BoardPosition;

/**
 * Unit test for {@linkplain BoardPosition}
 * @author JoseNote
 * @version 1.00
 */
public class BoardPositionTest {
    private final int elementsCol;
    private final int elementsRow;
    
    public BoardPositionTest() {
        elementsCol = 3;
        elementsRow = 4;
    }

    @Test
    public void testCalculateBoardPosition() {
        assertTrue(BoardPosition.calculatePosition(0, elementsCol, elementsRow) == BoardPosition.UPPER_LEFT_CORNER);
        assertTrue(BoardPosition.calculatePosition(2, elementsCol, elementsRow) == BoardPosition.DOWN_LEFT_CORNER);
        assertTrue(BoardPosition.calculatePosition(9, elementsCol, elementsRow) == BoardPosition.UPPER_RIGHT_CORNER);
        assertTrue(BoardPosition.calculatePosition(11, elementsCol, elementsRow) == BoardPosition.DOWN_RIGH_CORNER);
        
        assertTrue(BoardPosition.calculatePosition(4, elementsCol, elementsRow) == BoardPosition.INTERNAL);
        assertTrue(BoardPosition.calculatePosition(7, elementsCol, elementsRow) == BoardPosition.INTERNAL);
        
        assertTrue(BoardPosition.calculatePosition(3, elementsCol, elementsRow) == BoardPosition.UPPER_SIDE);
        assertTrue(BoardPosition.calculatePosition(6, elementsCol, elementsRow) == BoardPosition.UPPER_SIDE);
        
        assertTrue(BoardPosition.calculatePosition(5, elementsCol, elementsRow) == BoardPosition.DOWN_SIDE);
        assertTrue(BoardPosition.calculatePosition(8, elementsCol, elementsRow) == BoardPosition.DOWN_SIDE);
        
        assertTrue(BoardPosition.calculatePosition(1, elementsCol, elementsRow) == BoardPosition.LEFT_SIDE);
        
        assertTrue(BoardPosition.calculatePosition(10, elementsCol, elementsRow) == BoardPosition.RIGHT_SIDE);
    }

    @Test
    public void testToString() {
        assertThat(BoardPosition.UPPER_LEFT_CORNER.toString(), is("UPPER_LEFT_CORNER"));
    }
}
