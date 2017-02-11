
package edu.kit.informatik.campusmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A campus management system.
 * 
 * @author  Tobias Bachert
 * @version 1.00, 2016/08/11
 */
public final class CampusManagement {
    
    private static final int FIRST_ID = 1;
    
    private final Map<Professor, Professor> professors = new TreeMap<>();
    private final Map<Student,   Student>   students   = new TreeMap<>();
    
    private final List<Element> elements = new ArrayList<>();
    
    /**
     * Creates a new, empty {@code CampusManagement} system.
     */
    public CampusManagement() {
        ////
    }
    
    //==================================================================================================================
    /**
     * Adds a professor with the specified name and chair.
     * 
     * @param  forename the forename
     * @param  surname the surname
     * @param  chair the chair
     * @throws NullPointerException if any argument is {@code null}
     * @throws IllegalStateException if the professor was already added
     */
    public void addProfessor(
            final String forename,
            final String surname,
            final String chair) {
        ////
        Objects.requireNonNull(forename);
        Objects.requireNonNull(surname);
        Objects.requireNonNull(chair);
        
        final Professor professor = new Professor(forename, surname, chair);
        if (professors.putIfAbsent(professor, professor) != null)
            throw new IllegalStateException("Professor already added");
    }
    
    /**
     * Returns a stream containing all professors.
     * 
     * @return the stream
     */
    public Stream<Professor> professors() {
        ////
        return professors.keySet().stream();
    }
    
    /**
     * Returns the professor with the specified name.
     * 
     * @param  forename the forename
     * @param  surname the surname
     * @param  chair the chair
     * @return the professor
     * @throws NullPointerException if any argument is {@code null}
     * @throws IllegalArgumentException if the professor does not exist
     */
    public Professor professor(
            final String forename,
            final String surname,
            final String chair) {
        ////
        Objects.requireNonNull(forename);
        Objects.requireNonNull(surname);
        Objects.requireNonNull(chair);
        
        return Optional.of(new Professor(forename, surname, chair)).map(professors::get)
                .orElseThrow(() -> noSuch(Professor.class, forename, surname, chair));
    }
    
    //==================================================================================================================
    /**
     * Adds a student with the specified name and student number.
     * 
     * @param  forename the forename
     * @param  surname the surname
     * @param  studentnumber the student number
     * @throws NullPointerException if any argument is {@code null}
     * @throws IllegalStateException if a student with this student number already exists
     */
    public void addStudent(
            final String forename,
            final String surname,
            final int studentnumber) {
        ////
        Objects.requireNonNull(forename);
        Objects.requireNonNull(surname);
        
        final Student student = new Student(forename, surname, studentnumber);
        if (students.putIfAbsent(student, student) != null)
            throw new IllegalStateException("Student number in use");
    }
    
    /**
     * Returns a stream containing the students.
     * 
     * @return the stream
     */
    public Stream<Student> students() {
        ////
        return students.keySet().stream();
    }
    
    /**
     * Returns the student with the specified name.
     * 
     * @param  forename the forename
     * @param  surname the surname
     * @param  studentnumber the student number
     * @return the student
     * @throws NullPointerException if any argument is {@code null}
     * @throws IllegalArgumentException if the student does not exist
     */
    public Student student(
            final String forename,
            final String surname,
            final int studentnumber) {
        ////
        Objects.requireNonNull(forename);
        Objects.requireNonNull(surname);
        
        return Optional.of(new Student(null, null, studentnumber)).map(students::get)
                .filter((student) -> student.forename().equals(forename))
                .filter((student) -> student.surname().equals(surname))
                .orElseThrow(() -> noSuch(Student.class, forename, surname, studentnumber));
    }
    
    /**
     * Returns the student with the specified student number.
     * 
     * @param  studentnumber the student number
     * @return the student
     * @throws IllegalArgumentException if the student does not exist
     */
    public Student student(
            final int studentnumber) {
        ////
        return Optional.of(new Student(null, null, studentnumber)).map(students::get)
                .orElseThrow(() -> noSuch(Student.class, studentnumber));
    }
    
    //==================================================================================================================
    /**
     * Adds a module with the specified name.
     * 
     * @param  name the name
     * @throws NullPointerException if {@code name} is {@code null}
     */
    public void addModule(
            final String name) {
        ////
        elements.add(new Module(nextId(), name));
    }
    
    /**
     * Returns a stream containing the modules.
     * 
     * @return the stream
     */
    public Stream<Module> modules() {
        ////
        return elements.stream()
                .map(((GetModule) orElse -> null)::apply)
                .filter(Objects::nonNull);
    }
    
    /**
     * Returns the module with the specified id.
     * 
     * @param  id the id
     * @return the module
     * @throws IllegalArgumentException if the id does not represent a module
     */
    public Module module(
            final int id) {
        ////
        return element(id).accept((GetModule) orElse -> { throw new IllegalArgumentException("Not a module"); });
    }
    
