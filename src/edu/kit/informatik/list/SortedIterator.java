package edu.kit.informatik.list;

/**
 * Interface of a sorted iterator
 * 
 * @author JoseNote
 *
 * @param <T> A class that implements the interface {@linkplain Comparable}
 */
public interface SortedIterator<T extends Comparable<T>> {
    /**
     * Checks if the iterator has a next value
     * @return {@code True} if the iterator has a next element. {@code False} otherwise.
     */
    boolean hasNext();
    
    /**
     * Gets the next value and moves the iterator to the next element
     * @return The current {@linkplain T} value.
     */
    T next();
}
