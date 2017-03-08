package edu.kit.informatik.literatur_system;

import java.util.regex.Pattern;

/**
 * Contract for commands
 * @author JoseNote
 * @version %I%, %G%
 * @param <T> the type of the service against which the command will be executed
 */
public interface ICommand<T> {
    /**
     * Executes the command against a service
     * @param service the service to use for the command execution
     * @param input a CLI input
     */
    void execute(T service, String input);
    
    /**
     * Gets the REGEX pattern associated to the command
     * @return the pattern
     */
    Pattern pattern();
    
    /**
     * Checks if the program execution should be terminated when the command is invoked
     * @return {@code True} if program execution has to stop. {@code False} otherwise
     */
    boolean isQuit();
    
    /**
     * Checks if after the command execution an extra "ok" message should be displayed in console
     * @return {@code True} if the message is required. {@code False} otherwise
     */
    boolean okMessage();
}
