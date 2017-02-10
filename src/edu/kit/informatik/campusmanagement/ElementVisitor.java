
package edu.kit.informatik.campusmanagement;

/**
 * An element visitor.
 * 
 * @author  Tobias Bachert
 * @version 1.00, 2016/12/15
 * 
 * @param   <R> type of the result
 */
interface ElementVisitor<R> {
    
    /**
     * Visits the specified lecture.
     * 
     * @param  lecture the lecture
     * @return the result value
     */
    R visit(
            final Lecture lecture);
    
    /**
     * Visits the specified module.
     * 
     * @param  module the module
     * @return the result value
     */
    R visit(
            final Module module);
    
    /**
     * Visits the specified element.
     * 
     * @param  element the element
     * @return the return value
     */
    R visit(
            final Element element);
    
    /**
     * Visits the specified element.
     * 
     * @param  element the element
     * @return the return value
     */
    default R apply(
            final Element element) {
        ////
        return element.accept(this);
    }
    
    /**
     * Visitor implementation that forwards every type to the {@linkplain #visit(Element)} method.
     * 
     * @author  Tobias Bachert
     * @version 1.00, 2016/12/15
     * 
     * @param   <R> type of the result
     */
    interface SimpleVisitor<R> extends ElementVisitor<R> {
        
        @Override
        default R visit(
                final Lecture lecture) {
            ////
            return visit((Element) lecture);
        }
        
        @Override
        default R visit(
                final Module module) {
            ////
            return visit((Element) module);
        }
    }
}
