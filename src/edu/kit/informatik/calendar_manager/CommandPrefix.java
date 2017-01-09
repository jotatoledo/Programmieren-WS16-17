package edu.kit.informatik.calendar_manager;

/**
 * An enumeration for the possible console commands used in {@linkplain CalendarManagement}
 * @author s_toledonavarro
 * @version 1.00
 */
public enum CommandPrefix {
    /**
     * Prefix for the quit command
     */
    QUIT("quit"),
    /**
     * Prefix for the add command
     */
    ADD_APPOINTMENT("add appointment"),
    /**
     * Prefix for the simple print command
     */
    PRINT_APPOINTMENTS("print appointments"),
    /**
     * Prefix for the print command of appointments that start before a given date
     */
    PRINT_APPOINTMENTS_BEFORE("print appointments that start before"),
    /**
     * Prefix for the print command of appointments on a given day
     */
    PRINT_APPOINTMENTS_ON("print appointments on"),
    /**
     * Prefix for the print command of appointments contained in a given time interval
     */
    PRINT_APPOINTMENTS_INTERVAL("print appointments in interval"),
    /**
     * Prefix for the print command of appointments that conflict with a given one
     */
    PRINT_APPOINTMENTS_CONFLICT("print appointments that conflict with");
    
    private final String representation;
        
    CommandPrefix(String representation) {
        this.representation = representation;
    }
    
    /**
     * The string representation of this
     * @return The value of the string {@linkplain CommandPrefix#representation representation} of this
     */
    public String getRepresentation() {
        return representation;
    }
}
