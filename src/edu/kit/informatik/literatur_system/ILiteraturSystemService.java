package edu.kit.informatik.literatur_system;

/**
 * Contract for the literature system service
 * @author JoseNote
 * @version %I%, %G%
 */
public interface ILiteraturSystemService {
    /**
     * Creates a new author entity and persists it in the service
     * @param firstName the first name value for the new entity
     * @param lastName the last name value for the new entity
     * @return the new entity
     */
    Author addAuthor(String firstName, String lastName);
    
    /**
     * Checks for the existence of an author entity
     * @param firstName the first name value to match against
     * @param lastName the last name value to match against
     * @return {@code True} if there is an author entity with the given parameters. {@code False} otherwise
     */
    boolean existAuthor(String firstName, String lastName);
    
    /**
     * Creates a new journal entity and persists it in the service
     * @param name the name value for the new entity
     * @param publisher the publisher value for the new entity
     * @return the new entity
     */
    Journal addJournal(String name, String publisher);
    
    /**
     * Checks for the existence of a journal entity
     * @param name the name value to match against
     * @return {@code True} if there is a journal entity with the given parameters. {@code False} otherwise
     */
    boolean existJournal(String name);
}
