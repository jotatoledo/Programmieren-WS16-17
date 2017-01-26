package main.java.calendar;

/**
 * Represents a specific {@linkplain Time} at a specific {@linkplain Date}.
 * 
 * @author  JoseNote
 * @version 2.0, 2016/11/27
 */
public final class DateTime {   
    /**
     * The {@linkplain Date} component of the object
     */
    private final Date date;
    /**
     * The {@linkplain Time} component of the object
     */
    private final Time time;

    /**
     * Initializes a new {@linkplain DateTime} instance with values for all its attributes
     * @param date The value for {@link #date}
     * @param time The value for {@link #time}
     */
    public DateTime(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    //region Encapsulation A.5

    /**
     * Getter for {@link #date}
     * @return The value of {@link #date}
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter for {@link #time}
     * @return The value of {@link #time}
     */
    public Time getTime() {
        return time;
    }

    //endregion Encapsulation A.5

    //region Text representation A.6

    /**
     * Generates the string representation of the object
     * @return The string representation of the object in format DD-MM-YYYYThh:mm:ss
     */
    public String toString() {
        return date.toString().concat("T").concat(time.toString());
    }

    //endregion Text representation A.6

    //region Extra methods A.8

    /**
     * Gets the year value of the {@linkplain Date} component of the object
     * @return The value of {@link Date #year}
     */
    public int getYear() {
        return this.date.getYear();
    }

    /**
     * Gets the month value of the {@linkplain Date} component of the object
     * @return The value of {@link Date #month}
     */
    public int getMonthValue() {
        return this.date.getMonthValue();
    }

    /**
     * Gets the {@linkplain Month} value of the {@linkplain Date} component of the object
     * @return The {@linkplain Month month} value associated to {@link Date #month}
     */
    public Month getMonth() {
        return this.date.getMonth();
    }

    /**
     * The day on the year associated to {@link #date}
     * @return A value between 1 and 365 (366 if {@link #date} is associated to a leap year)
     */
    public int getDayOfYear() {
        return this.date.getDayOfYear();
    }

    /**
     * Gets the day value of the {@linkplain Date} component of the object
     * @return The value of {@link Date #day}
     */
    public int getDayOfMonth() {
        return this.date.getDayOfMonth();
    }

    /**
     * Gets the {@linkplain DayOfWeek} value of the {@linkplain Date} component of the object
     * @return The {@linkplain DayOfWeek} value associated to {@link Date #day}
     */
    public DayOfWeek getDayOfWeek() {
        return this.date.getDayOfWeek();
    }

    /**
     * Gets the hour value of the {@linkplain Time} component of the object
     * @return The value of {@link Time #hour}
     */
    public int getHour() {
        return this.time.getHour();
    }

    /**
     * Gets the minute value of the {@linkplain Time} component of the object
     * @return The value of {@link Time #minute}
     */
    public int getMinute() {
        return this.time.getMinute();
    }

    /**
     * Gets the second value of the {@linkplain Time} component of the object
     * @return The value of {@link Time #second}
     */
    public int getSecond() {
        return this.time.getSecond();
    }

    //endregion Extra methods A.8

    //region Datetime calculations A.9.3

    /**
     * Adds a {@linkplain DateTime} instance to the object.
     * Calendar rules are respected.
     * @param datetime The {@linkplain DateTime} instance to add
     * @return A new instance of {@linkplain DateTime} with the new values
     */
    public DateTime plus(DateTime datetime) {
        return plus(datetime.getTime()).plus(datetime.getDate());
    }

    /**
     * Subtract a {@linkplain DateTime} instance from the object.
     * Calendar rules are respected.
     * @param datetime The {@linkplain DateTime} instance to subtract
     * @return A new instance of {@linkplain DateTime} with the new values
     */
    public DateTime minus(DateTime datetime) {
        return minus(datetime.getTime()).minus(datetime.getDate());
    }

    /**
     * Adds a {@linkplain Date} instance to {@link #date}.
     * Calendar rules are respected.
     * @param date The {@linkplain Date} instance to add
     * @return A new instance of {@linkplain DateTime} with the new values
     */
    public DateTime plus(Date date) {
        return this.date.plus(date).atTime(time);
    }

    /**
     * Subtracts a {@linkplain Date} from {@link #date}.
     * Calendar rules are respected.
     * @param date The {@linkplain Date} instance to subtract
     * @return A new instance of {@linkplain DateTime} with the new values
     */
    public DateTime minus(Date date) {
        return this.date.minus(date).atTime(time);
    }

    /**
     * Adds a {@linkplain Time} instance to {@link #time}.
     * Calendar rules are respected.
     * @param time The {@linkplain Time} instance to be add
     * @return A new instance of {@linkplain DateTime} with the new values
     */
    public DateTime plus(Time time) {
        Time resultTime = this.time.plus(time);
        int dayCarry = 0;

        if (!time.isEqual(Time.MIN_VAL) && !this.time.isEqual(Time.MIN_VAL)) {
            //true: both the object time and the parameter time aren't 00:00:00
            //Only in this case could the addition of 2 times create a day carry
            if (!resultTime.isAfter(this.time) && !resultTime.isAfter(time)) {
                //true: the result isn't after the object time or the argument time
                //This means that the result surpassed 23:59:59, creating a day carry
                dayCarry++;
            }
        }
        return date.plusDays(dayCarry).atTime(resultTime);
    }

    /**
     * Subtracts a {@linkplain Time} instance from {@link #time}.
     * Calendar rules are respected. 
     * @param time The {@linkplain Time} instance to subtract
     * @return A new instance of {@linkplain DateTime} with the new values
     */
    public DateTime minus(Time time) {
        Time resultTime = this.time.minus(time);
        int dayCarry = 0;

        if (time.isAfter(this.time)) {
            //true: the time to be subtracted is larger than the one of the object
            //Only in this case, the subtraction will create a day carry
            dayCarry++;
        }
        return date.minusDays(dayCarry).atTime(resultTime);
    }

    /**
     * Adds years to the {@linkplain Date} component of the object.
     * Calendar rules are respected. 
     * @param years A positive or 0 number. The amount of years to add.
     * @return A new {@linkplain DateTime} instance with the new values
     */
    public DateTime plusYears(int years) {
        return date.plusYears(years).atTime(time);
    }

    /**
     * Adds months to the {@linkplain Date} component of the object.
     * Calendar rules are respected. 
     * @param months A positive or 0 number. The amount of months to add.
     * @return A new {@linkplain DateTime} instance with the new values
     */
    public DateTime plusMonths(int months) {
        return date.plusMonths(months).atTime(time);
    }

    /**
     * Adds days to the {@linkplain Date} component of the object.
     * Calendar rules are respected. 
     * @param days A positive or 0 number. The amount of days to add.
     * @return A new {@linkplain DateTime} instance with the new values
     */
    public DateTime plusDays(int days) {
        return date.plusDays(days).atTime(time);
    }

    /**
     * Subtracts years from the {@linkplain Date} component of the object
     * Calendar rules are respected. 
     * @param years A positive or 0 number. The amount of years to subtract.
     * @return A new {@linkplain DateTime} instance with the new values
     */
    public DateTime minusYears(int years) {
        return date.minusYears(years).atTime(time);
    }

    /**
     * Subtracts months from the {@linkplain Date} component of the object.
     * Calendar rules are respected. 
     * @param months A positive or 0 number. The amount of months to subtract.
     * @return A new {@linkplain DateTime} instance with the new values
     */
    public DateTime minusMonths(int months) {
        return date.minusMonths(months).atTime(time);
    }

    /**
     * Subtracts days from the {@linkplain Date} component of the object.
     * Calendar rules are respected. 
     * @param days A positive or 0 number. The amount of days to subtract.
     * @return A new {@linkplain DateTime} instance with the new values
     */
    public DateTime minusDays(int days) {
        return date.minusDays(days).atTime(time);
    }

    //endregion Datetime calculations A.9.3

    //region Order relations A.10

    /**
     * 
     * @param other The other instance to compare
     * @return A boolean value
     */
    public boolean isBefore(DateTime other) {
        boolean isBefore = false;

        if (this.date.isBefore(other.getDate())) {
            isBefore = true;
        } else {
            if (this.date.isEqual(other.getDate())) {
                if (this.time.isBefore(other.getTime())) {
                    isBefore = true;
                }
            }
        }
        return isBefore;
    }

    /**
     * 
     * @param other The other instance to compare
     * @return A boolean value
     */
    public boolean isEqual(DateTime other) {
        return !this.isAfter(other) && !this.isBefore(other);
    }

    /**
     * 
     * @param other The other instance to compare
     * @return A boolean value
     */
    public boolean isAfter(DateTime other) {
        boolean isAfter = false;

        if (this.date.isAfter(other.getDate())) {
            isAfter = true;
        } else {
            if (this.date.isEqual(other.getDate())) {
                if (this.time.isAfter(other.getTime())) {
                    isAfter = true;
                }
            }
        }
        return isAfter;
    }

    //endregion Order relations A.10
}
