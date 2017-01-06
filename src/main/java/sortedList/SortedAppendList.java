package main.java.sortedList;

public interface SortedAppendList<T extends Comparable<T>> {
    public void addSorted(T element);
    public SortedIterator<T> iterator();
}
