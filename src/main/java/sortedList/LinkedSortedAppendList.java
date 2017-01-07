package main.java.sortedList;

/**
 * Implements a sorted double connected list
 * 
 * @author JoseNote
 *
 * @param <T>
 * @version 1.00
 */
public class LinkedSortedAppendList<T extends Comparable<T>> implements SortedAppendList<T> {
    private class ListCell<T> {
        private ListCell<T> previous;
        private ListCell<T> next;
        private T value;
        
        private ListCell(T value) {
            this.value = value;
            previous = next = null;
        }
        
        private ListCell(T value, ListCell<T> previous, ListCell<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
        
        public String toString() {
            StringBuilder builder = new StringBuilder();
            
            builder.append(previous == null ? "null" : previous.value)
            .append("<-").append(value).append("->")
            .append(next == null ? "null" : next.value);
            return builder.toString();
        }
    }
    
    private class Iterator implements SortedIterator<T> {
        private ListCell<T> cursor;
        
        private Iterator(ListCell<T> start) {
            cursor = start;
        }
        
        public boolean hasNext() {
            return cursor != null;
        }

        public T next() {
            T currentContent = cursor.value;
            cursor = cursor.next;
            return currentContent;
        }
        
        public String toString() {
            StringBuilder builder = new StringBuilder();
            
            if (cursor == null) return "null";
            else {
                builder.append(cursor.previous == null ? "null" : cursor.previous.value)
                .append("<-").append(cursor.value).append("->")
                .append(cursor.next == null ? "null" : cursor.next.value);
                return builder.toString();
            }
        }
    }  
    
    private ListCell<T> first; 
    private ListCell<T> last;
    
    public LinkedSortedAppendList() {
        first = last = null;        
    }
    
    public void addSorted(T element) {
        if (first == null) {
            first = last = new ListCell<T>(element);
        } else {
            ListCell<T> cursor = first;
            while (element.compareTo(cursor.value) > 0 
                    && cursor != last) cursor = cursor.next;
            if (element.compareTo(cursor.value) < 0) {
                //true: the new element is smaller than the current cursor element
                ListCell<T> newNode = new ListCell<T>(element, cursor.previous, cursor);
                cursor.previous = newNode;
                if (newNode.previous != null)newNode.previous.next = newNode;
                if (cursor == first) {
                    //true: head must be replaced
                    first = newNode;
                }
            } else {
                //the new element is bigger than or equal to the current cursor element
                ListCell<T> newNode = new ListCell<T>(element, cursor, cursor.next);
                cursor.next = newNode;
                if (newNode.next != null)newNode.next.previous = newNode;
                if (cursor == last) {
                    //true: the end must be replaced
                    last = newNode;                    
                }
            }            
        }
    }

    public SortedIterator<T> iterator() {
        return new Iterator(first);
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        SortedIterator<T> it = iterator();
        while (it.hasNext()) {
            T element = it.next();
            builder.append(element);
            if (it.hasNext())builder.append("-");
        }
        return builder.toString();
    }
}
