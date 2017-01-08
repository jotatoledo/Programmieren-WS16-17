package test.java.sortedList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.list.LinkedSortedAppendList;
import edu.kit.informatik.list.SortedIterator;

public class LinkedSortedAppendListTest {

    public LinkedSortedAppendListTest() {
        
    }
    
    @Test
    public void testLinkedSortedAppendList() {
        LinkedSortedAppendList<Integer> test = new LinkedSortedAppendList<Integer>();
        
        assertTrue(test != null);
    }

    @Test
    public void testAddSorted() {
        LinkedSortedAppendList<Integer> test = new LinkedSortedAppendList<Integer>();
        StringBuilder builder = new StringBuilder();
        
        test.addSorted(10);
        test.addSorted(11);
        test.addSorted(5);
        test.addSorted(7);        
        test.addSorted(9);
        test.addSorted(7);
        test.addSorted(10);
        test.addSorted(11);
        test.addSorted(5);
        test.addSorted(7);        
        test.addSorted(9);
        test.addSorted(7);
        
        SortedIterator<Integer> it = test.iterator();
        while (it.hasNext()) {
            Integer element = it.next();
            builder.append(element);
            if (it.hasNext())builder.append("-");
        }
        assertThat(builder.toString(), is("5-5-7-7-7-7-9-9-10-10-11-11"));
    }

    @Test
    public void testIterator() {
        LinkedSortedAppendList<Integer> test = new LinkedSortedAppendList<Integer>();
        SortedIterator<Integer> it = test.iterator();
        assertTrue(it != null);
    }

}
