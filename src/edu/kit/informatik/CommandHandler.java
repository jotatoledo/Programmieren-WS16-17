
package edu.kit.informatik;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * TODO add doc
 * @author JoseNote
 *
 * @param <T> TODO add doc
 * @param <C> TODO add doc
 */
public final class CommandHandler<T, C extends CommandHandler.Command<T>> {
    
    private final T target;
    private final C[] commands;
    
    private CommandHandler(T target, C[] commands) {
        for (final C c : commands)
            c.getClass();
        this.target = target;
        this.commands = commands;
    }
    
    @SafeVarargs
    public static <T, C extends Command<T>> CommandHandler<T, C> of(T target, C... commands) {
        Objects.requireNonNull(target);
        return new CommandHandler<>(target, commands.clone());
    }
//    
//    @SuppressWarnings("unchecked")
//    public static <T, C extends Command<T>> CommandHandler<T, C> of(T target, Stream<? extends C> commands) {
//        Objects.requireNonNull(target);
//        return new CommandHandler<>(target, (C[]) commands.toArray(Command[]::new));
//    }
//    
//    public C accept(String string) {
//        return accept(string, s -> new NoSuchElementException("Command '" + s + "'"));
//    }
    
    /**
     * Executes a given command
     * @param string the line input
     * @param callback the function to execute in case the command is invalid
     * @return TODO add doc
     */
    public C accept(String string, Consumer<? super String> callback) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(callback);
        final Optional<C> cmd = find(string);
        if (cmd.isPresent())
            cmd.get().apply(target, string);
        else
            callback.accept(string);
        return cmd.orElse(null);
    }
    
    private Stream<C> commands() {
        return Stream.of(commands);
    }
    
    /**
     * Finds the required command for the given input.
     * Throws exception if there is ambiguity
     * @param string
     * @return
     */
    private Optional<C> find(String string) {
        return commands()
                .filter(cmd -> cmd.pattern().matcher(string).matches())
                .reduce((l, r) -> { 
                    throw new IllegalStateException("ambiguous: '" + string + "'"); 
                });
        //TODO refactor into a single() extension method. 
        //The reduce API wasn't created for the used purpose
    }
    
    public interface Command<T> {
        
        void apply(T t, String string);
        
        Pattern pattern();
    }
}
