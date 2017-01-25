package edu.kit.informatik.student_portal.bll;

import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import edu.kit.informatik.student_portal.bll.contracts.IPortalService;
import edu.kit.informatik.student_portal.chair.Chair;
import edu.kit.informatik.student_portal.common.ExaminationMark;
import edu.kit.informatik.student_portal.course.Lecture;
import edu.kit.informatik.student_portal.course.Module;
import edu.kit.informatik.student_portal.user.Professor;
import edu.kit.informatik.student_portal.user.Student;

/**
 * Service for the student portal
 * @author JoseNote
 * @version 1.00
 */
public class PortalService implements IPortalService {
    private final Set<Student> students;
    private final Set<Professor> professors;
    private final Set<Lecture> lectures;
    private final Set<Module> modules;
    private final Set<Chair> chairs;
    private final Set<ExaminationMark> marks;
    
    /**
     * TODO
     */
    public PortalService() {
        students = new TreeSet<Student>();
        professors = new TreeSet<Professor>();
        lectures = new TreeSet<Lecture>();
        modules = new TreeSet<Module>();
        chairs = new TreeSet<Chair>();
        marks = new TreeSet<ExaminationMark>();
    }

    @Override
    public Collection<Lecture> getLecture() {
        return Collections.unmodifiableCollection(lectures);
    }

    @Override
    public Lecture getLecture(final int id) {
        try {
            Optional<Lecture> result = lectures.stream()
                    .filter(x-> x.getId() == id)
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("there is no lecture with the given id");
        }  
    }
    
    @Override
    public Lecture addLecture(String name, int idModule, 
            String professorFirstName, String professorLastName,
            String chairName, int credits) {
        Professor prof = getProfessor(professorFirstName, professorLastName, getChair(chairName));
        Module mod = getModule(idModule);
        Lecture entity = new Lecture(prof, mod, credits, name);
        lectures.add(entity);
        return entity;
    }
    
    @Override
    public Collection<Module> getModule() {
        return Collections.unmodifiableCollection(modules);
    }
    
    @Override
    public Module getModule(final int id) {
        //TODO validate id input
        try {
            Optional<Module> result = modules.stream()
                    .filter(x-> x.getId() == id)
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("there is no module with the given id");
        }        
    }
    
    @Override
    public Module addModule(String name) {
        Module entity = new Module(name);
        modules.add(entity);
        return entity;
    }

    @Override
    public Collection<Student> getStudent() {
        return Collections.unmodifiableCollection(students);
    }
    
    @Override
    public Student getStudent(final int enrolmentNumber) {
      //TODO validate id input
        try {
            Optional<Student> result = students.stream()
                    .filter(x-> x.getEnrolmentNumber() == enrolmentNumber)
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("there is no student with the given enrolment number");
        } 
    }
    
    @Override
    public boolean existStudent(int enrolmentNumber) {
        Optional<Student> result = students.stream()
                .filter(x-> x.getEnrolmentNumber() == enrolmentNumber)
                .findFirst();        
        return result.isPresent();
    }
    
    @Override
    public Student addStudent(String firstName, String lastName, int enrolmentNumber) {
        if (existStudent(enrolmentNumber))
            throw new IllegalArgumentException("there is already a student with the given enrolment number");
        Student entity = new Student(enrolmentNumber, firstName, lastName);
        students.add(entity);
        return entity;
    }

    @Override
    public Collection<Professor> getProfessor() {
        return Collections.unmodifiableCollection(professors);
    }
    
    @Override
    public Professor getProfessor(final String firstName, 
            final String lastName, final Chair chair) {
        try {
            Optional<Professor> result = professors.stream()
                    .filter(x-> x.getFirstName().equals(firstName)
                            && x.getLastName().equals(lastName)
                            && x.getChair().equals(chair))
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("there is no professor with the given parameters");
        }  
    }
    
    @Override
    public boolean existProfessor(final String firstName, final String lastName, final String chairName) {
        Optional<Professor> result = professors.stream()
                .filter(x-> x.getFirstName().equals(firstName)
                        && x.getLastName().equals(lastName)
                        && x.getChair().getName().equals(chairName))
                .findFirst();        
        return result.isPresent();
    }

    @Override
    public Professor addProfesor(String firstName, String lastName, String chairName) {
        if (!existChair(chairName))
            chairs.add(new Chair(chairName));
        if (existProfessor(firstName, lastName, chairName))
            throw new IllegalArgumentException("there is already a professor with the given values");
        Professor entity = new Professor(getChair(chairName), firstName, lastName);
        boolean added = professors.add(entity);
        return entity;
    }

    @Override
    public ExaminationMark getMark(final Lecture lecture, final Student student) {
        try {
            Optional<ExaminationMark> result = marks.stream()
                    .filter(x-> x.matchMembers(lecture, student))
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("there is no mark with the parameters");
        } 
    }
    
    @Override
    public ExaminationMark addMark(Lecture lecture, Student student, double mark) {
        if (existMark(lecture, student))
            throw new IllegalArgumentException("there is already a mark for the given "
                    + "lecture assigned to the given student");
        ExaminationMark entity = new ExaminationMark(lecture, student, mark);
        marks.add(entity);
        return entity;
    }
    
    @Override
    public boolean existMark(Lecture lecture, Student student) {
        Optional<ExaminationMark> result = marks.stream()
                .filter(x-> x.matchMembers(lecture, student))
                .findFirst();
        
        return result.isPresent();
    }

    @Override
    public Chair getChair(final String chairName) {
        try {
            Optional<Chair> result = chairs.stream()
                    .filter(x-> x.getName().equals(chairName))
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("there is no chair with the given name");
        } 
    }
    
    @Override
    public boolean existChair(String chairName) {
        Optional<Chair> result = chairs.stream()
                .filter(x-> x.getName().equals(chairName))
                .findFirst();        
        return result.isPresent();
    }  
}