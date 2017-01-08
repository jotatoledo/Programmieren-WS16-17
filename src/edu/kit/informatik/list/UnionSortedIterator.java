package edu.kit.informatik.list;

/**
 * 
 * @author JoseNote
 *
 * @param <T>
 * @version 1.00
 */
public final class UnionSortedIterator<T extends Comparable<T>> implements SortedIterator<T> {
    private final SortedIterator<T> iteratorA;
    private final SortedIterator<T> iteratorB;
    private T currentValueIteratorA;
    private T currentValueIteratorB;
    
    /**
     * 
     * @param iteratorA
     * @param iteratorB
     */
    public UnionSortedIterator(SortedIterator<T> iteratorA, SortedIterator<T> iteratorB) {
        this.iteratorA = iteratorA;
        this.iteratorB = iteratorB;
        currentValueIteratorA = currentValueIteratorB = null;
    }
    
    public boolean hasNext() {
        return iteratorA.hasNext() || iteratorB.hasNext();
    }

    public T next() {
        if (currentValueIteratorA == null) 
            if (iteratorA.hasNext()) 
                currentValueIteratorA = iteratorA.next();
        if (currentValueIteratorB == null) 
            if (iteratorB.hasNext()) 
                currentValueIteratorB = iteratorB.next();
        
        if (currentValueIteratorA == null && currentValueIteratorB == null)
            //true: both iterators are empty
            return null;
        else {
            //At least one of the iterators had a value
            T value = null;
            if (currentValueIteratorA == null) {
                value = currentValueIteratorB;
                currentValueIteratorB = null;
                return value;
            }  
            if (currentValueIteratorB == null) {
                value = currentValueIteratorA;
                currentValueIteratorA = null;
                return value;
            }
            if (currentValueIteratorA.compareTo(currentValueIteratorB) < 0) {
                value = currentValueIteratorA;
                currentValueIteratorA = null;
            } else {
                value = currentValueIteratorB;
                currentValueIteratorB = null;
            }
            return value;
        }        
    }

}
