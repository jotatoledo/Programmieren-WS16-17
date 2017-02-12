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
     * Checks for the existence of an author
     * @param firstName the first name value to match against
     * @param lastName the last name value to match against
     * @return {@code True} if there is an author with the given parameters. {@code False} otherwise
     */
    boolean existAuthor(String firstName, String lastName);
}
