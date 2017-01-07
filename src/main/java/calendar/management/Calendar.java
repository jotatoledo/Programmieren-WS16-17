package main.java.calendar.management;

import edu.kit.informatik.calendar.Appointment;
import edu.kit.informatik.calendar.Date;
import edu.kit.informatik.calendar.DateTime;
import edu.kit.informatik.calendar.Time;
import main.java.sortedList.LinkedSortedAppendList;
import main.java.sortedList.SortedIterator;

public class Calendar {
    private LinkedSortedAppendList<Appointment> list;
    
    public Calendar() {
        list = new LinkedSortedAppendList<Appointment>();
    }
    
    public void add(Appointment element) {
        list.addSorted(element);
    }
    
    public SortedIterator<Appointment> get() {
        return list.iterator();
    }
    
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
    
    public Appointment get(String name) {
        Appointment current = null;
        SortedIterator<Appointment> it = get();
        
        while (it.hasNext()) {
            current = it.next();
            if (current.getName().equals(name)) return current;
        }
        return null;
    }
    
    public SortedIterator<Appointment> get(Appointment appointment) {
        LinkedSortedAppendList<Appointment> filtered = new LinkedSortedAppendList<Appointment>();
        SortedIterator<Appointment> it = get();
        Appointment current = null;
        
        while (it.hasNext()) {
            current = it.next();
            if (current.getFrom().compareTo(appointment.getFrom()) >= 0 
                    || current.getTo().compareTo(appointment.getTo()) <= 0)
                filtered.addSorted(current);
        }
        return filtered.iterator();
    }
}
