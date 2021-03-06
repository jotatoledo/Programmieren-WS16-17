package edu.kit.informatik.list;

import edu.kit.informatik.iterator.SortedIterator;

/**
 * Implements a sorted double connected generic list
 * 
 * @author JoseNote
 *
 * @param <T> A class that implements the interface {@linkplain Comparable}
 * @version 1.00
 */
public class LinkedSortedAppendList<T extends Comparable<T>> implements SortedAppendList<T> {
    /**
     * Represents the start of the list, the first element
     */
    private ListCell first; 
    /**
     * Represents the end of the list, the last element
     */
    private ListCell last;
    
    /**
     * Creates a new empty list
     */
    public LinkedSortedAppendList() {
        first = null;
        last = null;        
    }
    
    @Override
    public void addSorted(T element) {
        if (first == null) {
            //true: the list is empty
            first = new ListCell(element);
            last = first;
        } else {
            ListCell cursor = first;
            while (element.compareTo(cursor.value) > 0 
                    && cursor != last) cursor = cursor.next;
            if (element.compareTo(cursor.value) < 0) {
                //true: the new element is smaller than the current cursor element
                ListCell newNode = new ListCell(element, cursor.previous, cursor);
                cursor.previous = newNode;
                if (newNode.previous != null)newNode.previous.next = newNode;
                if (cursor == first) {
                    //true: head must be replaced
                    first = newNode;
                }
            } else {
                //the new element is bigger than or equal to the current cursor element
                ListCell newNode = new ListCell(element, cursor, cursor.next);
                cursor.next = newNode;
                if (newNode.next != null)newNode.next.previous = newNode;
                if (cursor == last) {
                    //true: the end must be replaced
                    last = newNode;                    
                }
            }            
        }
    }

    @Override
    public SortedIterator<T> iterator() {
        return new Iterator(first);
    }
    
    /**
     * An inner class used to represent the nodes contained in the double connected list
     *
     * @author JoseNote
     *
     * @param <T> A class that implements the interface {@linkplain Comparable}.
     */
    private final class ListCell {
        private ListCell previous;
        private ListCell next;
        private T value;
        
        /**
         * Creates a new instance
         * @param value The value for this instance
         */
        private ListCell(T value) {
            this.value = value;
            previous =  null;
            next = null;
        }
        
        /**
         * Creates a new instance associated to two other instances
         * @param value The value for this instance
         * @param previous A pointer to another instance
         * @param next A pointer to another instance
         */
        private ListCell(T value, ListCell previous, ListCell next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
    
    /**
     * An inner class used to represent an iterator associated to {@linkplain LinkedSortedAppendList}
     * @author JoseNote
     * @version 1.00
     */
    private final class Iterator implements SortedIterator<T> {
        /**
         * A pointer to the current element of the iterator
         */
        private ListCell cursor;
        
        /**
         * Creates a new iterator associated to a list
         * @param start The start element of a list
         */
        private Iterator(ListCell start) {
            cursor = start;
        }
        
        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            T currentContent = cursor.value;
            cursor = cursor.next;
            return currentContent;
        }
    }  
}
