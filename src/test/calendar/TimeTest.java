package test.calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.calendar.Time;

public class TimeTest {
    public TimeTest() {
        
    }
    
    @Test
    public void testTime() {
        fail("Not yet implemented");
    }

    @Test
    public void testAtDate() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetHour() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetMinute() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetSecond() {
        fail("Not yet implemented");
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
        fail("Not yet implemented");
    }

    @Test
    public void testMinus() {
        fail("Not yet implemented");
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
