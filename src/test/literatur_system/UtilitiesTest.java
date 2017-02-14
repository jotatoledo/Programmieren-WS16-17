package test.literatur_system;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
    private final List<String> fourthList = new ArrayList<String>();
    /**
     * TODO add doc
     */
    public UtilitiesTest() {
        firstList.add("one");
        firstList.add("two");
        firstList.add("three");
        firstList.add("1");
        firstList.add("2");
        firstList.add("3");
        
        secondList.add("four");
        secondList.add("five");
        secondList.add("six");
        secondList.add("4");
        secondList.add("5");
        secondList.add("6");
        
        thirdList.add("one");
        thirdList.add("three");
        thirdList.add("six");
        thirdList.add("1");
        thirdList.add("3");
        thirdList.add("6");
        
        
    }
    
    @Test
    public void test1Unify() throws InterruptedException {
        Thread.sleep(300L);
        Collection<String> result = Utilities.unify(firstList, secondList);
        assertTrue(result.size() == 12);
        assertTrue(result != firstList && result != secondList);
        assertTrue(result.contains("one"));
        assertTrue(result.contains("two"));
        assertTrue(result.contains("three"));
        assertTrue(result.contains("four"));
        assertTrue(result.contains("five"));
        assertTrue(result.contains("six"));
    }
    
    @Test
    public void testIntersectArgs() throws InterruptedException {
        Thread.sleep(300L);
        Collection<String> result = Utilities.intersectArgs(firstList, thirdList);
        
        assertTrue(result.size() == 4);
        assertTrue(result.contains("one"));
        assertTrue(result.contains("three"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("3"));
        assertTrue(result.contains("two") == false);
        assertTrue(result.contains("2") == false);
        
        //Intersect collections with no common elements
        result = Utilities.intersectArgs(firstList, secondList);
        assertTrue(result.size() == 0);
        
        //Intersect multiple collection with empty collection
        result = Utilities.intersectArgs(firstList, secondList, fourthList);
        assertTrue(result.size() == 0);
        
        //Intersect empty collection with no other
        result = Utilities.intersectArgs(fourthList);
        assertTrue(result.size() == 0);
    }
    
    @Test
    public void testIntersectCollection() throws InterruptedException {
        Thread.sleep(300L);
        Collection<String> result = Utilities.intersectCollection(Arrays.asList(firstList, thirdList));
        
        assertTrue(result.size() == 4);
        assertTrue(result.contains("one"));
        assertTrue(result.contains("three"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("3"));
        assertTrue(result.contains("two") == false);
        assertTrue(result.contains("2") == false);
        
        //Intersect collections with no common elements
        result = Utilities.intersectCollection(Arrays.asList(firstList, secondList));
        assertTrue(result.size() == 0);
        
        //Intersect multiple collection with empty collection
        result = Utilities.intersectCollection(Arrays.asList(firstList, secondList, fourthList));
        assertTrue(result.size() == 0);
        
        //Intersect empty collection with no other
        result = Utilities.intersectCollection(Arrays.asList(fourthList));
        assertTrue(result.size() == 0);
    }
    
    @Test
    public void testIntersectArgsCustomCollector() throws InterruptedException {
        Thread.sleep(300L);
        Collection<String> result = Utilities.intersectArgsCustomCollector(firstList, thirdList);
        
        assertTrue(result.size() == 4);
        assertTrue(result.contains("one"));
        assertTrue(result.contains("three"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("3"));
        assertTrue(result.contains("two") == false);
        assertTrue(result.contains("2") == false);
        
        //Intersect collections with no common elements
        result = Utilities.intersectArgsCustomCollector(firstList, secondList);
        assertTrue(result.size() == 0);
        
        //Intersect multiple collection with empty collection
        result = Utilities.intersectArgsCustomCollector(firstList, secondList, fourthList);
        assertTrue(result.size() == 0);
        
        //Intersect empty collection with no other
        result = Utilities.intersectArgsCustomCollector(fourthList);
        assertTrue(result.size() == 0);
    }
}