    //==================================================================================================================
    /**
     * Adds a lecture with the specified arguments.
     * 
     * @param  name the name of the lecture
     * @param  id the id of the module the lecture belongs to
     * @param  forename the forename of the responsible professor
     * @param  surname the surname of the responsible professor
     * @param  chair the chair of the responsible professor
     * @param  credits the credits of the lecture
     * @throws NullPointerException if any argument is {@code null}
     * @throws IllegalArgumentException if the credit count is invalid, or if the module or professor does not exist
     * @throws IllegalStateException if the lecture cannot be added to the module
     */
    public void addLecture(
            final String name,
            final int id,
            final String forename,
            final String surname,
            final String chair,
            final int credits) {
        ////
        addLecture(name, module(id), professor(forename, surname, chair), credits);
    }
    
    /**
     * Adds a lecture with the specified arguments.
     * 
     * @param  name the name of the lecture
     * @param  module the module the lecture belongs to
     * @param  professor the professor
     * @param  credits the credits of the lecture
     * @throws NullPointerException if any argument is {@code null}
     * @throws IllegalArgumentException if the credit count is invalid, or if the module or professor does not exist
     * @throws IllegalStateException if the lecture cannot be added to the module
     */
    public void addLecture(
            final String name,
            final Module module,
            final Professor professor,
            final int credits) {
        ////
        Objects.requireNonNull(name);
        Objects.requireNonNull(professor);
        
        elements.add(new Lecture(nextId(), name, module, credits, professor));
    }
    
    /**
     * Returns a stream containing the lectures.
     * 
     * @return the stream
     */
    public Stream<Lecture> lectures() {
        ////
        return elements.stream().map(((GetLecture) orElse -> null)::apply).filter(Objects::nonNull);
    }
    
    /**
     * Returns the lecture with the specified id.
     * 
     * @param  id the id
     * @return the lecture
     * @throws IllegalArgumentException if the id does not represent a module
     */
    public Lecture lecture(
            final int id) {
        ////
        return element(id).accept((GetLecture) orElse -> { throw new IllegalArgumentException("Not a lecture"); });
    }
    
    //==================================================================================================================
    /**
     * Adds an examination marking for the specified student for the specified lecture.
     * 
     * @param  id the id of the lecture
     * @param  studentnumber the student number of the student
     * @param  grade the grade
     * @throws NullPointerException if {@code grade} is {@code null}
     * @throws IllegalArgumentException if the lecture or student does not exist
     * @throws IllegalStateException if a grade for this student and lecture is already set
     */
    public void examinationMarking(
            final int id,
            final int studentnumber,
            final Grade grade) {
        ////
        Objects.requireNonNull(grade);
        
        lecture(id).addGrade(student(studentnumber), grade);
    }
    
    /**
     * Adds an examination marking for the specified student for the specified lecture.
     * 
     * @param  lecture the lecture
     * @param  student the student
     * @param  grade the grade
     * @throws NullPointerException if {@code grade} is {@code null}
     * @throws IllegalArgumentException if the lecture or student does not exist
     * @throws IllegalStateException if a grade for this student and lecture is already set
     */
    public void examinationMarking(
            final Lecture lecture,
            final Student student,
            final Grade grade) {
        ////
        Objects.requireNonNull(grade);
        
        lecture.addGrade(student, grade);
    }
    
    //==================================================================================================================
    /**
     * Clears any information stored in this management system.
     */
    public void clear() {
        ////
        professors.clear();
        students.clear();
        elements.clear();
    }
    
    //==================================================================================================================
    private Element element(
            final int id) {
        ////
        final int normalizedId = id - FIRST_ID;
        if (normalizedId < 0 || normalizedId >= elements.size())
            throw new IllegalArgumentException("Invalid id");
        return elements.get(normalizedId);
    }
    
    private int nextId() {
        ////
        return elements.size() + FIRST_ID;
    }
    
    private IllegalArgumentException noSuch(
            final Class<?> type,
            final Object arg,
            final Object... args) {
        ////
        return new IllegalArgumentException(Stream.concat(Stream.of(arg), Stream.of(args)).map(String::valueOf)
                .collect(Collectors.joining(", ", "No such " + type.getSimpleName() + ": ", "")));
    }
    
    //==================================================================================================================
    private interface GetModule extends ElementVisitor.SimpleVisitor<Module> {
        
        @Override
        default Module visit(
                final Module module) {
            ////
            return module;
        }
    }
    
    private interface GetLecture extends ElementVisitor.SimpleVisitor<Lecture> {
        
        @Override
        default Lecture visit(
                final Lecture lecture) {
            ////
            return lecture;
        }
    }
}
