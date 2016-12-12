package junit.test.kachelung;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

import edu.kit.informatik.kachelung.Board;
import edu.kit.informatik.kachelung.LineType;
import edu.kit.informatik.kachelung.Tile;

public class BoardTest {
    
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
        Board test = new Board();
        assertTrue(test.isEmpty() == true);
        
        test.setTile(1, new Tile(new LineType[] {
                LineType.GREEN, LineType.GREEN, LineType.YELLOW, 
                LineType.NONE, LineType.YELLOW, LineType.NONE }));
        
        assertTrue(test.isEmpty() == false);
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
        
        assertTrue(test.isValid() == true);
        
        test.rotateTileClockwise(1);
        
        assertTrue(test.isValid() == false);
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
    }

    @Test
    public void testToString() {
        Board firstTest = new Board();
        StringBuilder builder = new StringBuilder();
        
        builder.append("------;------;------;").append('\n');
        builder.append("------;------;------;").append('\n');
        builder.append("------;------;------;").append('\n');
        builder.append("------;------;------;");
        String actualResult = firstTest.toString();
        String expectedResult = builder.toString();        
        
        assertThat(actualResult, is(expectedResult));
    }
    
    @Test
    public void testCopy() {
        fail("Not yet implemented");
    }
}
