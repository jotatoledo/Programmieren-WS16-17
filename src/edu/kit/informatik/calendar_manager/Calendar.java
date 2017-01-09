package edu.kit.informatik.calendar_manager;

import edu.kit.informatik.calendar.Appointment;
import edu.kit.informatik.calendar.Date;
import edu.kit.informatik.calendar.DateTime;
import edu.kit.informatik.calendar.Time;
import edu.kit.informatik.iterator.SortedIterator;
import edu.kit.informatik.list.LinkedSortedAppendList;

/**
 * Offers functionalities to manage a collection of {@linkplain Appointment appointments}
 * @author JoseNote
 * @version 1.00
 */
public final class Calendar {
    private final LinkedSortedAppendList<Appointment> list;

    /**
     * Empty constructor
     */
    public Calendar() {
        list = new LinkedSortedAppendList<Appointment>();
    }

    /**
     * Adds a new {@linkplain Appointment} instance to the set contained in this
     * @param element The new instance to be added
     */
    public void add(final Appointment element) {
        list.addSorted(element);
    }

    /**
     * Get all the {@linkplain Appointment} instances stored in this
     * @return An iterator associated to the instances stored in this
     */
    public SortedIterator<Appointment> get() {
        return list.iterator();
    }

    /**
     * Get the {@linkplain Appointment} instances stored in this 
     * that start before a given {@linkplain DateTime} instance
     * @param pointOfTime The given instance
     * @return An iterator associated to the set of {@linkplain Appointment} instances 
     * which start before the given {@linkplain DateTime} instance
     */
    public SortedIterator<Appointment> get(final DateTime pointOfTime) {
        final LinkedSortedAppendList<Appointment> filtered = new LinkedSortedAppendList<Appointment>();
        final SortedIterator<Appointment> it = get();
        Appointment current = null;

        while (it.hasNext()) {
            current = it.next();
            if (current.getFrom().isBefore(pointOfTime))
                filtered.addSorted(current);
        }
        return filtered.iterator();
    }

    /**
     * Get the {@linkplain Appointment} instances stored in this which start and end in a given day
     * @param date The day to match
     * @return An iterator associated to the set of {@linkplain Appointment} instances 
     * which start and end in the given day
     */
    public SortedIterator<Appointment> get(final Date date) {
        final LinkedSortedAppendList<Appointment> filtered = new LinkedSortedAppendList<Appointment>();
        final SortedIterator<Appointment> it = get();
        Appointment current = null;
        final DateTime min = date.atTime(new Time(0, 0, 0));
        final DateTime max = date.atTime(new Time(23, 59, 59));

        while (it.hasNext()) {
            current = it.next();
            if (current.getFrom().compareTo(min) >= 0 && current.getTo().compareTo(max) <= 0)
                filtered.addSorted(current);
        }
        return filtered.iterator();
    }

    /**
     * Get the {@linkplain Appointment} instances stored in this which are contained in a given time interval
     * @param start The start of the time interval
     * @param end The end of the time interval
     * @return An iterator associated to the set of {@linkplain Appointment} instances contained in the time interval
     */
    public SortedIterator<Appointment> get(final DateTime start, final DateTime end) {
        final LinkedSortedAppendList<Appointment> filtered = new LinkedSortedAppendList<Appointment>();
        final SortedIterator<Appointment> it = get();
        Appointment current = null;

        while (it.hasNext()) {
            current = it.next();
            if (current.getFrom().compareTo(start) >= 0 && current.getTo().compareTo(end) <= 0)
                filtered.addSorted(current);
        }
        return filtered.iterator();
    }

    /**
     * Searches in the stored {@linkplain Appointment} instances in this for one with a specific name.
     * @param name The name to match
     * @return A {@linkplain Appointment} instance with the given name. 
     * {@value Null} if such instance isn't stored in this
     */
    public Appointment get(final String name) {
        final SortedIterator<Appointment> it = get();
        Appointment current = null;

        while (it.hasNext()) {
            current = it.next();
            if (current.getName().equals(name))
                return current;
        }
        return null;
    }

    /**
     * Get the {@linkplain Appointment} instances stored in this that conflict 
     * with a given {@linkplain Appointment} instance, which is stored in this too. 
     * @param appointment The instance to match against
     * @return An iterator associated to the set of {@linkplain Appointment} instances which conflict with the given one
     */
    public SortedIterator<Appointment> get(final Appointment appointment) {
        final LinkedSortedAppendList<Appointment> filtered = new LinkedSortedAppendList<Appointment>();
        final SortedIterator<Appointment> it = get();
        Appointment current = null;

        while (it.hasNext()) {
            current = it.next();
            if (appointment.equals(current))
                //true: the given instance is the same as the actual instance of the loop.
                //An instance doesn't conflict with itself. Just skip
                continue;
            if ((current.getFrom().compareTo(appointment.getFrom()) >= 0 
                    && current.getFrom().compareTo(appointment.getTo()) <= 0)
                    || 
                    (current.getTo().compareTo(appointment.getFrom()) >= 0 
                    && current.getTo().compareTo(appointment.getTo()) <= 0))
                filtered.addSorted(current);
        }
        return filtered.iterator();
    }
}
