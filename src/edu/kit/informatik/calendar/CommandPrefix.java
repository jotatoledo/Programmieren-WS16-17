package edu.kit.informatik.calendar;

public enum CommandPrefix {
    QUIT("quit"),
    ADD_APPOINTMENT("add appointment"),
    PRINT_APPOINTMENTS("print appointments"),
    PRINT_APPOINTMENTS_BEFORE("print appointments that start before"),
    PRINT_APPOINTMENTS_ON("print appointments on"),
    PRINT_APPOINTMENTS_INTERVAL("print appointments in interval"),
    PRINT_APPOINTMENTS_CONFLICT("print appointments that conflict with");
    
    private final String representation;
        
    CommandPrefix(String representation) {
        this.representation = representation;
    }
    
    public String getRepresentation() {
        return representation;
    }
}
