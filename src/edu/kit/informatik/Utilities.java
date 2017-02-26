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

/**
 * Various utility functions
 * @author JoseNote
 * @version %I%, %G%
 */
public class Utilities {
    /**
     * FIXME add doc
     * @param <T> FIXME add doc
     * @param collections FIXME add doc
     * @return FIXME add doc
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
     * FIXME add doc
     * @param <T> FIXME add doc
     * @param collections FIXME add doc
     * @return FIXME add doc
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
     * FIXME add doc
     * @param <T> FIXME add doc
     * @param collections FIXME add doc
     * @return FIXME add doc
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
     * @param <T> FIXME add doc
     * @param collections FIXME add doc
     * @return FIXME add doc
     */
    @SafeVarargs
    public static <T> Collection<T> unify(
            Collection<T>... collections) {
        return Arrays.stream(collections)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()); 
    }
    
    /** 
     * FIXME add doc
     * @param <T> FIXME add doc
     * @param collections FIXME add doc
     * @return FIXME add doc
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
     * @param values FIXME add doc
     * @return FIXME add doc
     */
    public static String listing(
            final Object... values) {
        return Stream.of(values).map(String::valueOf).collect(Collectors.joining(" "));
    }
    
    /**
     * FIXME add doc
     * @param type FIXME add doc
     * @param args FIXME add doc
     * @return FIXME add doc
     */
    public static NoSuchEntityException noSuch(
            final Class<?> type, final Object... args) {
        //TODO improve message
        return new NoSuchEntityException(Stream.of(args).map(String::valueOf)
                .collect(Collectors.joining(", ", "no such " + type.getSimpleName() + ": ", "")));
    }
    
    /**
     * FIXME add doc
     * @param type FIXME add doc
     * @param args FIXME add doc
     * @return FIXME add doc
     */
    public static EntityAlreadyExistsException alreadyExist(
            final Class<?> type, final Object... args) {
        //TODO improve message
        return new EntityAlreadyExistsException(Stream.of(args).map(String::valueOf)
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
        // FIXME filter repeated in collections before calculations
        Collection<String> union = Utilities.unifyNoRepetition(firstGroupWords, secondGroupWords);
        if (union.size() == 0)
            return 1;
        // FIXME refactor to improved intersect
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
        int hIndex = 0;
        final List<Integer> sortedValues = values.stream()
            .sorted((v1, v2) -> Integer.compare(v2, v1))
            .collect(Collectors.toList());
        for (int i = 0; i < sortedValues.size(); i++) {
            if (sortedValues.get(i) < i + 1) {
                hIndex = i;
                break;
            }  
        }
        return hIndex;
    }
    
    /**
     * FIXME add doc
     * @param input FIXME add doc
     * @param delimiter FIXME add doc
     * @param start FIXME add doc
     * @param end FIXME add doc
     * @return FIXME add doc
     */
    public static Collection<String> listElements(
            final String input, final String delimiter, 
            final int start, final int end) {
        return Arrays.asList(input.substring(start, end).split(delimiter));
    }
}
