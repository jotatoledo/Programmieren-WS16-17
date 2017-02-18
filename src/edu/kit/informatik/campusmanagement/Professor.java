
package edu.kit.informatik.campusmanagement;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * A professor of a campus management system.
 * 
 * @author  Tobias Bachert
 * @version 1.00, 2016/08/11
 */
public final class Professor extends User implements Comparable<Professor> {
    
    private final String chair;
    private final Collection<Lecture> lectures = new TreeSet<>();
    
    /**
     * Creates a new {@code Professor} with the provided arguments.
     * 
     * @param forename the forename
     * @param surname the surname
     * @param chair the chair
     */
    Professor(
            final String forename,
            final String surname,
            final String chair) {
        ////
        super(forename, surname);
        this.chair = chair;
    }
    
    /**
     * Adds the lecture to the professor.
     * 
     * @param  lecture the lecture to add
     * @throws IllegalStateException if the lecture does not belong to this professor or if the lecture was already
     *         added
     */
    void add(
            final Lecture lecture) {
        ////
        if (lecture.professor() != this) {
            throw new IllegalStateException("Lecture assigned to another professor");
        }
        if (!lectures.add(lecture)) {
            throw new IllegalStateException("Lecture already added");
        }
    }
    
    /**
     * Returns the chair of the professor.
     * 
     * @return the chair
     */
    public String chair() {
        ////
        return chair;
    }
    
    /**
     * Returns a stream containing the lectures.
     * 
     * @return the elements
     */
    public Stream<Lecture> lectures() {
        ////
        return lectures.stream();
    }
    
    @Override
    public int compareTo(
            final Professor other) {
        ////
        return Comparator.comparing(Professor::forename)
                .thenComparing(Professor::surname)
                .thenComparing(Professor::chair)
                .compare(this, other);
    }
    
    @Override
    public boolean equals(
            final Object obj) {
        ////
        if (obj instanceof Professor) {
            final Professor other = (Professor) obj;
            if (Objects.equals(forename(), other.forename())
                    && Objects.equals(surname(), other.surname())
                    && Objects.equals(chair(), other.chair())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        ////
        return Objects.hash(forename(), surname(), chair());
    }
    
    /**
     * Returns the average grade for lectures hold by this professor.
     * 
     * @return the grade
     */
    public Grade grade() {
        ////
        return Grade.average(lectures(), Lecture::grade, Lecture::credits);
    }
    
    @Override
    public String toString() {
        ////
        return "Professor[" + surname() + "," + forename() + ":" + chair() + "]";
    }
}
