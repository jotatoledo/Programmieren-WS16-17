package test.calendar;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.kit.informatik.calendar.Date;
import edu.kit.informatik.calendar.DateTime;
import edu.kit.informatik.calendar.DayOfWeek;
import edu.kit.informatik.calendar.Month;
import edu.kit.informatik.calendar.Time;

import static org.hamcrest.CoreMatchers.*;

public class DateTest {
    public DateTest() {
        
    }
    
    @Test
    public void testDate() {
        Date test = new Date(2012, 11, 25);
        assertTrue(test.getDayOfMonth() == 25);
        assertTrue(test.getMonthValue() == 11);
        assertTrue(test.getYear() == 2012);
    }

    @Test
    public void testAtTime() {
        Time testTime = Time.MAX_VAL;
        Date testDate = new Date(2016, 11, 27);

        DateTime expectedResult = new DateTime(testDate, testTime);
        DateTime result = testDate.atTime(testTime);

        assertThat(expectedResult.toString(),  is(result.toString()));
    }

    @Test
    public void testGetYear() {
        Date test = new Date(2012, 1, 1);
        assertTrue(2012 == test.getYear());
    }

    @Test
    public void testGetMonthValue() {
        Date test = new Date(2012, 1, 1);
        assertTrue(1 == test.getMonthValue());
    }

    @Test
    public void testGetDayOfMonth() {
        Date test = new Date(2012, 1, 1);
        assertTrue(1 == test.getDayOfMonth());
        assertTrue(2 != test.getDayOfMonth());
    }

    @Test
    public void testGetMonth() {
        Date test = new Date(2016, 2, 1);
        assertTrue(Month.FEBRUARY == test.getMonth());
        assertTrue(Month.MARCH == test.plusMonths(1).getMonth());
    }

    @Test
    public void testGetDayOfYear() {
        Date test = new Date(2016, 2, 2);
        assertTrue(31  +  2 == test.getDayOfYear());
        assertTrue(31  +  29 + 2 == test.plusMonths(1).getDayOfYear());
        assertTrue(31 + 29 + 31 + 2 == test.plusMonths(2).getDayOfYear());
    }

    @Test
    public void testGetDayOfWeek() {
        Date sundayTest = new Date(2015, 2, 1);
        Date mondayTest = new Date(2016, 2, 1);
        Date tuesdayTest = new Date(2016, 8, 2);
        Date wednesdayTest = sundayTest.plusDays(3);
        Date thursdayTest = sundayTest.plusDays(4);
        Date fridayTest = new Date(2016, 12, 16);
        Date saturdayTest = new Date(2016, 11, 26);

        assertTrue(DayOfWeek.SUNDAY == sundayTest.getDayOfWeek());
        assertTrue(DayOfWeek.MONDAY == mondayTest.getDayOfWeek());
        assertTrue(DayOfWeek.TUESDAY == tuesdayTest.getDayOfWeek());
        assertTrue(DayOfWeek.WEDNESDAY == wednesdayTest.getDayOfWeek());
        assertTrue(DayOfWeek.THURSDAY == thursdayTest.getDayOfWeek());
        assertTrue(DayOfWeek.FRIDAY == fridayTest.getDayOfWeek());
        assertTrue(DayOfWeek.SATURDAY == saturdayTest.getDayOfWeek());
    }

    @Test
    public void testGetDaysInYear() {
        Date test = new Date(2016, 2, 1);
        assertTrue(366 == test.getDaysInYear());
        assertTrue(365 == test.plusYears(1).getDaysInYear());
        assertTrue(365 == test.plusYears(2).getDaysInYear());
        assertTrue(366 == test.plusYears(4).getDaysInYear());
    }

    @Test
    public void testIsLeapYear() {
        Date test1 = new Date(2016, 1, 1);
        assertTrue(true == test1.isLeapYear());

        Date test2 = new Date(2015, 1, 1);
        assertTrue(false == test2.isLeapYear());

        Date test3 = new Date(2000, 1, 1);
        assertTrue(true == test3.isLeapYear());
    }

    @Test
    public void testToString() {
        Date test1 = new Date(1000, 1, 1);
        assertThat(test1.toString(), is("01-01-1000"));

        Date test2 = new Date(1000, 10, 1);
        assertThat(test2.toString(), is("01-10-1000"));

        Date test3 = new Date(1000, 10, 11);
        assertThat(test3.toString(), is("11-10-1000"));

        Date test4 = new Date(1, 10, 11);
        assertThat(test4.toString(), is("11-10-1"));

        Date test5 = new Date(1, 1, 1);
        assertThat(test5.toString(), is("01-01-1"));
    }

