package main.java.sortedList;

/**
 * Interface of a sorted iterator
 * @author JoseNote
 *
 * @param <T>
 */
public interface SortedIterator<T extends Comparable<T>> {
    /**
     * Checks if the iterator has a next value
     * @return {@code True} if the iterator has a next element. {@code False} otherwise.
     */
    public boolean hasNext();
    
    /**
     * Gets the next value and moves the iterator to the next element
     * @return The current {@linkplain T} value.
     */
    public T next();
}
