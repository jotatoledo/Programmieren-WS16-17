package edu.kit.informatik.calendar;

import edu.kit.informatik.Terminal;

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
            if (input.startsWith(CommandPrefix.QUIT.getRepresentation())) break;
            if (input.startsWith(CommandPrefix.ADD_APPOINTMENT.getRepresentation()))
                calManag.readAppointment(input);
                
        }
    }
    
    private void readAppointment(String input) {
        String[] splited = input.split(" ");
        
        String sub = input.substring(CommandPrefix.ADD_APPOINTMENT.getRepresentation().length() + 1);
        information.add(DateUtil.parseAppointment(sub));
    }
}
