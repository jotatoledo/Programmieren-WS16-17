package junit.test.kachelung;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.kachelung.LineType;
import edu.kit.informatik.kachelung.Tile;

public class TileTest {
    @Test
    public void testSetLineTypes() {
        fail("Not yet implemented");
    }

    @Test
    public void testTileLineTypeArray() {
        fail("Not yet implemented");
    }

    @Test
    public void testTile() {
        Tile firstTest = new Tile();

        assertTrue(firstTest != null);
        assertTrue(firstTest.isEmpty() == true);
    }

    @Test
    public void testGetLineTypeAtIndex() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetNumberOfColors() {
        fail("Not yet implemented");
    }

    @Test
    public void testIsExactlyEqualTo() {
        fail("Not yet implemented");
    }

    @Test
    public void testCopy() {
        fail("Not yet implemented");
    }

    @Test
    public void testRotateClockwise() {
        fail("Not yet implemented");
    }

    @Test
    public void testRotateCounterClockwise() {
        fail("Not yet implemented");
    }

    @Test
    public void testIsEmpty() {
        Tile firstTest = new Tile(new LineType[] {LineType.RED, LineType.RED, LineType.GREEN, 
                LineType.NONE, LineType.GREEN, LineType.NONE });
        Tile secondTest = new Tile();
        
        assertTrue(firstTest.isEmpty() == false);
        assertTrue(secondTest.isEmpty() == true);
    }

    @Test
    public void testIsRotationEqualTo() {
        fail("Not yet implemented");
    }

    @Test
    public void testCanBeRecoloredTo() {
        fail("Not yet implemented");
    }

    @Test
    public void testDominates() {
        fail("Not yet implemented");
    }

    @Test
    public void testHasSameColorsAs() {
        fail("Not yet implemented");
    }

    @Test
    public void testToString() {
        Tile firstTest = new Tile(new LineType[] {LineType.RED, LineType.RED, LineType.GREEN, 
                LineType.NONE, LineType.GREEN, LineType.NONE });
        String actualResult = firstTest.toString();
        String expectedResult = "RRG-G-";
        
        assertThat(actualResult, is(expectedResult));
        
        Tile secondTest = new Tile(new LineType[] {LineType.RED, LineType.NONE, LineType.GREEN,
                LineType.GREEN, LineType.RED, LineType.NONE });
        
        actualResult = secondTest.toString();
        expectedResult = "R-GGR-";
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void testFitsTo() {
        Tile firstElement = new Tile(new LineType[] {LineType.RED, LineType.RED, LineType.GREEN, 
                LineType.NONE, LineType.GREEN, LineType.NONE });
        Tile secondElement = new Tile(new LineType[] {LineType.RED, LineType.NONE, LineType.GREEN,
                LineType.GREEN, LineType.RED, LineType.NONE });

        assertTrue(firstElement.fitsTo(secondElement, 0) == false);
        assertTrue(firstElement.fitsTo(secondElement, 1) == true);
        assertTrue(firstElement.fitsTo(secondElement, 2) == true);
        assertTrue(firstElement.fitsTo(secondElement, 5) == true);
    }

}
