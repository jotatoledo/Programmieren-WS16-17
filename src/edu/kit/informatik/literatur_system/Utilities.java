package edu.kit.informatik.literatur_system;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Various utility functions
 * @author JoseNote
 * @version %I%, %G%
 */
public class Utilities {
    @SafeVarargs
    public static <T> Collection<T> intersectArgsCustomCollector(Collection<T>... collections) {
        return Arrays.stream(collections)
        .collect(intersecting());
    }
    
    private static <T, S extends Collection<T>> Collector<S, ?, Set<T>> intersecting() {
        class Acc {
            Set<T> result;

            void accept(S s) {
                if (result == null)
                    result = new HashSet<>(s);
                else result.retainAll(s);
            }

            Acc combine(Acc other) {
                if (result == null)
                    return other;
                if (other.result != null)
                    result.retainAll(other.result);
                return this;
            }
        }
        return Collector.of(Acc::new, Acc::accept, Acc::combine, 
                            acc -> acc.result == null ? Collections.emptySet() : acc.result, 
                            Collector.Characteristics.UNORDERED);
    }
    
    /**
     * TODO add doc
     * @param <T> TODO add doc
     * @param collections TODO add doc
     * @return TODO add doc
     */
    @SafeVarargs
    public static <T> Collection<T> intersectArgs(Collection<T>... collections) {
        return Arrays.stream(collections)
                .reduce((a, b) -> {
                    Set<T> c = new HashSet<>(a);
                    c.retainAll(b);
                    return c;
                }).orElseGet(HashSet::new);
    }
    
    public static <T> Set<T> intersectCollection(Collection<? extends Collection<T>> collections) {
        if (collections.isEmpty())
            return Collections.emptySet();
        Collection<T> smallest
            = Collections.min(collections, Comparator.comparingInt(Collection::size));
        return smallest.stream().distinct()
            .filter(t -> collections.stream().allMatch(c -> c == smallest || c.contains(t)))
            .collect(Collectors.toSet());
    }
    
    /**
     * Concatenates multiple generic collections into one list.
     * Doesn't remove repeated elements.
     * @param <T> TODO add doc
     * @param collections TODO add doc
     * @return TODO add doc
     */
    @SafeVarargs
    public static <T> Collection<T> unify(
            Collection<T>... collections) {
        return Arrays.stream(collections)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()); 
    }
    
    /** 
     * TODO add doc
     * @param <T> TODO add doc
     * @param collections TODO add doc
     * @return TODO add doc
     */
    @SafeVarargs
    public static <T> Collection<T> unifyNoRepetition(
            Collection<T>... collections) {
        return Arrays.stream(collections)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList()); 
    }
    
    /**
     * Joins multiple primitive type objects into a space separated string
     * @param values TODO add doc
     * @return TODO add doc
     */
    public static String listing(
            final Object... values) {
        return Stream.of(values).map(String::valueOf).collect(Collectors.joining(" "));
    }
    
    /**
     * TODO add doc
     * @param type TODO add doc
     * @param args TODO add doc
     * @return TODO add doc
     */
    public static IllegalArgumentException noSuch(
            final Class<?> type, final Object... args) {
        //TODO improve message
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
        //TODO improve message
        return new IllegalArgumentException(Stream.of(args).map(String::valueOf)
                .collect(Collectors.joining(", ", "exist already " + type.getSimpleName() + ": ", "")));
    }
}
