package test.literatur_system;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import edu.kit.informatik.literatur_system.Utilities;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public class UtilitiesTest {
    private final List<String> firstList = new ArrayList<String>();
    private final List<String> secondList = new ArrayList<String>();
    private final List<String> thirdList = new ArrayList<String>();
    /**
     * TODO add doc
     */
    public UtilitiesTest() {
        firstList.add("one");
        firstList.add("two");
        firstList.add("three");
        
        secondList.add("four");
        secondList.add("five");
        secondList.add("six");
        
        thirdList.add("one");
        thirdList.add("three");
        thirdList.add("six");
    }
    
    @Test
    public void testUnify() {
        Collection<String> result = Utilities.unify(firstList, secondList);
        assertTrue(result.size() == 6);
        assertTrue(result != firstList && result != secondList);
        assertTrue(result.contains("one"));
        assertTrue(result.contains("two"));
        assertTrue(result.contains("three"));
        assertTrue(result.contains("four"));
        assertTrue(result.contains("five"));
        assertTrue(result.contains("six"));
    }
    
    @Test
    public void testIntersectArgs() {
        Collection<String> result = Utilities.intersect(firstList, secondList);
        assertTrue(result.size() == 0);
        
        result = Utilities.intersect(firstList, thirdList);
        assertTrue(result.size() == 2);
        assertTrue(result.contains("one"));
        assertTrue(result.contains("three"));
        assertTrue(result.contains("two") == false);
        
        result = Utilities.intersect(firstList, secondList, thirdList);
        assertTrue(result.size() == 0);
        
        result = Utilities.intersect(firstList, thirdList, firstList);
        assertTrue(result.size() == 2);
        
    }
}
