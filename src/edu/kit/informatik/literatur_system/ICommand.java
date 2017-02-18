package edu.kit.informatik.literatur_system;

import java.util.regex.Pattern;

/**
 * FIXME add doc
 * @author JoseNote
 * @version %I%, %G%
 * @param <T> TODO add doc
 */
public interface ICommand<T> {
    /**
     * Executes the command against the service
     * @param service FIXME add doc
     * @param input FIXME add doc
     */
    void execute(T service, String input);
    
    /**
     * Gets the regex pattern associated to the command
     * @return FIXME add doc
     */
    Pattern pattern();
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    boolean isQuit();
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    boolean printOkMessage();
}
