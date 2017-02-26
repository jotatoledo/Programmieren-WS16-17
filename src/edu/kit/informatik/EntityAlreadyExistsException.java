package edu.kit.informatik;

/**
 * FIXME add doc
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
