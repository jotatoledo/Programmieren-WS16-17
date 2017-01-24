package edu.kit.informatik.student_portal.bll.contracts;

import java.util.Collection;

import edu.kit.informatik.student_portal.chair.Chair;
import edu.kit.informatik.student_portal.common.ExaminationMark;
import edu.kit.informatik.student_portal.course.Lecture;
import edu.kit.informatik.student_portal.course.Module;
import edu.kit.informatik.student_portal.user.Professor;
import edu.kit.informatik.student_portal.user.Student;

public interface IPortalService {
    /**
     * 
     * @return TODO
     */
    Collection<Lecture> getLecture();
    
    /**
     * 
     * @param id TODO
     * @return TODO
     */
    Lecture getLecture(int id);
        
    /**
     * 
     * @param name TODO
     * @param idModule TODO
     * @param professorFirstName TODO
     * @param professorLastName TODO
     * @param chairName TODO
     * @param credtis TODO
     * @return TODO
     */
    Lecture addLecture(String name, int idModule, 
            String professorFirstName, String professorLastName, String chairName, 
            int credtis);
    
    /**
     * 
     * @return TODO
     */
    Collection<Module> getModule();
    
    /**
     * 
     * @param id TODO
     * @return TODO
     */
    Module getModule(int id);
    
    /**
     * 
     * @param name TODO
     * @return TODO
     */
    Module addModule(String name);
    
    /**
     * 
     * @return TODO
     */    
    Collection<Student> getStudent();
    
    /**
     * 
     * @param enrolmentNumber TODO
     * @return TODO
     */
    Student getStudent(int enrolmentNumber);
    
    /**
     * 
     * @param firstName TODO
     * @param lastName TODO
     * @param enrolmentNumber TODO
     * @return TODO
     */
    Student addStudent(String firstName, String lastName, int enrolmentNumber);
    
    /**
     * 
     * @return TODO
     */
    Collection<Professor>  getProfessor();
    
    /**
     * 
     * @param name TODO
     * @param lastName TODO
     * @param chair TODO
     * @return TODO
     */
    Professor getProfessor(String name, String lastName, Chair chair);
    
    /**
     * 
     * @param firstName TODO
     * @param lastName TODO
     * @param chair TODO
     * @return TODO
     */
    Professor addProfesor(String firstName, String lastName, Chair chair);
    
    /**
     * 
     * @param lecture TODO
     * @param student TODO
     * @return TODO
     */
    ExaminationMark getMark(Lecture lecture, Student student);
    
    /**
     * 
     * @param lecture TODO
     * @param student TODO
     * @return TODO
     */
    boolean existMark(Lecture lecture, Student student);
    
    /**
     * 
     * @param lecture TODO
     * @param student TODO
     * @return TODO
     */
    ExaminationMark addMark(Lecture lecture, Student student);
    
    /**
     * 
     * @param nameChair TODO
     * @return TODO
     */
    Chair getChair(String nameChair);
    
    
}
