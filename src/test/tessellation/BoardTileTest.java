package test.tessellation;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import edu.kit.informatik.tessellation.BoardPosition;
import edu.kit.informatik.tessellation.BoardTile;
import edu.kit.informatik.tessellation.LineType;

public class BoardTileTest {

    private static final BoardTile EMPTY_TILE = new BoardTile(BoardPosition.INTERNAL); 
    private static final BoardTile TRIO_TILE = new BoardTile(BoardPosition.UPPER_LEFT_CORNER);
    
    public BoardTileTest() {
        TRIO_TILE.setLineTypes(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW });
    }
    
    @Test
    public void testToString() {
        assertThat(EMPTY_TILE.toString(), is("INTERNAL\n------"));
    }

    @Test
    public void testBoardTile() {
        BoardTile test = new BoardTile(BoardPosition.UPPER_LEFT_CORNER);
        
        assertTrue(test != null);
    }

    @Test
    public void testGetTile() {
        assertTrue(EMPTY_TILE.getTile() != null);
        assertThat(EMPTY_TILE.getTile().toString(), is("------"));
        
        assertTrue(TRIO_TILE.getTile() != null);
        assertThat(TRIO_TILE.getTile().toString(), is("RGRGYY"));
    }

    @Test
    public void testSetTile() {
        BoardTile test = new BoardTile(BoardPosition.INTERNAL);
        
        test.setLineTypes(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW });
        assertTrue(test.getTile() != null);
        assertThat(test.toString(), is("INTERNAL\nRGRGYY"));
    }

    @Test
    public void testGetPosition() {
        assertTrue(EMPTY_TILE.getPosition() != null);
        assertTrue(EMPTY_TILE.getPosition() == BoardPosition.INTERNAL);
    }

    @Test
    public void testSuperToString() {
        assertThat(EMPTY_TILE.superToString(), is("------"));
    }

}
