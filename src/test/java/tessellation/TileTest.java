package test.java.tessellation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.shape.Line;
import main.java.tessellation.LineType;
import main.java.tessellation.Tile;

/**
 * Unit test for {@linkplain Tile}
 * @author Jose Toledo Navarro
 * @version 1.00
 */
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

    /**
     * <pre>
     * Example in picture 4
     * RYRYGG
     * </pre>
     */
    private static final Tile TEST_TRIO =  new Tile(new LineType[] {
            LineType.RED, LineType.YELLOW, LineType.RED,
            LineType.YELLOW, LineType.GREEN, LineType.GREEN });
    
    public TileTest() {

    }

    @Test
    public void testTile() {
        Tile firstTest = new Tile();

        assertTrue(firstTest != null);
    }

    @Test
    public void testGetLineTypeAtIndex() {
        assertTrue(EMPTY_TILE.getLineTypeAtIndex(0) == LineType.NONE);
        assertTrue(TEST_TRIO.getLineTypeAtIndex(0) == LineType.RED);
        assertTrue(TEST_TRIO.getLineTypeAtIndex(1) == LineType.YELLOW);
        assertTrue(TEST_TRIO.getLineTypeAtIndex(5) == LineType.GREEN);
    }

    @Test
    public void testGetNumberOfColors() {
        //Examples in picture 4
        Tile testSingular = new Tile(new LineType[] {
                LineType.RED, LineType.RED, LineType.NONE,
                LineType.NONE, LineType.NONE, LineType.NONE });

        Tile testDuo = new Tile(new LineType[] {
                LineType.NONE, LineType.YELLOW, LineType.RED,
                LineType.NONE, LineType.RED, LineType.YELLOW });

        assertTrue(testDuo.getNumberOfColors() == 2);
        assertTrue(TEST_TRIO.getNumberOfColors() == 3);     
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
        //RYRYGG
        Tile copyTrio = TEST_TRIO.copy();
        
        copyTrio.rotateClockwise();
        assertThat(copyTrio.toString(), is("GRYRYG"));
        copyTrio.rotateClockwise();
        copyTrio.rotateClockwise();
        assertThat(copyTrio.toString(), is("YGGRYR"));
        copyTrio.rotateClockwise();
        copyTrio.rotateClockwise();
        copyTrio.rotateClockwise();
        assertThat(copyTrio.toString(), is(TEST_TRIO.toString()));
    }

    @Test
    public void testRotateCounterClockwise() {
        //RYRYGG
        Tile copyTrio = TEST_TRIO.copy();
        
        copyTrio.rotateCounterClockwise();
        assertThat(copyTrio.toString(), is("YRYGGR"));
        copyTrio.rotateCounterClockwise();
        copyTrio.rotateCounterClockwise();
        assertThat(copyTrio.toString(), is("YGGRYR"));
        copyTrio.rotateCounterClockwise();
        copyTrio.rotateCounterClockwise();
        copyTrio.rotateCounterClockwise();
        assertThat(copyTrio.toString(), is(TEST_TRIO.toString()));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(TEST_TRIO.isEmpty() == false);
        assertTrue(K0.isEmpty() == false);
        assertTrue(K1.isEmpty() == false);
        assertTrue(EMPTY_TILE.isEmpty() == true);
    }

    @Test
    public void testIsRotationEqualTo() {
        Tile copyTrio = TEST_TRIO.copy();
        
        copyTrio.rotateClockwise();
        copyTrio.rotateClockwise();
        
        assertTrue(copyTrio.isRotationEqualTo(TEST_TRIO) == true);
        assertTrue(EMPTY_TILE.isRotationEqualTo(EMPTY_TILE) == true);
        assertTrue(TEST_TRIO.isRotationEqualTo(TEST_TRIO) == true);
        assertTrue(TEST_TRIO.isRotationEqualTo(EMPTY_TILE) == false);
        assertTrue(EMPTY_TILE.isRotationEqualTo(TEST_TRIO) == false);
        
        Tile test = new Tile(new LineType[]{LineType.RED, LineType.RED, LineType.GREEN,
                LineType.GREEN, LineType.YELLOW, LineType.YELLOW});
        
        assertTrue(test.isRotationEqualTo(new Tile(new LineType[]{LineType.RED, LineType.GREEN, LineType.GREEN,
                LineType.YELLOW, LineType.YELLOW, LineType.RED})) == true);
    }

    @Test
    public void testCanBeRecoloredTo() {
        //Praktomat example
        Tile test = new Tile(new LineType[] {
                LineType.RED, LineType.NONE, LineType.GREEN,
                LineType.GREEN, LineType.RED, LineType.NONE });
        Tile objective = new Tile(new LineType[] {
                LineType.YELLOW, LineType.NONE, LineType.RED,
                LineType.RED, LineType.YELLOW, LineType.NONE });
        
        assertTrue(test.canBeRecoloredTo(objective) == true);
        test.rotateClockwise();
        assertTrue(test.canBeRecoloredTo(objective) == false);
        
        test = new Tile(new LineType[] {
                LineType.RED, LineType.GREEN, LineType.YELLOW,
                LineType.RED, LineType.GREEN, LineType.YELLOW });
        objective = new Tile(new LineType[] {
                LineType.YELLOW, LineType.GREEN, LineType.RED,
                LineType.YELLOW, LineType.RED, LineType.GREEN });
        
        assertTrue(test.canBeRecoloredTo(objective) == false);
        
        //Every tile can be recolored to itself
        assertTrue(EMPTY_TILE.canBeRecoloredTo(EMPTY_TILE) == true);
        assertTrue(K0.canBeRecoloredTo(K0) == true);
        assertTrue(K1.canBeRecoloredTo(K1) == true);
        assertTrue(TEST_TRIO.canBeRecoloredTo(TEST_TRIO) == true);
        
        //Recoloring requires adding one or more connection lines
        assertTrue(TEST_TRIO.canBeRecoloredTo(EMPTY_TILE) == false);
        assertTrue(TEST_TRIO.canBeRecoloredTo(K0) == false);
        assertTrue(TEST_TRIO.canBeRecoloredTo(K1) == false);
        
        
    }

    @Test
    public void testDominates() {
        //The tiles do not dominate themselves
        assertTrue(EMPTY_TILE.dominates(EMPTY_TILE) == false);
        assertTrue(K0.dominates(K0) == false);
        assertTrue(K1.dominates(K1) == false);
        assertTrue(TEST_TRIO.dominates(TEST_TRIO) == false);
        
        //Every tile except the empty one dominates the empty one
        assertTrue(K0.dominates(EMPTY_TILE) == true);
        assertTrue(K1.dominates(EMPTY_TILE) == true);
        assertTrue(TEST_TRIO.dominates(EMPTY_TILE) == true);
        
        Tile test = new Tile(new LineType[] {
                LineType.GREEN, LineType.NONE, LineType.RED,
                LineType.RED, LineType.NONE, LineType.GREEN });
        
        Tile objective = new Tile(new LineType[] {
                LineType.NONE, LineType.NONE, LineType.GREEN,
                LineType.GREEN, LineType.NONE, LineType.NONE });
        
        assertTrue(test.dominates(objective) == false);
    }

    @Test
    public void testHasSameColorsAs() {
        assertTrue(EMPTY_TILE.hasSameColorsAs(EMPTY_TILE) == true);
        assertTrue(K0.hasSameColorsAs(K0) == true);
        assertTrue(K1.hasSameColorsAs(K1) == true);
        assertTrue(TEST_TRIO.hasSameColorsAs(TEST_TRIO) == true);
        
        assertTrue(EMPTY_TILE.hasSameColorsAs(K0) == false);
        assertTrue(K0.hasSameColorsAs(EMPTY_TILE) == false);
        assertTrue(K1.hasSameColorsAs(K0) == true);
        assertTrue(K0.hasSameColorsAs(K0) == true);
        assertTrue(TEST_TRIO.hasSameColorsAs(EMPTY_TILE) == false);
        assertTrue(TEST_TRIO.hasSameColorsAs(K0) == false);
        assertTrue(TEST_TRIO.hasSameColorsAs(K1) == false);
    }

    @Test
    public void testToString() {
        assertThat(EMPTY_TILE.toString(), is("------"));  
        assertThat(K0.toString(), is("RRG-G-"));
        assertThat(K1.toString(), is("R-GGR-"));
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
