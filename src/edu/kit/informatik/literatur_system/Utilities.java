package edu.kit.informatik.literatur_system;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Various generic utility functions
 * @author JoseNote
 * @version %I%, %G%
 */
public class Utilities {
    /**
     * Concatenates multiple generic collections into one list.
     * Doesn't remove repeated elements.
     * @param <T> TODO add doc
     * @param collections TODO add doc
     * @return TODO add doc
     */
    @SafeVarargs
    public static <T> List<T> concatenatedList(Collection<T>... collections) {
        return Arrays.stream(collections)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()); 
    }
    
    /**
     * TODO add doc
     * @param type TODO add doc
     * @param args TODO add doc
     * @return TODO add doc
     */
    public static IllegalArgumentException noSuch(
            final Class<?> type, final Object... args) {
        //TODO refactor concat
        return new IllegalArgumentException(Stream.of(args).map(String::valueOf)
                .collect(Collectors.joining(", ", "No such " + type.getSimpleName() + ": ", "")));
    }
    
    /**
     * TODO add doc
     * @param type TODO add doc
     * @param args TODO add doc
     * @return TODO add doc
     */
    public static IllegalArgumentException alreadyExist(
            final Class<?> type, final Object... args) {
        //TODO refactor concat
        return new IllegalArgumentException(Stream.of(args).map(String::valueOf)
                .collect(Collectors.joining(", ", "exist already " + type.getSimpleName() + ": ", "")));
    }
}
