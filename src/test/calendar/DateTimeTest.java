package test.calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.calendar.Date;
import edu.kit.informatik.calendar.DateTime;
import edu.kit.informatik.calendar.Time;

public class DateTimeTest {
    public DateTimeTest() {
        
    }
    
    @Test
    public void testDateTime() {
        Date testDate = new Date(2016, 1, 1);
        Time testTime = Time.MIN_VAL;
        DateTime testDateTime = new DateTime(testDate,  testTime);
        
        assertTrue(testDateTime != null);
        assertThat(testDateTime.toString(), is("01-01-2016T00:00:00"));
    }

    @Test
    public void testGetDate() {
        Date testDate = new Date(2016, 1, 1);
        Time testTime = Time.MIN_VAL;
        DateTime testDateTime = new DateTime(testDate,  testTime);

        assertThat(testDateTime.getDate().toString(),  is(testDate.toString()));
    }

    @Test
    public void testGetTime() {
        Date testDate = new Date(2016, 1, 1);
        Time testTime = Time.MIN_VAL;
        DateTime testDateTime = new DateTime(testDate,  testTime);

        assertThat(testDateTime.getTime().toString(),  is(testTime.toString()));
    }

    @Test
    public void testToString() {
        Date testDate = new Date(2016, 1, 1);
        Time testTime = new Time(5, 3, 4);
        DateTime testDateTime = new DateTime(testDate,  testTime);

        assertThat(testDateTime.toString(),  is("01-01-2016T05:03:04"));
    }

//    @Test
//    public void testGetYear() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetMonthValue() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetMonth() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetDayOfYear() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetDayOfMonth() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetDayOfWeek() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetHour() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetMinute() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetSecond() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testPlusDateTime() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testMinusDateTime() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testPlusDate() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testMinusDate() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testPlusTime() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testMinusTime() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testPlusYears() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testPlusMonths() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testPlusDays() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testMinusYears() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testMinusMonths() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testMinusDays() {
//        fail("Not yet implemented");
//    }
}
