
package edu.kit.informatik.campusmanagement;

import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * A module of a campus management system.
 * 
 * @author  Tobias Bachert
 * @version 1.00, 2016/08/11
 */
public final class Module extends Element {
    
    private static final int UPPER_BOUND = 45;
    
    private final Collection<Lecture> lectures = new TreeSet<>();
    
    /**
     * Creates a new module with the given name.
     * 
     * @param id the id of the module
     * @param name the name
     */
    public Module(
            final int id,
            final String name) {
        ////
        super(id, name);
    }
    
    /**
     * Adds a lecture to the module.
     * 
     * @param  lecture the lecture to add
     * @throws IllegalStateException if the lecture would push the count of points over {@code 45}
     */
    void addLecture(
            final Lecture lecture) {
        ////
        if (lecture.module() != this)
            throw new IllegalArgumentException("Lecture does not belong to this module");
        if (credits() + lecture.credits() > UPPER_BOUND)
            throw new IllegalStateException("Total credit points over " + UPPER_BOUND);
        lectures.add(lecture);
    }
    
    /**
     * Returns a stream containing the lectures.
     * 
     * @return the lectures
     */
    public Stream<Lecture> lectures() {
        ////
        return lectures.stream();
    }
    
    /**
     * Returns the count of credits.
     * 
     * @return the credits
     */
    public int credits() {
        ////
        return lectures.stream().mapToInt(Lecture::credits).sum();
    }
    
    /**
     * Returns the average grade for this module.
     * 
     * @return the grade
     */
    public Grade grade() {
        ////
        return Grade.average(lectures(), Lecture::grade, Lecture::credits);
    }
    
    @Override
    public <R> R accept(
            final ElementVisitor<R> visitor) {
        ////
        return visitor.visit(this);
    }
}
