package test.literatur_system;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import edu.kit.informatik.literatur_system.Utilities;

public class UtilitiesTest {
    private final List<String> firstList = new ArrayList<String>();
    private final List<String> secondList = new ArrayList<String>();
    
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
    }
    
    
    @Test
    public void testConcatenatedList() {
        Collection<String> result = Utilities.concatenatedList(firstList, secondList);
        assertTrue(result.size() == 6);
        assertTrue(result != firstList && result != secondList);
        assertTrue(result.contains("one"));
        assertTrue(result.contains("two"));
        assertTrue(result.contains("three"));
        assertTrue(result.contains("four"));
        assertTrue(result.contains("five"));
        assertTrue(result.contains("six"));
    }

}
