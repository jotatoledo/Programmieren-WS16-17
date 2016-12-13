package main.java.calendar;

/**
 * Represents a specific time in seconds, minutes and hours.
 * 
 * @author  JoseNote
 * @version 2.0, 2016/11/27
 */
public final class Time {
    /**
     * Represents the minimum values that a {@linkplain Time} instance can have
     */
    public static final Time MIN_VAL = new Time(0, 0, 0);
    /**
     * Represents the maximum values that a {@linkplain Time} instance can have
     */
    public static final Time MAX_VAL = new Time(23, 59, 59);
    /**
     * A number between 0 and 23
     */
    private final int hour;
    /**
     * A number between 0 and 59
     */
    private final int minute;
    /**
     * A number between 0 and 59
     */
    private final int second;

    /**
     * Initializes a new object instance with values for all its attributes
     * @param hour The value for {@link #hour}
     * @param minute The value for {@link #minute}
     * @param second The value for {@link #second}
     */
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    //region Fabric method A.3

    /**
     * Creates a {@linkplain DateTime} instance using the current  {@linkplain Time} and a given {@linkplain Date}
     * @param date The {@linkplain Date} to use
     * @return An instance of {@linkplain DateTime}
     */
    public DateTime atDate(Date date) {
        return new DateTime(date, this);
    }

    //endregion

    //region Encapsulation A.5

    /**
     * Getter for {@link #hour}
     * @return The value of {@link #hour}
     */
    public int getHour() {
        return hour;
    }

    /**
     * Getter for {@link #minute}
     * @return The value of {@link #minute}
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Getter for {@link #second}
     * @return The value of {@link #second}
     */
    public int getSecond() {
        return second;
    }

    //endregion

    //region Text representation A.6

    /**
     * Generates the string representation of the object
     * @return The string representation of the object in format hh:mm:ss
     */
    public String toString() {
        String hourRepresentation = "";
        String minuteRepresentation = "";
        String secondRepresentation = "";

        if (hour < 10) { 
            hourRepresentation = "0" + String.valueOf(hour);
        } else {
            hourRepresentation = String.valueOf(hour);
        }
        if (minute < 10) {
            minuteRepresentation = "0";
        }
        minuteRepresentation = minuteRepresentation.concat(String.valueOf(minute));
        if (second < 10) {
            secondRepresentation = "0";
        }
        secondRepresentation = secondRepresentation.concat(String.valueOf(second));
        return hourRepresentation.concat(":").concat(minuteRepresentation).concat(":").concat(secondRepresentation);
    }

    //endregion

    //region Time calculations A.9.1

    /**
     * Adds a {@linkplain Time} instance to the object
     * @param time The {@linkplain Time} instance to add
     * @return A new {@linkplain Time} with the resulting values
     */
    public Time plus(Time time) {
        int totalSeconds = second + time.getSecond();
        int totalMinutes = minute + time.getMinute();
        int totalHours = hour + time.getHour();

        if (totalSeconds > 59) {
            totalMinutes++;
            totalSeconds -= 60;
        }
        if (totalMinutes > 59) {
            totalHours++;
            totalMinutes -= 60;
        }
        if (totalHours > 23) {
            totalHours -= 24;
        }
        return new Time(totalHours, totalMinutes, totalSeconds);
    }

    /**
     * Subtracts a {@linkplain Time} instance from the object
     * @param time The {@linkplain Time} instance to subtract
     * @return A new {@linkplain Time} with the resulting values
     */
    public Time minus(Time time) {
        int totalSeconds = second - time.getSecond();
        int totalMinutes = minute - time.getMinute();
        int totalHours = hour - time.getHour();

        if (totalSeconds < 0) {
            totalMinutes--;
            totalSeconds += 60;
        }
        if (totalMinutes < 0) {
            totalHours--;
            totalMinutes += 60;
        }
        if (totalHours < 0) {
            totalHours += 24;
        }
        return new Time(totalHours, totalMinutes, totalSeconds);
    }

    //endregion

    //region Order relations A.10

    /**
     * Compares the object to a given {@linkplain Time} instance and checks if the object is located before in time
     * @param other The {@linkplain Time} instance to compare
     * @return {@code True} if the object is located in time before the given value. {@code False} otherwise
     */
    public boolean isBefore(Time other) {
        boolean isBefore = false;

        if (hour <= other.getHour()) {
            if (hour < other.getHour()) {
                isBefore = true;
            } else {
                if (minute <= other.getMinute()) {
                    if (minute < other.getMinute()) {
                        isBefore = true;
                    } else {
                        if (second < other.getSecond()) {
                            isBefore = true;
                        }
                    }
                }
            }
        }
        return isBefore;
    }

    /**
     * Checks if the object is the same time point than a given {@linkplain Time}.
     * @param other The {@linkplain Time} to compare
     * @return {@code True} if the object and the parameter are the same time point. {@code False} otherwise.
     */
    public boolean isEqual(Time other) {
        return !isBefore(other) && !isAfter(other);
    }

    /**
     * Compares the object to a given {@linkplain Time} instance and checks if the object is located after in time
     * @param other The {@linkplain Time} instance to compare
     * @return {@code True} if the object is located in time after the given value. {@code False} otherwise
     */
    public boolean isAfter(Time other) {
        boolean isAfter = false;

        if (hour >= other.getHour()) {
            if (hour > other.getHour()) {
                isAfter = true;
            } else {
                if (minute >= other.getMinute()) {
                    if (minute > other.getMinute()) {
                        isAfter = true;
                    } else {
                        if (second > other.getSecond()) {
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
