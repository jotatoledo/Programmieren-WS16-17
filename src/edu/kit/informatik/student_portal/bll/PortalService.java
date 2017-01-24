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
    public Collection<Module> getModule() {
        return Collections.unmodifiableCollection(modules);
    }

    @Override
    public Collection<Student> getStudent() {
        return Collections.unmodifiableCollection(students);
    }

    @Override
    public Collection<Professor> getProfessor() {
        return Collections.unmodifiableCollection(professors);
    }

    @Override
    public Lecture getLecture(final int id) {
        try {
            Optional<Lecture> result = lectures.stream()
                    .filter(x-> x.getId() == id)
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("there is no lecture with the given id");
        }  
    }

    @Override
    public Student getStudent(final int enrolmentNumber) {
        try {
            Optional<Student> result = students.stream()
                    .filter(x-> x.getEnrolmentNumber() == enrolmentNumber)
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("there is no student with the given enrolment number");
        } 
    }

    @Override
    public Module getModule(final int id) throws NoSuchElementException {
        try {
            Optional<Module> result = modules.stream()
                    .filter(x-> x.getId() == id)
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("there is no module with the given id");
        }        
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
            throw new NoSuchElementException("there is no professor with the given parameters");
        }  
    }

    @Override
    public ExaminationMark getMark(final Lecture lecture, final Student student) {
        try {
            Optional<ExaminationMark> result = marks.stream()
                    .filter(x-> x.matchMembers(lecture, student))
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("there is no mark with the parameters");
        } 
    }

    @Override
    public Chair getChair(final String nameChair) {
        try {
            Optional<Chair> result = chairs.stream()
                    .filter(x-> x.getName().equals(nameChair))
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("there is no chair with the given name");
        } 
    }

    @Override
    public boolean existMark(Lecture lecture, Student student) {
        Optional<ExaminationMark> result = marks.stream()
                .filter(x-> x.matchMembers(lecture, student))
                .findFirst();
        
        return result.isPresent();
    }

    @Override
    public Lecture addLecture(String name, int idModule, 
            String professorFirstName, String professorLastName,
            String chairName, int credtis) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Module addModule(String name) {
        //TODO implement exist logic
        Module newEntity = new Module(name);
        modules.add(newEntity);
        return newEntity;
    }

    @Override
    public Student addStudent(String firstName, String lastName, int enrolmentNumber) {
        //TODO implement exist logic
        Student entity = new Student(enrolmentNumber, firstName, lastName);
        students.add(entity);
        return null;
    }

    @Override
    public Professor addProfesor(String firstName, String lastName, Chair chair) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExaminationMark addMark(Lecture lecture, Student student) {
        // TODO Auto-generated method stub
        return null;
    }
}
