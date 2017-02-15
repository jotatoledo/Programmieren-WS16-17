package edu.kit.informatik.literatur_system;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 * @param <T> TODO add doc
 * @param <C> TODO add doc
 */
public final class CommandHandler<T, C extends ICommand<T>> {
    private final T target;
    private final ICommand<T>[] commands;
    
    private CommandHandler(final T target, final C[] commands) {
        //TODO remove dead code
//        for (final C c : commands)
//            c.getClass();
        this.target = target;
        this.commands = commands;
    }
    
    /**
     * TODO add doc
     * @param <T> TODO add doc
     * @param <C> TODO add doc
     * @param target TODO add doc
     * @param commands TODO add doc
     * @return TODO add doc
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
     * Executes the callback function if the command has no matches
     * @param string TODO add doc
     * @param callback TODO add doc
     * @return TODO add doc
     */
    public ICommand<T> accept(final String string, final Consumer<? super String> callback) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(callback);
        final Optional<ICommand<T>> cmd = find(string);
        if (cmd.isPresent())
            cmd.get().execute(target, string);
        else
            callback.accept(string);
        return cmd.orElse(null);
    }
    
    /**
     * Finds the command associated to the input.
     * Throws exception if there is ambiguity
     * @param string TODO add doc
     * @return TODO add doc
     */
    private Optional<ICommand<T>> find(final String string) {
        return commands()
                .filter(cmd -> cmd.pattern().matcher(string).matches())
                .reduce((l, r) -> { 
                    throw new IllegalStateException("ambiguous: '" + string + "'"); 
                });
        //TODO refactor into a single() extension method. 
        //The reduce API wasn't created for the used purpose
    }
    
    private Stream<ICommand<T>> commands() {
        return Stream.of(commands);
    }
}
