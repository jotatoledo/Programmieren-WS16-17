package edu.kit.informatik;

/**
 * An exception triggered an entity that doesn't exist is "retrieved" by primary key
 * @author JoseNote
 * @version %I%, %G%
 */
public class NoSuchEntityException extends IllegalArgumentException {
    private static final long serialVersionUID = 1997753363232807009L;
    
    /**
     * Creates a new instance
     */
    public NoSuchEntityException() {
        super();
    }
    
    /**
     * Creates a new instance with the given error message
     * @param message the error message for this instance
     */
    public NoSuchEntityException(final String message) {
        super(message);
    }
}
