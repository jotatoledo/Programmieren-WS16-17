package main.java.sortedList;

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
    public void addSorted(T element);
    
    /**
     * Creates an iterator for the list
     * @return An iterator associated to this list
     */
    public SortedIterator<T> iterator();
}
