
package edu.kit.informatik.campusmanagement;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * A lecture of a campus management system.
 * 
 * @author  Tobias Bachert
 * @version 1.00, 2016/08/11
 */
public final class Lecture extends Element {
    
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 9;
    
    private final Module module;
    private final int credits;
    private final Professor professor;
    private final Map<Student, Grade> grades = new TreeMap<>();
    
    /**
     * Creates a new {@code Lecture} with the provided arguments.
     * 
     * @param id the id of the lecture
     * @param name the name of the lecture
     * @param module the module the lecture belongs to
     * @param credits the credits of the lecture, has to be between {@code 1} and {@code 9}
     * @param professor the professor who teaches the lecture
     */
    Lecture(
            final int id,
            final String name,
            final Module module,
            final int credits,
            final Professor professor) {
        ////
        super(id, name);
        
        if (credits < LOWER_BOUND || credits > UPPER_BOUND) {
            throw new IllegalArgumentException("Invalid credit count");
        }
        
        this.module    = module;
        this.credits   = credits;
        this.professor = professor;
        
        module.addLecture(this);
        professor.add(this);
    }
    
    /**
     * Adds a grade.
     * 
     * @param  student the student to add the grade for
     * @param  grade the grade
     * @throws IllegalStateException if the grade is already set
     */
    void addGrade(
            final Student student,
            final Grade grade) {
        ////
        if (grades.putIfAbsent(student, grade) != null)
            throw new IllegalStateException("Grade already set");
        student.add(this);
    }
    
    /**
     * Returns the grade of the student.
     * 
     * @param  student the student
     * @return the grade
     * @throws NoSuchElementException if the student has no grade set
     */
    Grade grade(
            final Student student) {
        ////
        return Optional.of(student).map(grades::get).orElseThrow(() -> new NoSuchElementException(student.toString()));
    }
    
    /**
     * Returns the average grade of this lecture.
     * 
     * @return the grade
     */
    public Grade grade() {
        ////
        return Grade.average(grades.values());
    }
    
    /**
     * Returns the module of the lecture.
     * 
     * @return the module
     */
    public Module module() {
        ////
        return module;
    }
    
    /**
     * Returns the professor of the lecture.
     * 
     * @return the professor
     */
    public Professor professor() {
        ////
        return professor;
    }
    
    /**
     * Returns a stream containing the students.
     * 
     * @return the students
     */
    public Stream<Student> students() {
        ////
        return grades.keySet().stream();
    }
    
    /**
     * Returns the count of credits.
     * 
     * @return the credits
     */
    public int credits() {
        ////
        return credits;
    }
    
    @Override
    public <R> R accept(
            final ElementVisitor<R> visitor) {
        ////
        return visitor.visit(this);
    }
}
