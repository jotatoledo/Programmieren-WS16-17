
package edu.kit.informatik;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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
    
    @SuppressWarnings("unchecked")
    public static <T, C extends Command<T>> CommandHandler<T, C> of(T target, Stream<? extends C> commands) {
        Objects.requireNonNull(target);
        return new CommandHandler<>(target, (C[]) commands.toArray(Command[]::new));
    }
    
    public C accept(String string) {
        return accept(string, s -> new NoSuchElementException("Command '" + s + "'"));
    }
    
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
    
    public Stream<C> commands() {
        return Stream.of(commands);
    }
    
    private Optional<C> find(String string) {
        return commands().filter(cmd -> cmd.pattern().matcher(string).matches())
                .reduce((l, r) -> { throw new IllegalStateException("ambiguous: '" + string + "'"); });
    }
    
    public interface Command<T> {
        
        void apply(T t, String string);
        
        Pattern pattern();
    }
}
