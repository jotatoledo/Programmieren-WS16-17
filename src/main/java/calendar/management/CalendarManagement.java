package main.java.calendar.management;

import edu.kit.informatik.Terminal;

public class CalendarManagement {
    private Calendar information;
    
    private CalendarManagement() {
        information = new Calendar();
    }
    
    public static void main(String[] args) {
        String input;
        while (true) {
            input = Terminal.readLine();
            if (input.startsWith(CommandPrefix.QUIT.getRepresentation())) break;
        }
    }
}
