package test.java.calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.calendar.Date;
import edu.kit.informatik.calendar.Time;

public class TimeTest {
    public TimeTest() {
        
    }
    
    @Test
    public void testTime() {
        Time testTime = new Time(1, 23, 11);
        
        assertTrue(testTime != null);
        assertThat(testTime.toString(), is("01:23:11"));
    }

    @Test
    public void testAtDate() {
        Time testTime = new Time(1, 23, 11);
        Date testDate = new Date(1, 1, 1);
        
        assertThat(testTime.atDate(testDate).toString(), is("01-01-1T01:23:11"));
    }

    @Test
    public void testGetHour() {
        Time testTime = new Time(1, 23, 11);
        
        assertTrue(testTime.getHour() == 1);
    }

    @Test
    public void testGetMinute() {
        Time testTime = new Time(1, 23, 11);
        
        assertTrue(testTime.getMinute() == 23);
    }

    @Test
    public void testGetSecond() {
        Time testTime = new Time(1, 23, 11);
        
        assertTrue(testTime.getSecond() == 11);
    }

    @Test
    public void testToString() {
        Time testTime = new Time(1, 23, 11);

        String actualResult = testTime.toString();
        String expectedResult = "01:23:11";

        assertThat(expectedResult,  is(actualResult));

        testTime = new Time(1, 1, 1);

        actualResult = testTime.toString();
        expectedResult = "01:01:01";

        assertThat(expectedResult,  is(actualResult));
    }

    @Test
    public void testPlus() {
        Time result = Time.MIN_VAL.plus(Time.MAX_VAL);
        
        assertThat(result.toString(), is(Time.MAX_VAL.toString()));
    }

    @Test
    public void testMinus() {
        Time result = Time.MAX_VAL.minus(Time.MIN_VAL);
        
        assertThat(result.toString(), is(Time.MAX_VAL.toString()));
    }

    @Test
    public void testIsBefore() {
        Time firstTest = new Time(23, 59, 59); 
        Time secondTest = new Time(0, 0, 0); 
        Time thirdTest = new Time(10, 10, 10);

        assertTrue(false == firstTest.isBefore(firstTest));
        assertTrue(false == firstTest.isBefore(secondTest));
        assertTrue(false == firstTest.isBefore(thirdTest));

        assertTrue(true == secondTest.isBefore(firstTest));
        assertTrue(false == secondTest.isBefore(secondTest));
        assertTrue(true == secondTest.isBefore(thirdTest));

        assertTrue(true == thirdTest.isBefore(firstTest));
        assertTrue(false == thirdTest.isBefore(secondTest));
        assertTrue(false == thirdTest.isBefore(thirdTest));
    }

    @Test
    public void testIsEqual() {
        Time firstTest = new Time(23, 59, 59); 
        Time secondTest = new Time(0, 0, 0);
        Time thirdTest = new Time(10, 10, 10);

        assertTrue(true == firstTest.isEqual(firstTest));
        assertTrue(false == secondTest.isEqual(firstTest));
        assertTrue(false == thirdTest.isEqual(firstTest));

        assertTrue(false == firstTest.isEqual(secondTest));
        assertTrue(true == secondTest.isEqual(secondTest));
        assertTrue(false == thirdTest.isEqual(secondTest));

        assertTrue(false == firstTest.isEqual(thirdTest));
        assertTrue(false == secondTest.isEqual(thirdTest));
        assertTrue(true == thirdTest.isEqual(thirdTest));
    }

    @Test
    public void testIsAfter() {
        Time firstTest = new Time(23, 59, 59); 
        Time secondTest = new Time(0, 0, 0); 
        Time thirdTest = new Time(10, 10, 10);
        Time fourthTest = new Time(23, 59, 58);

        assertTrue(false == firstTest.isAfter(firstTest));
        assertTrue(true == firstTest.isAfter(secondTest));
        assertTrue(true == firstTest.isAfter(thirdTest));
        assertTrue(true == firstTest.isAfter(fourthTest));

        assertTrue(false == secondTest.isAfter(firstTest));
        assertTrue(false == secondTest.isAfter(secondTest));
        assertTrue(false == secondTest.isAfter(thirdTest));

        assertTrue(false == thirdTest.isAfter(firstTest));
        assertTrue(true == thirdTest.isAfter(secondTest));
        assertTrue(false == thirdTest.isAfter(thirdTest));

        assertTrue(false == fourthTest.isAfter(firstTest));
    }

}