    @Test
    public void testPlus() {
        Date firstBase = new Date(1000, 1, 1);
        Date firstAdd = new Date(1000, 1, 1);
        Date firstResult = firstBase.plus(firstAdd);
        Date firstResultExpected = new Date(2000, 2, 2);
        assertTrue(firstResultExpected.getDayOfMonth() == firstResult.getDayOfMonth());
        assertTrue(firstResultExpected.getMonthValue() == firstResult.getMonthValue());
        assertTrue(firstResultExpected.getYear() == firstResult.getYear());

        Date secondAdd = new Date(1000, 1, 28);
        Date secondResult = firstBase.plus(secondAdd);
        Date secondResultExpected = new Date(2000, 3, 1);
        assertTrue(secondResultExpected.getDayOfMonth() == secondResult.getDayOfMonth());
        assertTrue(secondResultExpected.getMonthValue() == secondResult.getMonthValue());
        assertTrue(secondResultExpected.getYear() == secondResult.getYear());

        Date thirdAdd = new Date(1001, 1, 28);
        Date thirdResult = firstBase.plus(thirdAdd);
        Date thirdResultExpected = new Date(2001, 3, 1);
        assertTrue(thirdResultExpected.getDayOfMonth() == thirdResult.getDayOfMonth());
        assertTrue(thirdResultExpected.getMonthValue() == thirdResult.getMonthValue());
        assertTrue(thirdResultExpected.getYear() == thirdResult.getYear());

        Date fourthAdd = new Date(1000, 1, 28);
        Date fourthResult = firstBase.plus(fourthAdd);
        Date fourthResultExpected = new Date(2000, 3, 1);
        assertTrue(fourthResultExpected.getDayOfMonth() == fourthResult.getDayOfMonth());
        assertTrue(fourthResultExpected.getMonthValue() == fourthResult.getMonthValue());
        assertTrue(fourthResultExpected.getYear() == fourthResult.getYear());


        Date secondBase = new Date(2000, 1, 1);
        Date fifthAdd = new Date(1000, 1, 28);
        Date fifthResultExpected = new Date(3000, 3, 1);
        Date fifthResult = secondBase.plus(fifthAdd);
        assertTrue(fifthResultExpected.getDayOfMonth() == fifthResult.getDayOfMonth());
        assertTrue(fifthResultExpected.getMonthValue() == fifthResult.getMonthValue());
        assertTrue(fifthResultExpected.getYear() == fifthResult.getYear());
    }

    @Test
    public void testPlusYears() {
        Date firstTest = new Date(1000, 1, 30);
        Date firstResult = firstTest.plusYears(2);
        Date firstResultExpected = new Date(1002, 1, 30);
        assertTrue(firstResultExpected.getDayOfMonth() == firstResult.getDayOfMonth());
        assertTrue(firstResultExpected.getMonthValue() == firstResult.getMonthValue());
        assertTrue(firstResultExpected.getYear() == firstResult.getYear());

        Date secondTest = new Date(2000, 2, 29);
        Date secondResult = secondTest.plusYears(1);
        Date secondResultExpected = new Date(2001, 3, 1);
        assertTrue(secondResultExpected.getDayOfMonth() == secondResult.getDayOfMonth());
        assertTrue(secondResultExpected.getMonthValue() == secondResult.getMonthValue());
        assertTrue(secondResultExpected.getYear() == secondResult.getYear());
    }

    @Test
    public void testPlusMonths() {
        Date firstTest = new Date(1000, 1, 30);
        Date firstResult = firstTest.plusMonths(1);
        Date firstResultExpected = new Date(1000, 3, 2);
        assertTrue(firstResultExpected.getDayOfMonth() == firstResult.getDayOfMonth());
        assertTrue(firstResultExpected.getMonthValue() == firstResult.getMonthValue());
        assertTrue(firstResultExpected.getYear() == firstResult.getYear());

        Date secondTest = new Date(2000, 2, 1);
        Date secondResult = secondTest.plusMonths(1);
        Date secondResultExpected = new Date(2000, 3, 1);
        assertTrue(secondResultExpected.getDayOfMonth() == secondResult.getDayOfMonth());
        assertTrue(secondResultExpected.getMonthValue() == secondResult.getMonthValue());
        assertTrue(secondResultExpected.getYear() == secondResult.getYear());

        Date thirdTest = new Date(2016, 1, 31);
        Date thirdResult = thirdTest.plusMonths(1);
        Date thirdResultExpected = new Date(2016, 3, 2);
        assertTrue(thirdResultExpected.getDayOfMonth() == thirdResult.getDayOfMonth());
        assertTrue(thirdResultExpected.getMonthValue() == thirdResult.getMonthValue());
        assertTrue(thirdResultExpected.getYear() == thirdResult.getYear());
    }

