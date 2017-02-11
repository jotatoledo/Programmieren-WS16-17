package edu.kit.informatik.literatur_system;

import java.util.regex.Pattern;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 * @param <T> TODO add doc
 */
public interface ICommand<T> {
    /**
     * Executes the command against the service
     * @param service TODO add doc
     * @param string TODO add doc
     */
    void execute(T service, String string);
    
    /**
     * Gets the regex pattern associated to the command
     * @return TODO add doc
     */
    Pattern pattern();
}
