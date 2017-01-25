package edu.kit.informatik.student_portal;

import java.util.Collection;

/**
 * Contract for the portal service
 * @author JoseNote
 * @version 1.00
 */
public interface IPortalService {
    /**
     * TODO add doc
     * @return TODO add doc
     */
    Collection<Lecture> getLecture();
    
    /**
     * TODO add doc
     * @param id TODO add doc
     * @return TODO add doc
     */
    Lecture getLecture(int id);
      
    
    /**
     * TODO add doc
     * @param name TODO add doc
     * @param idModule TODO add doc
     * @param professorFirstName TODO add doc
     * @param professorLastName TODO add doc
     * @param chairName TODO add doc
     * @param credtis TODO add doc
     * @return TODO add doc
     */
    Lecture addLecture(String name, int idModule, 
            String professorFirstName, String professorLastName, String chairName, 
            int credtis);
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    Collection<Module> getModule();
    
    /**
     * TODO add doc
     * @param id TODO add doc
     * @return TODO add doc
     */
    Module getModule(int id);
    
    /**
     * TODO add doc
     * @param name TODO add doc
     * @return TODO add doc
     */
    Module addModule(String name);
    
    /**
     * TODO add doc
     * @return TODO add doc
     */    
    Collection<Student> getStudent();
    
    /**
     * TODO add doc
     * @param enrolmentNumber TODO add doc
     * @return TODO add doc
     */
    Student getStudent(String firstName, String lastName, int enrolmentNumber);
    
    Student getStudent(int enrolmentNumber);
    
    /**
     * TODO add doc
     * @param enrolmentNumber TODO add doc
     * @return TODO add doc
     */
    boolean existStudent(int enrolmentNumber);
    
    /**
     * TODO add doc
     * @param firstName TODO add doc
     * @param lastName TODO add doc
     * @param enrolmentNumber TODO add doc
     * @return TODO add doc
     */
    Student addStudent(String firstName, String lastName, int enrolmentNumber);
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    Collection<Professor>  getProfessor();
    
    /**
     * TODO add doc
     * @param firstName TODO add doc
     * @param lastName TODO add doc
     * @param chair TODO add doc
     * @return TODO add doc
     */
    Professor getProfessor(String firstName, String lastName, Chair chair);
    
    /**
     * TODO add doc
     * @param firstName TODO add doc
     * @param lastName TODO add doc
     * @param chairName TODO add doc
     * @return TODO add doc
     */
    boolean existProfessor(String firstName, String lastName, String chairName);
    
    /**
     * TODO add doc
     * @param firstName TODO add doc
     * @param lastName TODO add doc
     * @param chair TODO add doc
     * @return TODO add doc
     */
    Professor addProfesor(String firstName, String lastName, String chair);
    
    /**
     * TODO add doc
     * @param lecture TODO add doc
     * @param student TODO add doc
     * @return TODO add doc
     */
    ExaminationMark getMark(Lecture lecture, Student student);
    
    /**
     * TODO add doc
     * @param lecture TODO add doc
     * @param student TODO add doc
     * @return TODO add doc
     */
    boolean existMark(Lecture lecture, Student student);
    
    /**
     * TODO add doc
     * @param lecture TODO add doc
     * @param student TODO add doc
     * @param mark TODO add doc
     * @return TODO add doc
     */
    ExaminationMark addMark(Lecture lecture, Student student, double mark);
    
    /**
     * TODO add doc
     * @param chairName TODO add doc
     * @return TODO add doc
     */
    Chair getChair(String chairName);
    
    /**
     * TODO add doc
     * @param name TODO add doc
     * @return TODO add doc
     */
    Chair addChair(String name);
    
    /**
     * TODO add doc
     * @param chairName TODO add doc
     * @return TODO add doc
     */
    boolean existChair(String chairName);
    
    
}
