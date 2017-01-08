package edu.kit.informatik.calendar;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.list.SortedIterator;

public class CalendarManagement {
    private Calendar information;
    
    private CalendarManagement() {
        information = new Calendar();
    }
    
    public static void main(String[] args) {
        CalendarManagement calManag = new CalendarManagement();
        String input;
        
        while (true) {
            input = Terminal.readLine();
            if (input.startsWith(CommandPrefix.QUIT.getRepresentation())) 
                break;
            if (input.startsWith(CommandPrefix.ADD_APPOINTMENT.getRepresentation()))
                calManag.addAppointment(input);            
            else if (input.startsWith(CommandPrefix.PRINT_APPOINTMENTS_BEFORE.getRepresentation())) 
                calManag.printAppointmentsBefore(input);
            else if (input.startsWith(CommandPrefix.PRINT_APPOINTMENTS_ON.getRepresentation())) 
                calManag.printAppointmentsOn(input);
            else if (input.startsWith(CommandPrefix.PRINT_APPOINTMENTS_INTERVAL.getRepresentation())) 
                calManag.printAppointmentsInInterval(input);
            else if (input.startsWith(CommandPrefix.PRINT_APPOINTMENTS_CONFLICT.getRepresentation())) 
                calManag.printAppointmentsConflict(input);
            else if (input.startsWith(CommandPrefix.PRINT_APPOINTMENTS.getRepresentation())) 
                calManag.printAppointments();
        }
    }
    
    /**
     * 
     * @param input
     */
    private void addAppointment(String input) {        
        String sub = input.substring(CommandPrefix.ADD_APPOINTMENT.getRepresentation().length() + 1);
        
        information.add(DateUtil.parseAppointment(sub));
    }
    
    /**
     * 
     */
    private void printAppointments() {
        SortedIterator<Appointment> it = information.get();
        
        while (it.hasNext()) {
            Terminal.printLine(it.next());
        }
    }
    
    /**
     * 
     * @param input
     */
    private void printAppointmentsBefore(String input) {
        String sub = input.substring(CommandPrefix.PRINT_APPOINTMENTS_BEFORE.getRepresentation().length() + 1);
        
        SortedIterator<Appointment> it = information.get(DateUtil.parseDateTime(sub));
        
        while (it.hasNext()) {
            Terminal.printLine(it.next());
        }
    }
    
    /**
     * 
     * @param input
     */
    private void printAppointmentsOn(String input) {
        String sub = input.substring(CommandPrefix.PRINT_APPOINTMENTS_ON.getRepresentation().length() + 1);
        
        SortedIterator<Appointment> it = information.get(DateUtil.parseDate(sub));
        
        while (it.hasNext()) {
            Terminal.printLine(it.next());
        }
    }
    
    /**
     * 
     * @param input
     */
    private void printAppointmentsInInterval(String input) {
        String sub = input.substring(CommandPrefix.PRINT_APPOINTMENTS_INTERVAL.getRepresentation().length() + 1);
        String[] splited = sub.split(" ");
        
        SortedIterator<Appointment> it = information
                .get(DateUtil.parseDateTime(splited[0]), DateUtil.parseDateTime(splited[1]));
        
        while (it.hasNext()) {
            Terminal.printLine(it.next());
        }
    }
    
    /**
     * 
     * @param input
     */
    private void printAppointmentsConflict(String input) {
        String sub = input.substring(CommandPrefix.PRINT_APPOINTMENTS_CONFLICT.getRepresentation().length() + 1);
        Appointment app = information.get(sub);
        
        if (app == null)Terminal.printLine("Appointment not found.");
        else {
            SortedIterator<Appointment> it = information.get(app);
            
            while (it.hasNext()) {
                Terminal.printLine(it.next());
            }
        } 
    }
}
