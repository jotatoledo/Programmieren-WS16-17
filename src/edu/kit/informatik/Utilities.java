package edu.kit.informatik;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.kit.informatik.literatur_system.AuthorNames;
import edu.kit.informatik.literatur_system.ConferenceArticleBibliography;
import edu.kit.informatik.literatur_system.JournalArticleBibliography;

/**
 * Various utility functions
 * @author JoseNote
 * @version %I%, %G%
 */
public class Utilities {
    /**
     * TODO add doc
     * @param <T> TODO add doc
     * @param collections TODO add doc
     * @return TODO add doc
     */
    @SafeVarargs
    public static <T> Collection<T> intersectCustomCollector(Collection<T>... collections) {
        return Arrays.stream(collections)
        .collect(intersecting());
    }
    
    private static <T, S extends Collection<T>> Collector<S, ?, Set<T>> intersecting() {
         class Acc {
            Set<T> result;

            private void accept(S s) {
                if (result == null)
                    result = new HashSet<>(s);
                else result.retainAll(s);
            }

            private Acc combine(Acc other) {
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
    public static <T> Collection<T> intersectMultipleRetain(Collection<T>... collections) {
        return Arrays.stream(collections)
                .reduce((a, b) -> {
                    Set<T> c = new HashSet<>(a);
                    c.retainAll(b);
                    return c;
                }).orElseGet(HashSet::new);
    }
    
    /**
     * TODO add doc
     * @param <T> TODO add doc
     * @param collections TODO add doc
     * @return TODO add doc
     */
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
                .collect(Collectors.joining(", ", "no such " + type.getSimpleName() + ": ", "")));
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
    
    /**
     * FIXME add doc
     * @param pattern FIXME add doc
     * @param input FIXME add doc
     * @return FIXME add doc
     */
    public static Matcher matcher(final Pattern pattern, final String input) {
        final Matcher m = pattern.matcher(input);
        if (!m.matches())
            throw new IllegalStateException("no match available");
        return m;
    }
    
    /**
     * FIXME add doc 
     * @param d FIXME add doc
     * @return FIXME add doc
     */
    public static double roundDown3(double d) {
        return (long) (d * 1e3) / 1e3;
    }
    
    /**
     * FIXME add doc
     * C14
     * @param firstGroupWords FIXME add doc
     * @param secondGroupWords FIXME add doc
     * @return FIXME add doc
     */
    public static float jaccard(
            final Collection<String> firstGroupWords, final Collection<String> secondGroupWords) {
        Objects.requireNonNull(firstGroupWords);
        Objects.requireNonNull(secondGroupWords);
        Collection<String> union = Utilities.unifyNoRepetition(firstGroupWords, secondGroupWords);
        if (union.size() == 0)
            return 1;
        //FIXME refactor to improved intersect
        Collection<String> intersection = Utilities.intersectMultipleRetain(firstGroupWords, secondGroupWords);
        return ((float) intersection.size()) / union.size();
    }
    
    /**
     * FIXME add doc
     * C16
     * @param values FIXME add doc
     * @return FIXME add doc
     */
    public static int directHIndex(final Collection<Integer> values) {
        //FIXME implement
        return 0;
    }
}
