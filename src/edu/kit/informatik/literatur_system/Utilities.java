package edu.kit.informatik.literatur_system;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        //TODO check creation of new list
        return Arrays.stream(collections)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()); 
    }
}
