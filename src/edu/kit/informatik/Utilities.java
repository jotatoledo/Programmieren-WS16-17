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
     * @param <T> the type of the elements in the given collections
     * @param collections a collection of element collections
     * @return the collection resulting from the unification of the given collections, with possible repeated elements
     */
    @SafeVarargs
    public static <T> Collection<T> unify(
            Collection<T>... collections) {
        return Arrays.stream(collections)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()); 
    }
    
    /** 
     * Concatenates multiple generic collections into one list.
     * @param <T> the type of the elements in the given collections
     * @param collections a collection of element collections
     * @return the collection resulting from the unification of the given collections, with no repeated elements
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
     * Instantiates a new {@linkplain EntityAlreadyExistsException}
     * @param type the type corresponding to the already existing object
     * @param args extra parameters for the error message
     * @return a new exception instance
     */
    public static EntityAlreadyExistsException alreadyExist(
            final Class<?> type, final Object... args) {
        //TODO improve message
        return new EntityAlreadyExistsException(Stream.of(args).map(String::valueOf)
                .collect(Collectors.joining(", ", "exist already " + type.getSimpleName() + ": ", "")));
    }
    
    /**
     * Instantiates a new {@linkplain InvalidRelationException}.
     * It signalizes an invalid relation between 2 types.
     * @param firstType one of the types in the invalid relation
     * @param secondType the other type in the invalid relation
     * @return a new exception instance
     */
    public static InvalidRelationException invalidRelation(
            final Class<?> firstType, final Class<?> secondType) {
        return new InvalidRelationException("invalid relation between the given " + firstType.getSimpleName()
        + " and " + secondType.getSimpleName());
    }
    
    /**
     * Generates a matcher from a given pattern and string input
     * @param pattern the pattern to test against the input
     * @param input the input string
     * @return a matcher instance associated to the given pattern evaluated against the given input
     * @throws IllegalArgumentException if the input doesn't match the pattern
     */
    public static Matcher matcher(final Pattern pattern, final String input) {
        final Matcher m = pattern.matcher(input);
        if (!m.matches())
            throw new IllegalArgumentException("no match available");
        return m;
    }
    
    /**
     * Rounds a decimal number to 3 decimal positions.
     * @param value the value to round
     * @return the rounded value
     */
    public static double roundDown3(double value) {
        // What is just happening here? Go and ask stack overflow!
        return (long) (value * 1e3) / 1e3;
    }
    
    /**
     * Calculates the jaccard factor of two collections of words.
     * The calculation method is describe in the first final assignment, command C14
     * @param firstGroupWords a collection of words
     * @param secondGroupWords another collection of words
     * @return the jaccard factor of the two given collections
     */
    public static float jaccard(
            final Collection<String> firstGroupWords, final Collection<String> secondGroupWords) {
        Objects.requireNonNull(firstGroupWords);
        Objects.requireNonNull(secondGroupWords);
        // FIXME filter repeated in collections before calculations
        Collection<String> union = Utilities.unifyNoRepetition(firstGroupWords, secondGroupWords);
        // If the unified collections have no elements
        if (union.size() == 0)
            return 1;
        // FIXME re factor to improved intersect
        Collection<String> intersection = Utilities.intersectMultipleRetain(firstGroupWords, secondGroupWords);
        return ((float) intersection.size()) / union.size();
    }
    
    /**
     * Calculates the h-index factor for a collection of citation counts.
     * The calculation method is described in detail in the first final assignment, command C16
     * @param values a collection of citation numbers
     * @return the h-index factor for the given collection
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
     * Generates a collection of string elements separated by a delimiter in a given string
     * @param input the string that contains the elements
     * @param delimiter the delimiter between elements in the input string
     * @param start a starting index in the input string
     * @param end an ending index in the input string
     * @return a collection made of the separated elements
     */
    public static Collection<String> listElements(
            final String input, final String delimiter, 
            final int start, final int end) {
        return Arrays.asList(input.substring(start, end).split(delimiter));
    }
}
