package main.java.sortedList;

public interface SortedIterator<T extends Comparable<T>> {
    public boolean hasNext();
    public T next();
}
