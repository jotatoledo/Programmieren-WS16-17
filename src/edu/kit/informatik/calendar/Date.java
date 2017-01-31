package edu.kit.informatik.calendar;

/**
 * Represents a date consisting of a year, a {@linkplain Month month} and a day.
 * 
 * @author  JoseNote
 * @version 2.0, 2016/11/27
 */
public final class Date {
    private final int year;
    /**
     * A value between 1 and 12
     */
    private final int month;
    /**
     * A value between 1 and 31
     */
    private final int day;

    /**
     * Initializes a new object instance with values for all its attributes
     * @param year The value for {@link #year}
     * @param month The value for {@link #month}
     * @param dayOfMonth The value for {@link #day}
     */
    public Date(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        day = dayOfMonth;
    }

    //region Fabric method A.3

    /**
     * Creates a {@linkplain DateTime} instance using the current  {@linkplain Date} and a given {@linkplain Time}
     * @param time The {@linkplain Time} to use
     * @return An instance of {@linkplain DateTime}
     */
    public DateTime atTime(Time time) {
        return new DateTime(this, time);
    }

    //endregion

    //region Encapsulation A.5

    /**
     * Gets the value of {@link #year}
     * @return A number
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the value of {@link #month}
     * @return A number between 1 and 12
     */
    public int getMonthValue() {
        return month;
    }

    /**
     * Gets the value of {@link #day}
     * @return A number between 1 and 31
     */
    public int getDayOfMonth() {
        return day;
    }

    //endregion

    //region Expanded functions A.7

    /**
     * Gets the matching {@linkplain Month month} associated to {@link #month} 
     * @return A {@linkplain Month month}
     */
    public Month getMonth() {
        return Month.ofIndex(month);
    }

    /**
     * Calculates the day value in relation to the year associated to this instance
     * @return A value between 1 and 365 (366 if {@link #year}  represents a leap year)
     */
    public int getDayOfYear() {
        int dayOfYear = 0;
        boolean isLeapYear = isLeapYear();

        //adds the days of the months previous to the one of the instance in an accumulator
        for (int i = 1; i < month; i++) {
            dayOfYear = dayOfYear + Month.ofIndex(i).getDaysInMonth(isLeapYear);
        }
        //Adds the days of the actual month of the instance to the accumulator
        dayOfYear += day;
        return dayOfYear;
    }

    /**
     * Gets the matching {@linkplain DayOfWeek day of week} associated to {@link #day} 
     * @return A {@linkplain DayOfWeek day of week}
     */
    public DayOfWeek getDayOfWeek() {
        Date dateBuffer = null;
        int totalDaysSinceStart = 0;

        for (int i = 0; i < year; i++) {
            dateBuffer = new Date(i, 1, 1);
            totalDaysSinceStart += dateBuffer.getDaysInYear();
        }

        totalDaysSinceStart += getDayOfYear() - 1;
        int modResult = totalDaysSinceStart % 7;


        int index = modResult == 0 ? 6 : modResult - 1;

        index = index == 0 ? 7 : index;
        return DayOfWeek.ofIndex(index);
    }

    /**
     * Gets the number of days associated to {@link #year}
     * @return 366 if {@link #year} represents a leap year. 365 otherwise
     */
    public int getDaysInYear() {
        return isLeapYear() ? 366 : 365;
    }

    /**
     * Checks if the value of {@link #year} is associated to a leap year.
     * @return True if {@link #year} represents a leap year. False otherwise.
     */
    public boolean isLeapYear() {
        return (year % 100 == 0) ? (year % 400 == 0) : (year % 4 == 0);
    }

    //endregion

    //region Text representation A.6

    /**
     * Generates the string representation of the object
     * @return The string representation of the object in format DD-MM-YYYY
     */
    public String toString() {
        String dayRepresentation = null;
        String monthRepresentation = null;
        String yearRepresentation = String.valueOf(year);

        if (day < 10) {
            dayRepresentation  =  "0" + String.valueOf(day);
        } else {
            dayRepresentation  =  String.valueOf(day);
        }
        if (month < 10) {
            monthRepresentation  =  "0" + String.valueOf(month);
        } else { 
            monthRepresentation  =  String.valueOf(month);
        }
        return dayRepresentation.concat("-").concat(monthRepresentation).concat("-").concat(yearRepresentation);
    }

    //endregion

    //region Date calculations A.9.2

    /**
     * Adds the values from a given {@link Date} to the current instance.
     * Calendar rules are respected
     * @param date An instance of {@link Date}
     * @return A new instance of {@link Date} with the merged values
     */
    public Date plus(Date date) {
        return this.plusDays(date.getDayOfMonth())
                .plusMonths(date.getMonthValue())
                .plusYears(date.getYear());
    }

    /**
     * Adds a given amount of years to the instance
     * Calendar rules are respected
     * @param years The amount of years to add
     * @return A new {@link Date} instance with the corresponding values
     */
    public Date plusYears(int years) {
        return new Date(years + this.year, 1, 1).plusMonths(month - 1).plusDays(day - 1);
    }

