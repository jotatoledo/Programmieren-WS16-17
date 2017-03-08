package edu.kit.informatik;

/**
 * An exception class used when a new entity with the primary key values of 
 * an entity already stored in the system is added
 * @author JoseNote
 * @version %I%, %G%
 */
public class EntityAlreadyExistsException extends IllegalArgumentException {
    private static final long serialVersionUID = 123456789L;

    /**
     * Creates a new instance
     */
    public EntityAlreadyExistsException() {
        super();
    }
    
    /**
     * Creates a new instance with the given error message
     * @param message the error message for this instance
     */
    public EntityAlreadyExistsException(final String message) {
        super(message);
    }
}
