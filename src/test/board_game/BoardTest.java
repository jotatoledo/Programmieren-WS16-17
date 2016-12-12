package test.board_game;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.board_game.Board;
import main.java.board_game.LineType;
import main.java.board_game.Tile;

import static org.hamcrest.CoreMatchers.*;

public class BoardTest {
    /**
     * <pre>
     * Empty board
     * 
     * ------;------;------;
     * ------;------;------;
     * ------;------;------;
     * ------;------;------;
     * </pre>
     */
    private static final Board EMPTY_BOARD = new Board();
    /**
     * <pre>
     * Example in picture 9
     * 
     * ------;GGY-Y-;----RR;
     * ------;RGRGYY;GG----;
     * --YGGY;G--RGR;-YY---;
     * ------;---YY-;Y----Y;
     * </pre>
     */
    private static final Board VALID_BOARD = new Board();
    /**
     * <pre>
     * Example in picture 10
     * 
     * ------;-GGY-Y;RR----;
     * ------;RGRGYY;GG----;
     * Y--YGG;G--RGR;-YY---;
     * ------;---YY-;YY----;
     * </pre> 
     */
    private static final Board INVALID_BOARD = new Board();
    
    /**
     * Used to populate the general test cases
     */
    public BoardTest() {
        VALID_BOARD.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        VALID_BOARD.setTile(2, new Tile(new LineType[] {
                LineType.NONE, LineType.NONE, LineType.NONE, 
                LineType.NONE, LineType.RED, LineType.RED }));
        VALID_BOARD.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));
        VALID_BOARD.setTile(5, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.NONE, 
                LineType.NONE, LineType.NONE, LineType.NONE }));
        VALID_BOARD.setTile(6, new Tile(new LineType[] {
                LineType.NONE, LineType.NONE, LineType.YELLOW, 
                LineType.GREEN, LineType.GREEN, LineType.YELLOW }));
        VALID_BOARD.setTile(7, new Tile(new LineType[] {
                LineType.GREEN, LineType.NONE, LineType.NONE, 
                LineType.RED, LineType.GREEN, LineType.RED }));
        VALID_BOARD.setTile(8, new Tile(new LineType[] {
                LineType.NONE, LineType.YELLOW, LineType.YELLOW, 
                LineType.NONE, LineType.NONE, LineType.NONE }));
        VALID_BOARD.setTile(10, new Tile(new LineType[] {
                LineType.NONE, LineType.NONE, LineType.NONE, 
                LineType.YELLOW, LineType.YELLOW, LineType.NONE }));
        VALID_BOARD.setTile(11, new Tile(new LineType[] {
                LineType.YELLOW, LineType.NONE, LineType.NONE, 
                LineType.NONE, LineType.NONE, LineType.YELLOW }));
        
        INVALID_BOARD.setTile(1, new Tile(new LineType[] {
                LineType.NONE, LineType.GREEN, LineType.GREEN, 
                LineType.YELLOW, LineType.NONE, LineType.YELLOW }));        
        INVALID_BOARD.setTile(2, new Tile(new LineType[] {
                LineType.RED, LineType.RED, LineType.NONE, 
                LineType.NONE, LineType.NONE, LineType.NONE }));
        INVALID_BOARD.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));
        INVALID_BOARD.setTile(5, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.NONE, 
                LineType.NONE, LineType.NONE, LineType.NONE }));
        INVALID_BOARD.setTile(6, new Tile(new LineType[] {
                LineType.YELLOW, LineType.NONE, LineType.NONE, 
                LineType.YELLOW, LineType.GREEN, LineType.GREEN }));
        INVALID_BOARD.setTile(7, new Tile(new LineType[] {
                LineType.GREEN, LineType.NONE, LineType.NONE, 
                LineType.RED, LineType.GREEN, LineType.RED }));
        INVALID_BOARD.setTile(8, new Tile(new LineType[] {
                LineType.NONE, LineType.YELLOW, LineType.YELLOW, 
                LineType.NONE, LineType.NONE, LineType.NONE }));
        INVALID_BOARD.setTile(10, new Tile(new LineType[] {
                LineType.NONE, LineType.NONE, LineType.NONE, 
                LineType.YELLOW, LineType.YELLOW, LineType.NONE }));
        INVALID_BOARD.setTile(11, new Tile(new LineType[] {
                LineType.YELLOW, LineType.YELLOW, LineType.NONE, 
                LineType.NONE, LineType.NONE, LineType.NONE }));
    }
    
    @Test
    public void testBoard() {
        Board firstTest = new Board();
        StringBuilder builder = new StringBuilder();

        builder.append("------;------;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;");
        assertTrue(firstTest != null);
        assertTrue(firstTest.isEmpty() == true);
        assertThat(firstTest.toString(), is(builder.toString()));
    }

    @Test
    public void testGetTile() {
        Tile actualResult = null;
        Tile expectedResult = null;
        
        actualResult = VALID_BOARD.getTile(1);
        expectedResult = new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE });
        
        assertThat(actualResult.toString(), is(expectedResult.toString()));
        
        actualResult = EMPTY_BOARD.getTile(5);
        expectedResult = new Tile(new LineType[] {
                LineType.NONE, LineType.NONE, LineType.NONE, 
                LineType.NONE, LineType.NONE, LineType.NONE });
        assertThat(actualResult.toString(), is(expectedResult.toString()));
    }

    @Test
    public void testSetTile() {
        Board test = new Board();
        StringBuilder builder = new StringBuilder();

        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));

        builder.append("------;GGY-Y-;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;");
        assertThat(test.toString(), is(builder.toString()));

        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));
        builder.setLength(0);
        builder.append("------;GGY-Y-;------;\n");
        builder.append("------;RGRGYY;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;");  
        assertThat(test.toString(), is(builder.toString()));
    }

    @Test
    public void testRemoveTile() {
        Board test = new Board(); 
        StringBuilder builder = new StringBuilder();
        
        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));
        
        builder.append("------;GGY-Y-;------;\n");
        builder.append("------;RGRGYY;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;");   
        assertThat(test.toString(), is(builder.toString()));
        
        test.removeTile(4);
        builder.setLength(0);
        builder.append("------;GGY-Y-;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;");
        assertThat(test.toString(), is(builder.toString()));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(EMPTY_BOARD.isEmpty() == true);
        assertTrue(INVALID_BOARD.isEmpty() == false);
        assertTrue(VALID_BOARD.isEmpty() == false);
    }

    @Test
    public void testRotateTileClockwise() {
        Board test = new Board();
        StringBuilder builder = new StringBuilder();

        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));
        test.rotateTileClockwise(1);  
        builder.append("------;-GGY-Y;------;\n");
        builder.append("------;RGRGYY;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;");

        assertThat(test.toString(), is(builder.toString()));
    }

    @Test
    public void testRotateTileCounterClockwise() {
        Board test = new Board();
        StringBuilder builder = new StringBuilder();

        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));

        test.rotateTileCounterClockwise(1);
        builder.append("------;GY-Y-G;------;\n");
        builder.append("------;RGRGYY;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;");
        
        assertThat(test.toString(), is(builder.toString()));
    }

    @Test
    public void testGetNumberOfColors() {
        Board test = new Board(); 
        
        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));
        
        assertTrue(test.getNumberOfColors() == 3);
        test.removeTile(4);
        assertTrue(test.getNumberOfColors() == 2);
        assertTrue(EMPTY_BOARD.getNumberOfColors() == 0);
        assertTrue(INVALID_BOARD.getNumberOfColors() == 3);
        assertTrue(INVALID_BOARD.getNumberOfColors() == 3);
    }

    @Test
    public void testIsValid() {
        Board test = new Board(); 
        
        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));

        assertTrue(test.isValid() == true); //Example in code snippet 3
        test.rotateTileClockwise(1);
        assertTrue(test.isValid() == false); //Example in code snippet 3
        assertTrue(EMPTY_BOARD.isValid() == true);
        assertTrue(VALID_BOARD.isValid() == true);
        assertTrue(INVALID_BOARD.isValid() == false);
    }

    @Test
    public void testGetConnectedPathColor() {
        Board test = new Board();

        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));
        
        assertTrue(test.getConnectedPathColor(new int[]{1, 4}) == LineType.YELLOW);
        test.rotateTileClockwise(1);
        assertTrue(test.getConnectedPathColor(new int[] {1, 4}) == LineType.NONE);
        
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {0, 3}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {1, 4}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {1, 2, 4}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {4, 5, 7}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6, 4}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6, 4, 5, 7}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {8, 10, 11}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {8, 10, 11, 8}) == LineType.NONE);
        
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {0, 3}) == LineType.NONE);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {1, 4}) == LineType.YELLOW);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {1, 2, 4}) == LineType.NONE);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7}) == LineType.GREEN);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6}) == LineType.GREEN);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6, 4}) == LineType.GREEN);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6, 4, 5, 7}) == LineType.GREEN);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {8, 10, 11}) == LineType.YELLOW);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {8, 10, 11, 8}) == LineType.YELLOW);
        
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {0, 3}) == LineType.NONE);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {1, 4}) == LineType.NONE);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {1, 2, 4}) == LineType.NONE);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7}) == LineType.GREEN);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6}) == LineType.NONE);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6, 4}) == LineType.NONE);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6, 4, 5, 7}) == LineType.NONE);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {8, 10, 11}) == LineType.YELLOW);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {8, 10, 11, 8}) == LineType.NONE);
    }

    @Test
    public void testToString() {
        StringBuilder builder  = new StringBuilder();

        builder.append("------;------;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;");
        
        assertThat(EMPTY_BOARD.toString(), is(builder.toString()));
        
        builder.setLength(0);
        builder.append("------;-GGY-Y;RR----;\n");
        builder.append("------;RGRGYY;GG----;\n");
        builder.append("Y--YGG;G--RGR;-YY---;\n");
        builder.append("------;---YY-;YY----;");
        
        assertThat(INVALID_BOARD.toString(), is(builder.toString()));
        
        builder.setLength(0);
        builder.append("------;GGY-Y-;----RR;\n");
        builder.append("------;RGRGYY;GG----;\n");
        builder.append("--YGGY;G--RGR;-YY---;\n");
        builder.append("------;---YY-;Y----Y;");
        
        assertThat(VALID_BOARD.toString(), is(builder.toString()));
    }
}
