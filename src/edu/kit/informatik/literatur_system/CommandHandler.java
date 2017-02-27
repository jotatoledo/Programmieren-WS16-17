package edu.kit.informatik.literatur_system;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A bridge class between the system service and the CLI
 * @author JoseNote
 * @version %I%, %G%
 * @param <T> the type of the service
 * @param <C> the type of the command enumeration
 */
public final class CommandHandler<T, C extends ICommand<T>> {
    private final T target;
    private final ICommand<T>[] commands;
    
    private CommandHandler(final T target, final ICommand<T>[] commands) {
        this.target = target;
        this.commands = commands;
    }
    
    /**
     * Factory method to create a new instance for the given service instance and the given collection
     * of commands
     * @param <T> the type of the service
     * @param <C> the type of the command enumeration
     * @param target the service instance
     * @param commands the collection of commands
     * @return a new handler instance
     */
    @SafeVarargs
    public static <T, C extends ICommand<T>> CommandHandler<T, ICommand<T>> createFor(
            final T target, 
            final C... commands) {
        Objects.requireNonNull(target);
        return new CommandHandler<>(target, commands);
    }
    
    /**
     * Tries to execute the command line.
     * @param input the CLI input
     * @return the command associated to the input in the CLI
     * @throws IllegalArgumentException if the given command isn't valid
     */
    public ICommand<T> accept(final String input) {
        Objects.requireNonNull(input);
        final Optional<ICommand<T>> cmd = find(input);
        if (!cmd.isPresent())
            throw new IllegalArgumentException("no such command: '" + input + "'");            
        cmd.get().execute(target, input);
        return cmd.get();
    }
    
    /**
     * Finds the command associated to the input.
     * Throws exception if there is ambiguity
     * @param string the CLI input
     * @return a possible match for the command typed in the CLI
     * @throws IllegalStateException if the given command is ambiguous to other
     */
    private Optional<ICommand<T>> find(final String string) {
        return commands()
                .filter(cmd -> cmd.pattern().matcher(string).matches())
                .reduce((l, r) -> { 
                    throw new IllegalStateException("ambiguous: '" + string + "'"); 
                });
        // TODO refactor into a single() extension method. 
        // The reduce API wasn't created for the used purpose
    }
    
    /**
     * Transform the command collection into a stream.
     * Support method for {@linkplain #find(String)}.
     * @return a stream with the commands
     */
    private Stream<ICommand<T>> commands() {
        return Stream.of(commands);
    }
}