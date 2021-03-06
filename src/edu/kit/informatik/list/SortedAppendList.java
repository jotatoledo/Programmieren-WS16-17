package edu.kit.informatik.list;

import edu.kit.informatik.iterator.SortedIterator;

/**
 * Interface for a sorted list
 * 
 * @author JoseNote
 *
 * @param <T> A class that implements the interface {@linkplain Comparable}
 * @version 1.00
 */
public interface SortedAppendList<T extends Comparable<T>> {
    /**
     * Adds an element to the list
     * @param element The new element to be added
     */
    void addSorted(T element);
    
    /**
     * Creates an iterator for the list
     * @return An iterator associated to this list
     */
    SortedIterator<T> iterator();
}
