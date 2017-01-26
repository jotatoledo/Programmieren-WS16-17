package edu.kit.informatik.student_portal;

import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

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
     * TODO add doc
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
    public Lecture getLecture(final int lectureId) {
        ValueTester.testValidLectureId(lectureId);
        
        try {
            Optional<Lecture> result = lectures.stream()
                    .filter(x-> x.getId() == lectureId)
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("there is no lecture with the given id");
        }  
    }

    @Override
    public Lecture addLecture(final String lectureName, final int moduleId, 
            final String professorFirstName, final String professorLastName,
            final String chairName, final int lectureCredits) {
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.LECTURE_NAME, lectureName);
        ValueTester.testValidModuleId(moduleId);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.PROFESSOR_FIRSTNAME, professorFirstName);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.PROFESSOR_LASTNAME, professorLastName);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.CHAIR_NAME, chairName);
        ValueTester.testValidCredits(lectureCredits);
        
        Module mod = getModule(moduleId);
        if ((mod.totalCredits() + lectureCredits) > 45)
            throw new IllegalArgumentException("cant exceed 45 credits on the module");
        Professor prof = getProfessor(professorFirstName, professorLastName, chairName);
        Lecture entity = new Lecture(prof, mod, lectureCredits, lectureName);
        lectures.add(entity);
        return entity;
    }

    @Override
    public Collection<Module> getModule() {
        return Collections.unmodifiableCollection(modules);
    }

    @Override
    public Module getModule(final int moduleId) {
        ValueTester.testValidModuleId(moduleId);
        
        try {
            Optional<Module> result = modules.stream()
                    .filter(x-> x.getId() == moduleId)
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("there is no module with the given id");
        }        
    }

    @Override
    public Module addModule(final String moduleName) {
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.MODULE_NAME, moduleName);
        
        Module entity = new Module(moduleName);
        modules.add(entity);
        return entity;
    }

    @Override
    public Collection<Student> getStudent() {
        return Collections.unmodifiableCollection(students);
    }

    @Override
    public Student getStudent(final String firstName, final String lastName, final int enrolmentNumber) {
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.STUDENT_FIRSTNAME, firstName);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.STUDENT_FIRSTNAME, lastName);
        ValueTester.testValidEnrolmentNumber(enrolmentNumber);
        
        try {
            Optional<Student> result = students.stream()
                    .filter(x-> x.getEnrolmentNumber() == enrolmentNumber)
                    .findFirst();
            Student value = result.get();
            if (!value.getFirstName().equals(firstName))
                throw new IllegalArgumentException("the given first name doenst match "
                        + "the one of the student with the given enrolment number");
            if (!value.getLastName().equals(lastName))
                throw new IllegalArgumentException("the given last name doenst match "
                        + "the one of the student with the given enrolment number");
            return value;
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("there is no student with the given enrolment number");
        }
    }

    @Override
    public Student getStudent(final int enrolmentNumber) {
        ValueTester.testValidEnrolmentNumber(enrolmentNumber);
        
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
    public boolean existStudent(final int enrolmentNumber) {
        ValueTester.testValidEnrolmentNumber(enrolmentNumber);
        
        Optional<Student> result = students.stream()
                .filter(x-> x.getEnrolmentNumber() == enrolmentNumber)
                .findFirst();        
        return result.isPresent();
    }

    @Override
    public Student addStudent(final String studentFirstName, final String studentLastName, 
            final int enrolmentNumber) {
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.STUDENT_FIRSTNAME, studentFirstName);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.STUDENT_LASTNAME, studentLastName);
        ValueTester.testValidEnrolmentNumber(enrolmentNumber);
        
        if (existStudent(enrolmentNumber))
            throw new IllegalArgumentException("there is already a student with the given enrolment number");
        Student entity = new Student(enrolmentNumber, studentFirstName, studentLastName);
        students.add(entity);
        return entity;
    }

    @Override
    public Collection<Professor> getProfessor() {
        return Collections.unmodifiableCollection(professors);
    }

    @Override
    public Professor getProfessor(final String professorFirstName, 
            final String professorLastName, final String chairName) {
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.PROFESSOR_FIRSTNAME, professorFirstName);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.PROFESSOR_LASTNAME, professorLastName);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.CHAIR_NAME, chairName);
        
        try {
            Chair chair = getChair(chairName);
            Optional<Professor> result = professors.stream()
                    .filter(x-> x.getFirstName().equals(professorFirstName)
                            && x.getLastName().equals(professorLastName)
                            && x.getChair().equals(chair))
                    .findFirst();
            return result.get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("there is no professor with the given parameters");
        }  
    }

    @Override
    public boolean existProfessor(final String professorFirstName, 
            final String professorLastName, final String chairName) {
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.PROFESSOR_FIRSTNAME, professorFirstName);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.PROFESSOR_LASTNAME, professorLastName);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.CHAIR_NAME, chairName);
        
        Chair chair = getChair(chairName);
        Optional<Professor> result = professors.stream()
                .filter(x-> x.getFirstName().equals(professorFirstName)
                        && x.getLastName().equals(professorLastName)
                        && x.getChair().equals(chair))
                .findFirst();        
        return result.isPresent();
    }

    @Override
    public Professor addProfesor(final String professorFirstName, 
            final String professorLastName, final String chairName) {
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.PROFESSOR_FIRSTNAME, professorFirstName);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.PROFESSOR_LASTNAME, professorLastName);
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.CHAIR_NAME, chairName);
        
        //TODO optimize chair reference
        if (!existChair(chairName))
            addChair(chairName);
        if (existProfessor(professorFirstName, professorLastName, chairName))
            throw new IllegalArgumentException("there is already a professor with the given values");
        Professor entity = new Professor(getChair(chairName), professorFirstName, professorLastName);
        professors.add(entity);
        return entity;
    }

    @Override
    public ExaminationMark getMark(final int lectureId, 
            final int studentEnrolmentNumber) {
        ValueTester.testValidLectureId(lectureId);
        ValueTester.testValidEnrolmentNumber(studentEnrolmentNumber);
        
        Lecture lecture = getLecture(lectureId);
        Student student = getStudent(studentEnrolmentNumber);
        
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
    public ExaminationMark addMark(final int lectureId, 
            final int studentEnrolmentNumber, final double mark) {
        ValueTester.testValidLectureId(lectureId);
        ValueTester.testValidEnrolmentNumber(studentEnrolmentNumber);
        ValueTester.testValidMark(mark);
        
        if (existMark(lectureId, studentEnrolmentNumber))
            throw new IllegalArgumentException("there is already a mark for the given "
                    + "lecture assigned to the given student");
        ExaminationMark entity = new ExaminationMark(getLecture(lectureId), 
                getStudent(studentEnrolmentNumber), mark);
        marks.add(entity);
        return entity;
    }

    @Override
    public boolean existMark(final int lectureId, final int studentEnrolmentNumber) {
        ValueTester.testValidLectureId(lectureId);
        ValueTester.testValidEnrolmentNumber(studentEnrolmentNumber);
        
        Lecture lecture = getLecture(lectureId);
        Student student = getStudent(studentEnrolmentNumber);
        Optional<ExaminationMark> result = marks.stream()
                .filter(x-> x.matchMembers(lecture, student))
                .findFirst();
        return result.isPresent();
    }

    @Override
    public Chair getChair(final String chairName) {
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.CHAIR_NAME, chairName);
        
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
    public boolean existChair(final String chairName) {
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.CHAIR_NAME, chairName);
        
        Optional<Chair> result = chairs.stream()
                .filter(x-> x.getName().equals(chairName))
                .findFirst();        
        return result.isPresent();
    }

    @Override
    public Chair addChair(final String chairName) {
        ValueTester.testStringNotNullAndLowercase(ErrorMessage.CHAIR_NAME, chairName);
        
        Chair entity = new Chair(chairName);
        chairs.add(entity);
        return entity;
    }


}
