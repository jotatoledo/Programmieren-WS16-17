package main.java.calendar;

/**
 * Represents a month of a year.
 * The Enumeration starts at 0.
 * 
 * @author  JoseNote
 * @version 1.1, 2016/11/27
 */
public enum Month {
    /**
     * Represents the first month of the year, 31 days.
     */
    JANUARY,
    /**
     * Represents the second month of the year, 29 days in a leap year, 28 otherwise.
     */
    FEBRUARY,
    /**
     * Represents the third month of the year, 31 days.
     */
    MARCH,
    /**
     * Represents the fourth month of the year, 30 days.
     */
    APRIL,
    /**
     * Represents the fifth month of the year, 31 days.
     */
    MAY,
    /**
     * Represents the sixth month of the year, 30 days.
     */
    JUNE,
    /**
     * Represents the seventh month of the year, 31 days.
     */
    JULY,
    /**
     * Represents the eight month of the year, 31 days.
     */
    AUGUST,
    /**
     * Represents the ninth month of the year, 30 days.
     */
    SEPTEMBER,
    /**
     * Represents the tenth month of the year, 31 days.
     */
    OCTOBER,
    /**
     * Represents the eleventh month of the year, 30 days.
     */
    NOVEMBER,
    /**
     * Represents the twelfth and last month of the year, 31 days.
     */
    DECEMBER;

    private static final Month[] MONTHS = values();

    /**
     * Returns the month with the specified index.
     * 
     * <p>The specified index has to be between the {@linkplain #toIndex() index} of  {@linkplain #JANUARY} and
     * {@linkplain #DECEMBER} (inclusive).
     * 
     * @param  index the index
     * @return the month
     * @throws IllegalArgumentException if {@code index} is not the index of a month
     */
    public static Month ofIndex(final int index) {
        if (index < 1 || index > MONTHS.length)
            throw new IllegalArgumentException("Bad index " + index);
        return MONTHS[index - 1];
    }

    /**
     * Returns the index of the month.
     * 
     * <p>The months are continuously indexed, starting at {@code 1}.
     * 
     * @return the index
     */
    public int toIndex() {
        return ordinal() + 1;
    }

    /**
     * Returns the number of days associated to the object
     * @param leapYear A flag boolean value. If its true, it means that the object is related to a leap year.
     * @return A value between 28 and 31
     */
    public int getDaysInMonth(boolean leapYear) {
        int daysOfMonth = 0;
        switch(this) {
            case FEBRUARY:
                //If the leapYear flag is true, 29 will be assigned to daysOfMonth. 
                //28 will be assigned otherwise
                daysOfMonth = leapYear == true ? 29 : 28;
                break;
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER: 
                daysOfMonth = 31;
                break;
            default:
                //Default case catches APRIL,JUNE,SEPTEMBER,NOVEMBER
                daysOfMonth = 30;
        }
        return daysOfMonth;
    }
}
