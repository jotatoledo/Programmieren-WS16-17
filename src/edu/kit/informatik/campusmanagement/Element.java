
package edu.kit.informatik.campusmanagement;

/**
 * An element of a campus management system.
 * 
 * @author  Tobias Bachert
 * @version 1.01, 2016/12/15
 */
abstract class Element implements Comparable<Element> {
    
    private final int id;
    private final String name;
    
    /**
     * Creates a new element with the specified name.
     * 
     * @param  id the identifier
     * @param  name the name
     * @throws NullPointerException if {@code name} is {@code null}
     */
    protected Element(
            final int id,
            final String name) {
        ////
        this.id   = id;
        this.name = name;
    }
    
    /**
     * Visits this element with the specified visitor and returns the result.
     * 
     * <p>This method has to be implemented as following:
     * <blockquote><pre>
     * visitor.visit(this);</pre>
     * </blockquote>
     * 
     * @param  <R> return type parameter
     * @param  visitor the visitor
     * @return the result
     * @throws NullPointerException if visitor is {@code null}
     */
    abstract <R> R accept(
            final ElementVisitor<R> visitor);
    
    /**
     * Returns the id of the lecture.
     * 
     * @return the id
     */
    public final int id() {
        ////
        return id;
    }
    
    /**
     * Returns the name of the element.
     * 
     * @return the name
     */
    public final String name() {
        ////
        return name;
    }
    
    @Override
    public final int compareTo(
            final Element other) {
        ////
        return Integer.compare(id, other.id);
    }
    
    @Override
    public final boolean equals(
            final Object obj) {
        ////
        return obj instanceof Element && id == ((Element) obj).id;
    }
    
    @Override
    public final int hashCode() {
        ////
        return Integer.hashCode(id);
    }
    
    @Override
    public String toString() {
        ////
        return getClass().getSimpleName() + "[" + id + "=" + name + "]";
    }
}
