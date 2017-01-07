package edu.kit.informatik.calendar;

import main.java.sortedList.LinkedSortedAppendList;
import main.java.sortedList.SortedIterator;

/**
 * 
 * @author JoseNote
 * @version 1.00
 */
public class Calendar {
    private LinkedSortedAppendList<Appointment> list;
    
    /**
     * Empty constructor
     */
    public Calendar() {
        list = new LinkedSortedAppendList<Appointment>();
    }
    
    /**
     * 
     * @param element
     */
    public void add(Appointment element) {
        list.addSorted(element);
    }
    
    /**
     * 
     * @return
     */
    public SortedIterator<Appointment> get() {
        return list.iterator();
    }
    
    /**
     * 
     * @param pointOfTime
     * @return
     */
    public SortedIterator<Appointment> get(DateTime pointOfTime) {
        LinkedSortedAppendList<Appointment> filtered = new LinkedSortedAppendList<Appointment>();
        SortedIterator<Appointment> it = get();
        Appointment current = null;
        
        while (it.hasNext()) {
            current = it.next();
            if (current.getFrom().isBefore(pointOfTime))filtered.addSorted(current);
        }
        return filtered.iterator();
    }
    
    /**
     * 
     * @param date
     * @return
     */
    public SortedIterator<Appointment> get(Date date) {
        LinkedSortedAppendList<Appointment> filtered = new LinkedSortedAppendList<Appointment>();
        SortedIterator<Appointment> it = get();
        Appointment current = null;
        DateTime min = date.atTime(new Time(0, 0, 0));
        DateTime max = date.atTime(new Time(23, 59, 59));
        
        while (it.hasNext()) {
            current = it.next();
            if (current.getFrom().compareTo(min) >= 0 && current.getTo().compareTo(max) <= 0)
                filtered.addSorted(current);
        }
        return filtered.iterator();
    }
    
    /**
     * 
     * @param start
     * @param end
     * @return
     */
    public SortedIterator<Appointment> get(DateTime start, DateTime end) {
        LinkedSortedAppendList<Appointment> filtered = new LinkedSortedAppendList<Appointment>();
        SortedIterator<Appointment> it = get();
        Appointment current = null;
        
        while (it.hasNext()) {
            current = it.next();
            if (current.getFrom().compareTo(start) >= 0 && current.getTo().compareTo(end) <= 0)
                filtered.addSorted(current);
        }
        return filtered.iterator();
    }
    
    /**
     * 
     * @param name
     * @return
     */
    public Appointment get(String name) {
        Appointment current = null;
        SortedIterator<Appointment> it = get();
        
        while (it.hasNext()) {
            current = it.next();
            if (current.getName().equals(name)) return current;
        }
        return null;
    }
    
    /**
     * 
     * @param appointment
     * @return
     */
    public SortedIterator<Appointment> get(Appointment appointment) {
        LinkedSortedAppendList<Appointment> filtered = new LinkedSortedAppendList<Appointment>();
        SortedIterator<Appointment> it = get();
        Appointment current = null;
        
        while (it.hasNext()) {
            current = it.next();
            if (appointment.equals(current)) continue;
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
