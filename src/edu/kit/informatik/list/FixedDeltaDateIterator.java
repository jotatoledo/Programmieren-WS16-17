package edu.kit.informatik.list;

import edu.kit.informatik.calendar.Date;

/**
 * 
 * @author JoseNote
 * @version 1.00
 */
public class FixedDeltaDateIterator implements SortedIterator<Date> {
    private Date currentValue;
    private final Date endDate;
    private final int deltaYear;
    private final int deltaMonth;
    private final int deltaDay;
    
    public FixedDeltaDateIterator(Date startDate, Date endDate,
            int deltaYear, int deltaMonth, int deltaDay) {
        currentValue = startDate;
        this.endDate = endDate;
        this.deltaDay = deltaDay;
        this.deltaMonth = deltaMonth;
        this.deltaYear = deltaYear;
    }

    public boolean hasNext() {
        if (endDate == null) return true;
        return currentValue.compareTo(endDate) <= 0;
    }

    public Date next() {
        Date result = currentValue;
        currentValue = currentValue.plusDays(deltaDay).plusMonths(deltaMonth).plusYears(deltaYear);
        return result;
    }
}
