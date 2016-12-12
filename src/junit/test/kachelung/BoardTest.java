package junit.test.kachelung;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

import edu.kit.informatik.kachelung.Board;
import edu.kit.informatik.kachelung.LineType;
import edu.kit.informatik.kachelung.Tile;

public class BoardTest {
    private static final Board EMPTY_BOARD = new Board(); //Empty board
    private static final Board VALID_BOARD = new Board(); //Example in picture 9
    private static final Board INVALID_BOARD = new Board(); //Example in picture 10
    
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

        builder.append("------;------;------;").append("\n");
        builder.append("------;------;------;").append("\n");
        builder.append("------;------;------;").append("\n");
        builder.append("------;------;------;");
        assertTrue(firstTest != null);
        assertTrue(firstTest.isEmpty() == true);
        assertThat(firstTest.toString(), is(builder.toString()));
    }

    @Test
    public void testGetTile() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetTile() {
        Board test = new Board();
        StringBuilder builder = new StringBuilder();
        String actualResult;
        String expectedResult;

        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));

        builder.append("------;GGY-Y-;------;").append('\n');
        builder.append("------;------;------;").append('\n');
        builder.append("------;------;------;").append('\n');
        builder.append("------;------;------;");
        actualResult = test.toString();
        expectedResult = builder.toString();
        assertThat(actualResult, is(expectedResult));

        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));

        builder.setLength(0);
        builder.append("------;GGY-Y-;------;").append('\n');
        builder.append("------;RGRGYY;------;").append('\n');
        builder.append("------;------;------;").append('\n');
        builder.append("------;------;------;");        
        actualResult = test.toString();
        expectedResult = builder.toString();        
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void testRemoveTile() {
        fail("Not yet implemented");
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

        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));

        test.rotateTileClockwise(1);

        StringBuilder builder = new StringBuilder();

        builder.append("------;-GGY-Y;------;").append('\n');
        builder.append("------;RGRGYY;------;").append('\n');
        builder.append("------;------;------;").append('\n');
        builder.append("------;------;------;");
        String actualResult = test.toString();
        String expectedResult = builder.toString();   

        assertThat(actualResult, is(expectedResult));
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
        builder.append("------;GY-Y-G;------;").append('\n');
        builder.append("------;RGRGYY;------;").append('\n');
        builder.append("------;------;------;").append('\n');
        builder.append("------;------;------;");
        String actualResult = test.toString();
        String expectedResult = builder.toString();   
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void testGetNumberOfColors() {
        fail("Not yet implemented");
    }

    @Test
    public void testIsValid() {
        Board test = new Board(); 

        assertTrue(test.isValid() == true);

        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));

        assertTrue(test.isValid() == true); //Example in code snippet 3
        test.rotateTileClockwise(1);
        assertTrue(test.isValid() == false); //Example in code snippet 3
        assertTrue(VALID_BOARD.isValid() == true);
        assertTrue(INVALID_BOARD.isValid() == false);
    }

    @Test
    public void testGetConnectedPathColor() {
        Board test = new Board();
        LineType actualResult = null;
        LineType expectedResult = null;

        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        test.setTile(4, new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.RED, 
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));

        actualResult = test.getConnectedPathColor(new int[]{1, 4});
        expectedResult = LineType.YELLOW;
        assertTrue(actualResult == expectedResult);

        test.rotateTileClockwise(1);

        actualResult = test.getConnectedPathColor(new int[] {1, 4});
        expectedResult = LineType.NONE;
        assertTrue(actualResult == expectedResult);
        
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {0, 3}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {1, 4}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {1, 2, 4}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {4, 5, 7}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6, 4, 5, 7}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {8, 10, 11}) == LineType.NONE);
        assertTrue(EMPTY_BOARD.getConnectedPathColor(new int[] {8, 10, 11, 8}) == LineType.NONE);
        
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {0, 3}) == LineType.NONE);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {1, 4}) == LineType.YELLOW);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {1, 2, 4}) == LineType.NONE);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7}) == LineType.GREEN);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6, 4, 5, 7}) == LineType.GREEN);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {8, 10, 11}) == LineType.YELLOW);
        assertTrue(VALID_BOARD.getConnectedPathColor(new int[] {8, 10, 11, 8}) == LineType.YELLOW);
        
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {0, 3}) == LineType.NONE);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {1, 4}) == LineType.NONE);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {1, 2, 4}) == LineType.NONE);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7}) == LineType.GREEN);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {4, 5, 7, 6, 4, 5, 7}) == LineType.NONE);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {8, 10, 11}) == LineType.YELLOW);
        assertTrue(INVALID_BOARD.getConnectedPathColor(new int[] {8, 10, 11, 8}) == LineType.NONE);
    }

    @Test
    public void testToString() {
        String actualResult = null;
        String expectedResult = null;
        StringBuilder builder = new StringBuilder();

        builder.append("------;------;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;\n");
        builder.append("------;------;------;");
        actualResult = EMPTY_BOARD.toString();
        expectedResult = builder.toString();
        
        assertThat(actualResult, is(expectedResult));
        
        builder.setLength(0);
        builder.append("------;-GGY-Y;RR----;\n");
        builder.append("------;RGRGYY;GG----;\n");
        builder.append("Y--YGG;G--RGR;-YY---;\n");
        builder.append("------;---YY-;YY----;");
        actualResult = INVALID_BOARD.toString();
        expectedResult = builder.toString();
        
        assertThat(actualResult, is(expectedResult));
        
        builder.setLength(0);
        builder.append("------;GGY-Y-;----RR;\n");
        builder.append("------;RGRGYY;GG----;\n");
        builder.append("--YGGY;G--RGR;-YY---;\n");
        builder.append("------;---YY-;Y----Y;");
        actualResult = VALID_BOARD.toString();
        expectedResult = builder.toString();
        
        assertThat(actualResult, is(expectedResult));
    }
}
