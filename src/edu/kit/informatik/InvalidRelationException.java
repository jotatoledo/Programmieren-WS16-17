package edu.kit.informatik;

/**
 * An exception triggered when a new invalid relation between 2 or more entities is persisted
 * @author JoseNote
 * @version %I%, %G%
 */
public class InvalidRelationException extends IllegalArgumentException {
    private static final long serialVersionUID = 987654321L;

    /**
     * Creates a new instance
     */
    public InvalidRelationException() {
        super();
    }
    
    /**
     * Creates a new instance with the given error message
     * @param message the error message for this instance
     */
    public InvalidRelationException(final String message) {
        super(message);
    }
}
