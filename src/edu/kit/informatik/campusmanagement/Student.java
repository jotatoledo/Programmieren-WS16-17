
package edu.kit.informatik.campusmanagement;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * A student of a campus management system.
 * 
 * @author  Tobias Bachert
 * @version 1.00, 2016/08/11
 */
public final class Student extends User implements Comparable<Student> {
    
    private final int studentnumber;
    private final Map<Module, Set<Lecture>> grades = new TreeMap<>();
    
    /**
     * Creates a new {@code Student} with the provided arguments.
     * 
     * @param forename the forename
     * @param surname the surname
     * @param studentnumber the student number
     */
    /*pkg*/ Student(
            final String forename,
            final String surname,
            final int studentnumber) {
        ////
        super(forename, surname);
        this.studentnumber = studentnumber;
    }
    
    /**
     * Adds the lecture to the student.
     * 
     * @param  lecture the lecture to add
     * @throws java.util.NoSuchElementException if the student has no grade set in the lecture
     * @throws IllegalStateException if the lecture was already added
     */
    void add(
            final Lecture lecture) {
        ////
        lecture.grade(this); // throws NoSuchElementException if required
        if (!grades.computeIfAbsent(lecture.module(), (module) -> new TreeSet<>()).add(lecture)) {
            throw new IllegalStateException("lecture already added");
        }
    }
    
    /**
     * Returns the student number.
     * 
     * @return the student number
     */
    public int studentnumber() {
        ////
        return studentnumber;
    }
    
    /**
     * Returns a stream containing the lectures.
     * 
     * @return the elements
     */
    public Stream<Lecture> lectures() {
        ////
        return grades.values().stream().flatMap(Collection::stream);
    }
    
    /**
     * Returns the average grade of this student.
     * 
     * @return the grade
     */
    public Grade grade() {
        ////
        return Grade.average(grades.keySet(), this::grade, Module::credits);
    }
    
    /**
     * Returns the average grade of this student in the specified module.
     * 
     * @param  module the module
     * @return the grade
     */
    public Grade grade(
            final Module module) {
        ////
        return Grade.average(grades.get(module), this::grade, Lecture::credits);
    }
    
    /**
     * Returns the grade of this student in the specified lecture.
     * 
     * @param  lecture the lecture
     * @return the grade
     */
    public Grade grade(
            final Lecture lecture) {
        ////
        return lecture.grade(this);
    }
    
    @Override
    public int compareTo(
            final Student other) {
        ////
        return Integer.compare(studentnumber(), other.studentnumber());
    }
    
    @Override
    public boolean equals(
            final Object obj) {
        ////
        return obj instanceof Student && studentnumber() == ((Student) obj).studentnumber();
    }
    
    @Override
    public int hashCode() {
        ////
        return studentnumber();
    }
    
    @Override
    public String toString() {
        ////
        return "Student[" + surname() + "," + forename() + ":" + studentnumber() + "]";
    }
}
