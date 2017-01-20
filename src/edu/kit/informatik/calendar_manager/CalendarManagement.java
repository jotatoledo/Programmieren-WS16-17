package edu.kit.informatik.calendar_manager;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.calendar.Appointment;
import edu.kit.informatik.calendar.Date;
import edu.kit.informatik.calendar.DateUtil;
import edu.kit.informatik.iterator.SortedIterator;

/**
 * Emulates the behavior of a simple console-interface appointment manager
 * 
 * @author s_toledonavarro
 * @version 1.00
 */
public final class CalendarManagement {
    /**
     * Represents the information from a set of appointments
     */
    private final Calendar information;
    private final CommandPrefix terminateCommand;
    private final Date objectDate;
    private static final Date classDate = new Date(1,1,1);
    
    /**
     * Creates a new instance
     */
    private CalendarManagement() {
        information = new Calendar();
        terminateCommand = CommandPrefix.QUIT;
        objectDate = classDate.plusDays(1);
    }

    /**
     * Program start method
     * @param args execution arguments
     */
    public static void main(String[] args) {
        final CalendarManagement calManag = new CalendarManagement();
        String input;

        //The while loop goes on until the user executes the quit command
        while (true) {
            input = Terminal.readLine();
            if (input.startsWith(calManag.terminateCommand.getRepresentation()))
                //true: the user invoked the quit method
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
                //Note: the order of the conditional blocks is important
                //Starting with the last if would cause wrong behavior (more than 1 command will be executed).
                //Refactoring possible: add "continue" after the execution of every command (except quit)
                calManag.printAppointments();
        }
    }

    /**
     * Adds a new {@linkplain Appointment} instance by processing 
     * the user input when the command {@linkplain CommandPrefix#ADD_APPOINTMENT "add appointment"} is invoked
     * @param input The users input through the console
     */
    private void addAppointment(final String input) {        
        String sub = input.substring(CommandPrefix.ADD_APPOINTMENT.getRepresentation().length() + 1);

        information.add(DateUtil.parseAppointment(sub));
    }

    /**
     * Displays the current stored instances of {@linkplain Appointment} 
     */
    private void printAppointments() {
        SortedIterator<Appointment> it = information.get();

        while (it.hasNext()) {
            Terminal.printLine(it.next());
        }
    }

    /**
     * Processes the users input and displays a set of {@linkplain Appointment} 
     * instances located before a given point in time
     * @param input The users input through the console. 
     * Contains the {@code String} representation of a {@linkplain DateTime} instance
     */
    private void printAppointmentsBefore(final String input) {
        String sub = input.substring(CommandPrefix.PRINT_APPOINTMENTS_BEFORE.getRepresentation().length() + 1);        
        SortedIterator<Appointment> it = information.get(DateUtil.parseDateTime(sub));

        while (it.hasNext()) {
            Terminal.printLine(it.next());
        }
    }

    /**
     * Displays a set of stored {@linkplain Appointment} instances which start and end at a specific day
     * @param input The users input through the console. 
     * Contains the {@code String} representation of a {@linkplain Date} instance
     */
    private void printAppointmentsOn(final String input) {
        String sub = input.substring(CommandPrefix.PRINT_APPOINTMENTS_ON.getRepresentation().length() + 1);        
        SortedIterator<Appointment> it = information.get(DateUtil.parseDate(sub));

        while (it.hasNext()) {
            Terminal.printLine(it.next());
        }
    }

    /**
     * Displays a set of stored {@linkplain Appointment} instances which are contained in a specific time interval
     * @param input The users input through the console. 
     * Contains the {@code String} representation of two {@linkplain DateTime} instances
     */
    private void printAppointmentsInInterval(final String input) {
        String sub = input.substring(CommandPrefix.PRINT_APPOINTMENTS_INTERVAL.getRepresentation().length() + 1);
        String[] splited = sub.split(" ");        
        SortedIterator<Appointment> it = information
                .get(DateUtil.parseDateTime(splited[0]), DateUtil.parseDateTime(splited[1]));

        while (it.hasNext()) {
            Terminal.printLine(it.next());
        }
    }

    /**
     * Displays a set of {@linkplain Appointment} instances which are in conflict to another {@linkplain Appointment}
     * @param input The users input through the console. Contains the name of a {@linkplain Appointment} instance
     */
    private void printAppointmentsConflict(final String input) {
        String sub = input.substring(CommandPrefix.PRINT_APPOINTMENTS_CONFLICT.getRepresentation().length() + 1);
        Appointment app = information.get(sub);

        if (app == null)
            //true: no appointment instance with the given name was found
            Terminal.printLine("Appointment not found.");
        else {
            //an appointment instance with the given name was found
            SortedIterator<Appointment> it = information.get(app);

            while (it.hasNext()) {
                Terminal.printLine(it.next());
            }
        } 
    }
}