    @Test
    public void testPlusDays() {
        Date firstTest = new Date(2016, 1, 31);
        Date firstResult = firstTest.plusDays(1);
        Date firstResultExpected = new Date(2016, 2, 1);
        assertTrue(firstResultExpected.getDayOfMonth() == firstResult.getDayOfMonth());
        assertTrue(firstResultExpected.getMonthValue() == firstResult.getMonthValue());
        assertTrue(firstResultExpected.getYear() == firstResult.getYear());

        Date secondTest = new Date(2016, 1, 29);
        Date secondResult = secondTest.plusDays(3);
        Date secondResultExpected = new Date(2016, 2, 1);
        assertTrue(secondResultExpected.getDayOfMonth() == secondResult.getDayOfMonth());
        assertTrue(secondResultExpected.getMonthValue() == secondResult.getMonthValue());
        assertTrue(secondResultExpected.getYear() == secondResult.getYear());

        Date thirdTest = new Date(2015, 2, 27);
        Date thirdResult = thirdTest.plusDays(3);
        Date thirdResultExpected = new Date(2015, 3, 2);
        assertTrue(thirdResultExpected.getDayOfMonth() == thirdResult.getDayOfMonth());
        assertTrue(thirdResultExpected.getMonthValue() == thirdResult.getMonthValue());
        assertTrue(thirdResultExpected.getYear() == thirdResult.getYear());
    }

    @Test
    public void testMinus() {
        //02-02-2000 - 01-01-1000 = 01-01-1000
        Date firstTest = new Date(2000, 2, 2);
        Date firstOperator = new Date(1000, 1, 1);
        Date firstResult = firstTest.minus(firstOperator);
        Date firstResultExpected  = new Date(1000, 1, 1);
        assertTrue(firstResultExpected.getDayOfMonth() == firstResult.getDayOfMonth());
        assertTrue(firstResultExpected.getMonthValue() == firstResult.getMonthValue());
        assertTrue(firstResultExpected.getYear() == firstResult.getYear());

        //29-02-2000 - 28-01-1000 = 01-01-1000
        Date secondTest = new Date(2000, 2, 29);
        Date secondOperator = new Date(1000, 1, 28);
        Date secondResult = secondTest.minus(secondOperator);
        Date secondResultExpected = new Date(1000, 1, 1);
        assertTrue(secondResultExpected.getDayOfMonth() == secondResult.getDayOfMonth());
        assertTrue(secondResultExpected.getMonthValue() == secondResult.getMonthValue());
        assertTrue(secondResultExpected.getYear() == secondResult.getYear());

        //01-03-2001 - 28-01-1001 = 01-01-1000
        Date thirdTest = new Date(2000, 2, 29);
        Date thirdOperator = new Date(1000, 1, 28);
        Date thirdResult = thirdTest.minus(thirdOperator);
        Date thirdResultExpected = new Date(1000, 1, 1);
        assertTrue(thirdResultExpected.getDayOfMonth() == thirdResult.getDayOfMonth());
        assertTrue(thirdResultExpected.getMonthValue() == thirdResult.getMonthValue());
        assertTrue(thirdResultExpected.getYear() == thirdResult.getYear());

        //31.3.2015 - 1.1.0000 = 2.3.2015
        Date fourthTest = new Date(2015, 3, 31);
        Date fourthOperator = new Date(0000, 1, 1);
        Date fourthResult = fourthTest.minus(fourthOperator);
        Date fourthResultExpected = new Date(2015, 3, 2);
        assertTrue(fourthResultExpected.getDayOfMonth() == fourthResult.getDayOfMonth());
        assertTrue(fourthResultExpected.getMonthValue() == fourthResult.getMonthValue());
        assertTrue(fourthResultExpected.getYear() == fourthResult.getYear());
    }

    @Test
    public void testMinusYears() {
        //Soft case: change of year doesn't change month/day attributes
        Date firstTest = new Date(2016, 1, 31);
        Date firstResult = firstTest.minusYears(1);
        Date firstResultExpected = new Date(2015, 1, 31);
        assertTrue(firstResultExpected.getDayOfMonth() == firstResult.getDayOfMonth());
        assertTrue(firstResultExpected.getMonthValue() == firstResult.getMonthValue());
        assertTrue(firstResultExpected.getYear() == firstResult.getYear());

        //Strong case: change of year causes change of month and day
        Date secondTest = new Date(2016, 2, 29);
        Date secondResult = secondTest.minusYears(1);
        Date secondResultExpected = new Date(2015, 3, 1);
        assertTrue(secondResultExpected.getDayOfMonth() == secondResult.getDayOfMonth());
        assertTrue(secondResultExpected.getMonthValue() == secondResult.getMonthValue());
        assertTrue(secondResultExpected.getYear() == secondResult.getYear());

        //Soft case: change of year doesn't change month/day attributes
        Date thirdTest = new Date(2016, 2, 29);
        Date thirdResult = thirdTest.minusYears(4);
        Date thirdResultExpected = new Date(2012, 2, 29);
        assertTrue(thirdResultExpected.getDayOfMonth() == thirdResult.getDayOfMonth());
        assertTrue(thirdResultExpected.getMonthValue() == thirdResult.getMonthValue());
        assertTrue(thirdResultExpected.getYear() == thirdResult.getYear());
    }

