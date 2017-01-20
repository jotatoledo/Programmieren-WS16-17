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
    QUIT("programm_quit","programm_quit".length()),
    /**
     * Prefix for the add command
     */
    ADD_APPOINTMENT("add appointment","add appointment".length()),
    /**
     * Prefix for the simple print command
     */
    PRINT_APPOINTMENTS("print appointments","print appointments".length()),
    /**
     * Prefix for the print command of appointments that start before a given date
     */
    PRINT_APPOINTMENTS_BEFORE("print appointments that start before","print appointments that start before".length()),
    /**
     * Prefix for the print command of appointments on a given day
     */
    PRINT_APPOINTMENTS_ON("print appointments on","print appointments on".length()),
    /**
     * Prefix for the print command of appointments contained in a given time interval
     */
    PRINT_APPOINTMENTS_INTERVAL("print appointments in interval","print appointments in interval".length()),
    /**
     * Prefix for the print command of appointments that conflict with a given one
     */
    PRINT_APPOINTMENTS_CONFLICT("print appointments that conflict with","print appointments that conflict with".length());
    
    private final String prefix;
    private final int prefixLength;
        
    /**
     * Creates a new item
     * @param representation The command line prefix associated to the new element
     */
    CommandPrefix(final String representation,final int prefixLength) {
        this.prefix = representation;
        this.prefixLength = prefixLength;
    }
    
    /**
     * The string representation of this
     * @return The value of the string {@linkplain CommandPrefix#prefix representation} of this
     */
    public String getRepresentation() {
        return prefix;
    }
    
    public int getPrefixLength(){
    	return prefixLength;
    }
}
