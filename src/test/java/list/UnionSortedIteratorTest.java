package test.java.list;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.calendar_sample.Date;
import edu.kit.informatik.iterator.fixed_delta_iterator.FixedDeltaDateIterator;
import edu.kit.informatik.iterator.union_sorted_iterator.UnionSortedIterator;

public class UnionSortedIteratorTest {
    private final FixedDeltaDateIterator first;
    private final FixedDeltaDateIterator second;
    
    public UnionSortedIteratorTest() {
        first = new FixedDeltaDateIterator(new Date(2016, 11, 15), 
                new Date(2017, 1 , 25) , 0, 0, 14);
        second = new FixedDeltaDateIterator(new Date(2016, 10, 26),
                new Date(2016, 12, 28), 0, 0, 7);
    }
    
    @Test
    public void testUnionSortedIterator() {
        UnionSortedIterator<Date> test = new UnionSortedIterator<Date>(first, second);
        
        assertTrue(test != null);
    }

    @Test
    public void testHasNext() {
        UnionSortedIterator<Date> test = new UnionSortedIterator<Date>(first, second);
        String expectedResult = null;
        String actualResult = null;
        StringBuilder builder = new StringBuilder();
        
        builder.append("26-10-2016 WEDNESDAY\n")
        .append("02-11-2016 WEDNESDAY\n")
        .append("09-11-2016 WEDNESDAY\n")
        .append("15-11-2016 TUESDAY\n")
        .append("16-11-2016 WEDNESDAY\n")
        .append("23-11-2016 WEDNESDAY\n")
        .append("29-11-2016 TUESDAY\n")
        .append("30-11-2016 WEDNESDAY\n")
        .append("07-12-2016 WEDNESDAY\n")
        .append("13-12-2016 TUESDAY\n")
        .append("14-12-2016 WEDNESDAY\n")
        .append("21-12-2016 WEDNESDAY\n")
        .append("27-12-2016 TUESDAY\n")
        .append("28-12-2016 WEDNESDAY\n")
        .append("10-01-2017 TUESDAY\n")
        .append("24-01-2017 TUESDAY");
        expectedResult = builder.toString();
        builder.setLength(0);       
        
        while (test.hasNext()) {
            Date date = test.next();
            builder.append(date.toString() + " " + date.getDayOfWeek());
            if (test.hasNext()) builder.append("\n");
        }
        actualResult = builder.toString();
        builder.setLength(0);
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void testNext() {
        UnionSortedIterator<Date> test = new UnionSortedIterator<Date>(first, second);
        
        assertThat("26-10-2016", is(test.next().toString()));
        assertThat("02-11-2016", is(test.next().toString()));
    }

}
