package edu.kit.informatik.literatur_system;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Various utility functions
 * @author JoseNote
 * @version %I%, %G%
 */
public class Utilities {
//    /**
//     * TODO add doc
//     * @param <T> TODO add doc
//     * @param collections TODO add doc
//     * @return TODO add doc
//     */
//    public static <T> Set<T> intersect(Collection<? extends Collection<T>> collections) {
//        Set<T> common = new LinkedHashSet<T>();
//        if (!collections.isEmpty()) {
//           Iterator<? extends Collection<T>> iterator = collections.iterator();
//           common.addAll(iterator.next());
//           while (iterator.hasNext()) {
//              common.retainAll(iterator.next());
//           }
//        }
//        return common;
//    }
    
    /**
     * TODO add doc
     * @param <T> TODO add doc
     * @param collections TODO add doc
     * @return TODO add doc
     */
    @SafeVarargs
    public static <T> Collection<T> intersect(Collection<T>... collections) {
        return Arrays.stream(collections)
                .reduce((a, b) -> {
                    Set<T> c = new HashSet<>(a);
                    c.retainAll(b);
                    return c;
                }).orElseGet(HashSet::new);
        
//        return Arrays.stream(collections)
//                .reduce(new HashSet<T>(),
//                        (a, b) -> {
//                            ((HashSet) a).retainAll(b);
//                            return a;
//                        });
    }
    
    /**
     * Concatenates multiple generic collections into one list.
     * Doesn't remove repeated elements.
     * @param <T> TODO add doc
     * @param collections TODO add doc
     * @return TODO add doc
     */
    @SafeVarargs
    public static <T> List<T> unify(
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
    public static <T> List<T> unifyNoRepetition(
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
