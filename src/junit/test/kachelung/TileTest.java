package junit.test.kachelung;

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
        fail("Not yet implemented");
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
        fail("Not yet implemented");
    }

    @Test
    public void testFitsTo() {
        Tile firstElement = new Tile(new LineType[] { LineType.RED, LineType.RED, LineType.GREEN, LineType.NONE,
                LineType.GREEN, LineType.NONE });
        Tile secondElement = new Tile(new LineType[] { LineType.RED, LineType.NONE, LineType.GREEN, LineType.GREEN,
                LineType.RED, LineType.NONE });

        assertTrue(firstElement.fitsTo(secondElement, 0) == false);
        assertTrue(firstElement.fitsTo(secondElement, 1) == true);
        assertTrue(firstElement.fitsTo(secondElement, 2) == true);
    }

}
