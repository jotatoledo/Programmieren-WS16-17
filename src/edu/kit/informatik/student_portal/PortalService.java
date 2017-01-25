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
    public Lecture addLecture(final String name, final int idModule, 
            final String professorFirstName, final String professorLastName,
            final String chairName, final int credits) {
    	//TODO add parameter test
        Professor prof = getProfessor(professorFirstName, professorLastName, getChair(chairName));
        Module mod = getModule(idModule);
        if ((mod.totalCredits() + credits) > 45)
            throw new IllegalArgumentException("cant exceed 45 credits on the module");
        Lecture entity = new Lecture(prof, mod, credits, name);
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
    	ValueTester.testEnrolmentNumber(enrolmentNumber);
    	return getStudent(enrolmentNumber);
    }
    
    @Override
	public Student getStudent(final int enrolmentNumber) {
    	ValueTester.testEnrolmentNumber(enrolmentNumber);
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
    	ValueTester.testEnrolmentNumber(enrolmentNumber);
        Optional<Student> result = students.stream()
                .filter(x-> x.getEnrolmentNumber() == enrolmentNumber)
                .findFirst();        
        return result.isPresent();
    }
    
    @Override
    public Student addStudent(final String studentFirstName, final String studentLastName, final int enrolmentNumber) {
    	ValueTester.testStringNotNullAndLowercase(ErrorMessage.STUDENT_FIRSTNAME, studentFirstName);
    	ValueTester.testStringNotNullAndLowercase(ErrorMessage.STUDENT_LASTNAME, studentLastName);
    	ValueTester.testEnrolmentNumber(enrolmentNumber);
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
            final String professorLastName, final Chair chair) {
    	ValueTester.testStringNotNullAndLowercase(ErrorMessage.PROFESSOR_FIRSTNAME, professorFirstName);
    	ValueTester.testStringNotNullAndLowercase(ErrorMessage.PROFESSOR_LASTNAME, professorLastName);
        try {
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
    public Professor addProfesor(final String professorFirstName, final String professorLastName, final String chairName) {
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
    public ExaminationMark addMark(final Lecture lecture, final Student student, final double mark) {
    	ValueTester.testValidMark(mark);
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
	public Chair addChair(String chairName) {
		ValueTester.testStringNotNullAndLowercase(ErrorMessage.CHAIR_NAME, chairName);
		Chair entity = new Chair(chairName);
		chairs.add(entity);
		return entity;
	}

	 
}