    @Test
    public void testMinusMonths() {
        //Mid case: change of month causes change of year but not day
        Date firstTest = new Date(2016, 1, 31);
        Date firstResult = firstTest.minusMonths(1);
        Date firstResultExpected = new Date(2015, 12, 31);
        assertTrue(firstResultExpected.getDayOfMonth() == firstResult.getDayOfMonth());
        assertTrue(firstResultExpected.getMonthValue() == firstResult.getMonthValue());
        assertTrue(firstResultExpected.getYear() == firstResult.getYear());

        //Soft case: change of month doesn't causes change of year nor day
        Date secondTest = new Date(2016, 8, 31);
        Date secondResult = secondTest.minusMonths(1);
        Date secondResultExpected = new Date(2016, 7, 31);
        assertTrue(secondResultExpected.getDayOfMonth() == secondResult.getDayOfMonth());
        assertTrue(secondResultExpected.getMonthValue() == secondResult.getMonthValue());
        assertTrue(secondResultExpected.getYear() == secondResult.getYear());

        //Mid case: change of month causes change of day but not year
        Date thirdTest = new Date(2016, 7, 31);
        Date thirdResult = thirdTest.minusMonths(1);
        Date thirdResultExpected = new Date(2016, 7, 1);
        assertTrue(thirdResultExpected.getDayOfMonth() == thirdResult.getDayOfMonth());
        assertTrue(thirdResultExpected.getMonthValue() == thirdResult.getMonthValue());
        assertTrue(thirdResultExpected.getYear() == thirdResult.getYear());
    }

    @Test
    public void testMinusDays() {
        //Soft case: change only of day attribute
        Date firstTest = new Date(2016, 1, 31);
        Date firstResult = firstTest.minusDays(1);
        Date firstResultExpected = new Date(2016, 1, 30);

        assertTrue(firstResultExpected.getDayOfMonth() == firstResult.getDayOfMonth());
        assertTrue(firstResultExpected.getMonthValue() == firstResult.getMonthValue());
        assertTrue(firstResultExpected.getYear() == firstResult.getYear());
    }

    @Test
    public void testIsBefore() {
        Date firstTest = new Date(2016, 1, 31);
        Date secondTest = new Date(2016, 5, 1);
        Date thirdTest = new Date(2015, 10, 31);

        assertTrue(false == firstTest.isAfter(firstTest));
        assertTrue(false == firstTest.isAfter(secondTest));
        assertTrue(true == firstTest.isAfter(thirdTest));

        assertTrue(true == secondTest.isAfter(firstTest));
        assertTrue(false == secondTest.isAfter(secondTest));
        assertTrue(true == secondTest.isAfter(thirdTest));

        assertTrue(false == thirdTest.isAfter(firstTest));
        assertTrue(false == thirdTest.isAfter(secondTest));
        assertTrue(false == thirdTest.isAfter(thirdTest));
    }

    @Test
    public void testIsAfter() {
        Date firstTest = new Date(2016, 1, 31);
        Date secondTest = new Date(2016, 5, 1);
        Date thirdTest = new Date(2015, 10, 31);

        assertTrue(false == firstTest.isAfter(firstTest));
        assertTrue(false == firstTest.isAfter(secondTest));
        assertTrue(true == firstTest.isAfter(thirdTest));

        assertTrue(true == secondTest.isAfter(firstTest));
        assertTrue(false == secondTest.isAfter(secondTest));
        assertTrue(true == secondTest.isAfter(thirdTest));

        assertTrue(false == thirdTest.isAfter(firstTest));
        assertTrue(false == thirdTest.isAfter(secondTest));
        assertTrue(false == thirdTest.isAfter(thirdTest));
    }

    @Test
    public void testIsEqual() {
        Date firstTest = new Date(2016, 1, 31);
        Date secondTest = new Date(2016, 5, 1);
        Date thirdTest = new Date(2015, 10, 31);

        assertTrue(true == firstTest.isEqual(firstTest));
        assertTrue(false == firstTest.isEqual(secondTest));
        assertTrue(false == firstTest.isEqual(thirdTest));

        assertTrue(false == secondTest.isEqual(firstTest));
        assertTrue(true == secondTest.isEqual(secondTest));
        assertTrue(false == secondTest.isEqual(thirdTest));

        assertTrue(false == thirdTest.isEqual(firstTest));
        assertTrue(false == thirdTest.isEqual(secondTest));
        assertTrue(true == thirdTest.isEqual(thirdTest));
    }
}
