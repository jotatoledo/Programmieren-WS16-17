package test.list;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.calendar_sample.Date;
import edu.kit.informatik.iterator.fixed_delta_iterator.FixedDeltaDateIterator;

public class FixedDeltaDateIteratorTest {
    
    public FixedDeltaDateIteratorTest() {
    }
    
    @Test
    public void testFixedDeltaDateIterator() {
        FixedDeltaDateIterator test = new FixedDeltaDateIterator(new Date(2017, 1, 1), null , 3, 4, 10);
        
        assertTrue(test != null);
    }

    @Test
    public void testHasNext() {
        StringBuilder builder = new StringBuilder();
        final int limit = 5;
        String expectedResult = null;
        String actualResult = null;
        Date current = null;
        
        builder.append("01-01-2017 SUNDAY\n")
        .append("11-05-2020 MONDAY\n")
        .append("21-09-2023 THURSDAY\n")
        .append("01-02-2027 MONDAY\n")
        .append("11-06-2030 TUESDAY");
        expectedResult = builder.toString();
        builder.setLength(0);
        
        FixedDeltaDateIterator testEndNull = new FixedDeltaDateIterator(new Date(2017, 1, 1), 
                null, 3, 4, 10);
        //case where the end is not defined in the iterator
        
        for (int i = 0; i < limit; i++) {
            assertTrue(testEndNull.hasNext());
            current = testEndNull.next();
            builder.append(current.toString() + " " + current.getDayOfWeek());
            if (i != limit - 1)builder.append("\n");
        }
        actualResult = builder.toString();
        builder.setLength(0);
        
        assertThat(actualResult, is(expectedResult));
        
        FixedDeltaDateIterator testSameStartEnd = new FixedDeltaDateIterator(new Date(2017, 1, 1), 
                new Date(2017, 1, 1), 3, 4, 10);
        //case where the end is the same value as the start
        
        builder.append("01-01-2017 SUNDAY");
        expectedResult = builder.toString();
        builder.setLength(0);
        while (testSameStartEnd.hasNext()) {
            assertTrue(testSameStartEnd.hasNext());
            current = testSameStartEnd.next();
            builder.append(current.toString() + " " + current.getDayOfWeek());
            if (testSameStartEnd.hasNext())builder.append("\n");
        }
        actualResult = builder.toString();
        builder.setLength(0);
        
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void testNext() {
        FixedDeltaDateIterator testSameStartEnd = new FixedDeltaDateIterator(new Date(2017, 1, 1), 
                new Date(2017, 1, 1), 3, 4, 10);
        Date current = testSameStartEnd.next();
        
        assertThat(new Date(2017, 1, 1).toString(), is(current.toString()));
    }

}
