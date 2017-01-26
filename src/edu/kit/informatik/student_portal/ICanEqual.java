package edu.kit.informatik.student_portal;

/**
 * Interface used to support the implementation of equality in class hierarchies
 * @author JoseNote
 * @version 1.00
 * @see <a href="http://www.artima.com/lejava/articles/equality.html">Background</a>
 */
public interface ICanEqual {
    /**
     * Support method for equality
     * @param obj TODO add doc
     * @return TODO add doc
     */
    boolean canEqual(Object obj);
}
