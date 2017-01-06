package main.java.sortedList;

public class LinkedSortedAppendList<T extends Comparable<T>> implements SortedAppendList<T> {
    private ListCell head;    
    
    public LinkedSortedAppendList() {
        head = null;
    }
    
    public void addSorted(T element) {
        if (head == null) {
            head = new ListCell(element, null, null);
        } else {
            
        }
    }

    public SortedIterator<T> iterator() {
        return new Iterator(head);
    }
    
    public class Iterator implements SortedIterator<T> {
        private ListCell cursor;
        
        private Iterator(ListCell start) {
            cursor = start;
        }
        
        public boolean hasNext() {
            return cursor.next != null;
        }

        public T next() {
            cursor = cursor.next;
            return cursor.value;
        }

    }  
    
    private class ListCell {
        private ListCell previous;
        private ListCell next;
        private T value;
        
        private ListCell(T value) {
            this.value = value;
            this.previous = this.next = this;
        }
        
        private ListCell(T value, ListCell previous, ListCell next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
}