    /**
     * Adds a given amount of months to the instance
     * Calendar rules are respected
     * @param months The amount of months to add
     * @return A new {@link Date} instance with the corresponding values
     */
    public Date plusMonths(int months) {
        int totalMonths = months + this.month;

        if (totalMonths > 12) {
            //true: the addition of months is more than 12
            totalMonths -= 13;
            return new Date(year, 1, 1).plusYears(1).plusMonths(totalMonths).plusDays(day - 1);
        } else {
            //false: otherwise
            return new Date(year, totalMonths, 1).plusDays(day - 1);
        }
    }

    /**
     * Adds a given amount of days to the instance
     * Calendar rules are respected
     * @param days The amount of days to add
     * @return A new {@link Date} instance with the corresponding values
     */
    public Date plusDays(int days) {
        int totalDays = days + this.day;
        int daysInCurrentMonth = getDaysInMonth();

        if (totalDays > daysInCurrentMonth) {
            //true:the addition of days is bigger than the days in the current month
            totalDays -= daysInCurrentMonth + 1;
            return new Date(year, month, 1).plusMonths(1).plusDays(totalDays);
        } else {
            //false: the addition of days doesn't surpasses the the days of the current month
            return new Date(year, month, totalDays);
        }
    }

    /**
     * Subtracts the values from a given {@link Date} to the current instance.
     * Calendar rules are respected
     * @param date An instance of {@link Date}
     * @return A new instance of {@link Date} with the merged values
     */
    public Date minus(Date date) {
        return this.minusDays(date.getDayOfMonth())
                .minusMonths(date.getMonthValue())
                .minusYears(date.getYear());
    }

    /**
     * Subtracts a given amount of years to the instance
     * Calendar rules are respected
     * @param years The amount of years to subtract
     * @return A new {@link Date} instance with the corresponding values
     */
    public Date minusYears(int years) {
        return new Date(this.year - years, 1, 1).plusMonths(month - 1).plusDays(day - 1);
    }

    /**
     * Subtracts a given amount of months to the instance
     * Calendar rules are respected
     * @param months The amount of months to subtract
     * @return A new {@link Date} instance with the corresponding values
     */
    public Date minusMonths(int months) {
        int totalMonths = this.month - months;

        if (totalMonths <= 0) {
            return new Date(year, 12, 1).minusYears(1).minusMonths(Math.abs(totalMonths)).plusDays(day - 1);
        } else {
            return new Date(year, totalMonths, 1).plusDays(day - 1);
        }
    }

    /**
     * Subtracts a given amount of days to the instance
     * Calendar rules are respected
     * @param days The amount of days to subtract
     * @return A new {@link Date} instance with the corresponding values
     */
    public Date minusDays(int days) {
        int totalDays = this.day - days;

        if (totalDays <= 0) {
            return  new Date(year, month, 1).minusMonths(1).moveToEndOfMonth().minusDays(Math.abs(totalDays));
        } else {
            return new Date(year, month, totalDays);
        }
    }

    /**
     * Support function for the calculation of dates.
     * Returns an instance of {@linkplain Date date} who's {@link #day} value matches the biggest day in {@link #month}
     * @return A new instance of {@linkplain Date date}
     */
    private Date moveToEndOfMonth() {
        return new Date(getYear(), getMonthValue(), getDaysInMonth());
    }

    /**
     * Support function for the calculation of dates.
     * Gets the amount of days associated to {@link #month} 
     * @return A number between 28 and 31
     */
    private int getDaysInMonth() {
        return getMonth().getDaysInMonth(isLeapYear());
    }

    //endregion

    //region Order relations A.10

    /**
     * Compares the object to a given {@linkplain Date} instance and checks if the object is located before in time
     * @param other The {@linkplain Date} instance to compare
     * @return {@code True} if the object is located in time before the given value. {@code False} otherwise
     */
    public boolean isBefore(Date other) {
        boolean isBefore = false;

        if (year <= other.getYear()) {
            if (year < other.getYear()) {
                isBefore = true;
            } else {
                if (month <= other.getMonthValue()) {
                    if (month < other.getMonthValue()) {
                        isBefore = true;
                    } else {
                        if (day < other.getDayOfMonth()) {
                            isBefore = true;
                        }
                    }
                }
            }
        }
        return isBefore;
    }

    /**
     * Compares the object to a given {@linkplain Date} instance and checks if both represent the same point in time
     * @param other The {@linkplain Date} instance to compare
     * @return {@code True} if the object and the parameter are the same point in time
     */
    public boolean isEqual(Date other) {
        return !isAfter(other) && !isBefore(other);
    }

    /**
     * Compares the object to a given {@linkplain Date} instance and checks if the object is located after in time
     * @param other The {@linkplain Date} instance to compare
     * @return {@code True} if the object is located in time after the given value. {@code False} otherwise
     */
    public boolean isAfter(Date other) {
        boolean isAfter = false;

        if (year >= other.getYear()) {
            if (year > other.getYear()) {
                isAfter = true;
            } else {
                if (month >= other.getMonthValue()) {
                    if (month > other.getMonthValue()) {
                        isAfter = true;
                    } else {
                        if (day > other.getDayOfMonth()) {
                            isAfter = true;
                        }
                    }
                }
            }
        }
        return isAfter;
    }

    //endregion
}
