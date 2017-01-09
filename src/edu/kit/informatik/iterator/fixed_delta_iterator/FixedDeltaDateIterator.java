package edu.kit.informatik.iterator.fixed_delta_iterator;

import edu.kit.informatik.calendar.Date;
import edu.kit.informatik.iterator.SortedIterator;

/**
 * Implements a dynamically generated list of {@linkplain Date} instances
 * @author JoseNote
 * @version 1.00
 */
public final class FixedDeltaDateIterator implements SortedIterator<Date> {
    private Date currentValue;
    private final Date endDate;
    private final int deltaYear;
    private final int deltaMonth;
    private final int deltaDay;

    /**
     * Creates a new iterator
     * @param startDate The starting date
     * @param endDate The maximum date
     * @param deltaYear The displacement value for years
     * @param deltaMonth The displacement value for months
     * @param deltaDay The displacement value for days
     */
    public FixedDeltaDateIterator(Date startDate, Date endDate,
            int deltaYear, int deltaMonth, int deltaDay) {
        currentValue = new Date(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth());
        this.endDate = endDate != null 
                ? new Date(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth()) : null;
        this.deltaDay = deltaDay;
        this.deltaMonth = deltaMonth;
        this.deltaYear = deltaYear;
    }

    public boolean hasNext() {
        if (endDate == null) 
            return true;
        return currentValue.compareTo(endDate) <= 0;
    }

    public Date next() {
        Date result = currentValue;
        currentValue = currentValue.plusDays(deltaDay).plusMonths(deltaMonth).plusYears(deltaYear);
        return result;
    }
}
