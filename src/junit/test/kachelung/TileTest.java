package junit.test.kachelung;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.kachelung.LineType;
import edu.kit.informatik.kachelung.Tile;

public class TileTest {
    /**
     * <pre>
     * Empty tile
     * ------
     * </pre>
     */
    private static final Tile EMPTY_TILE = new Tile();
    /**
     * <pre>
     * Example in picture 6
     * RRG-G-
     * </pre>
     */
    private static final Tile K0 = new Tile(new LineType[] {
            LineType.RED, LineType.RED, LineType.GREEN, 
            LineType.NONE, LineType.GREEN, LineType.NONE }); 
    /**
     * <pre>
     * Example in picture 6
     * R-GGR-
     * </pre>
     */
    private static final Tile K1 = new Tile(new LineType[] {
            LineType.RED, LineType.NONE, LineType.GREEN,
            LineType.GREEN, LineType.RED, LineType.NONE });

    public TileTest() {

    }

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
        assertTrue(EMPTY_TILE.isEmpty() == true);
    }

    @Test
    public void testGetLineTypeAtIndex() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetNumberOfColors() {
        //Examples in picture 4
        Tile testSingular = new Tile(new LineType[] {
                LineType.RED, LineType.RED, LineType.NONE,
                LineType.NONE, LineType.NONE, LineType.NONE });

        Tile testTrio =  new Tile(new LineType[] {
                LineType.RED, LineType.YELLOW, LineType.RED,
                LineType.YELLOW, LineType.GREEN, LineType.GREEN });

        Tile testDuo = new Tile(new LineType[] {
                LineType.NONE, LineType.YELLOW, LineType.RED,
                LineType.NONE, LineType.RED, LineType.YELLOW });

        assertTrue(testDuo.getNumberOfColors() == 2);
        assertTrue(testTrio.getNumberOfColors() == 3);     
        assertTrue(testSingular.getNumberOfColors() == 1);        
        assertTrue(EMPTY_TILE.getNumberOfColors() == 0);
        assertTrue(K1.getNumberOfColors() == 2);
        assertTrue(K0.getNumberOfColors() == 2);
    }

    @Test
    public void testIsExactlyEqualTo() {
        assertTrue(EMPTY_TILE.isExactlyEqualTo(EMPTY_TILE) == true);
        assertTrue(K1.isExactlyEqualTo(K1) == true);
        assertTrue(K0.isExactlyEqualTo(K0) == true);

        assertTrue(EMPTY_TILE.isExactlyEqualTo(K1) == false);
        assertTrue(K1.isExactlyEqualTo(K0) == false);
        assertTrue(K0.isExactlyEqualTo(EMPTY_TILE) == false);
    }

    @Test
    public void testCopy() {
        Tile copyEmpty = EMPTY_TILE.copy();
        Tile copyK1 = K1.copy();
        Tile copyK0 = K0.copy();

        assertTrue(copyEmpty != EMPTY_TILE);
        assertTrue(copyEmpty.isExactlyEqualTo(EMPTY_TILE) == true);        
        assertTrue(copyK1 != K1);
        assertTrue(copyK1.isExactlyEqualTo(K1) == true);        
        assertTrue(copyK0 != K0);
        assertTrue(copyK0.isExactlyEqualTo(K0) == true);
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
        String actualResult = null;
        String expectedResult = null;

        actualResult = EMPTY_TILE.toString();
        expectedResult = "------";
        assertThat(actualResult, is(expectedResult));

        actualResult = K0.toString();
        expectedResult = "RRG-G-";        
        assertThat(actualResult, is(expectedResult));

        actualResult = K1.toString();
        expectedResult = "R-GGR-";
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void testFitsTo() {
        assertTrue(K0.fitsTo(K1, 0) == false);
        assertTrue(K0.fitsTo(K1, 1) == true);
        assertTrue(K0.fitsTo(K1, 2) == true);
        assertTrue(K0.fitsTo(K1, 3) == true);
        assertTrue(K0.fitsTo(K1, 4) == true);
        assertTrue(K0.fitsTo(K1, 5) == true);
    }

}
