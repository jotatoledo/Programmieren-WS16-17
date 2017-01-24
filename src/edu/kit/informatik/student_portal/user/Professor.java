package edu.kit.informatik.student_portal.user;

import java.util.Set;
import java.util.TreeSet;

import edu.kit.informatik.student_portal.chair.Chair;
import edu.kit.informatik.student_portal.common.ICanEqual;
import edu.kit.informatik.student_portal.course.Lecture;

public final class Professor extends User implements ICanEqual {
    private final Chair chair;
    private final Set<Lecture> lectures;
    
    /**
     * TODO
     * @param chair TODO
     * @param firstName TODO
     * @param lastName TODO
     * @throws IllegalArgumentException TODO
     */
    public Professor(final Chair chair, final String firstName, 
            final String lastName) throws IllegalArgumentException {
        super(firstName, lastName);    
        this.chair = chair;
        chair.addProfessor(this);
        lectures = new TreeSet<Lecture>();        
    }

    /**
     * 
     * @param o TODO
     * @return TODO
     */
    public int compareTo(Professor o) {
        //TODO Add null-safe
        final int superComparission = super.compareTo(o);
        return superComparission == 0 ? superComparission : chair.compareTo(o.getChair());
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Professor 
                && ((Professor) obj).canEqual(this)
                && compareTo((Professor) obj) == 0;
    }

    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof Professor;
    }
    
    /**
     * TODO
     * @return TODO
     */
    public Chair getChair() {
        return chair;
    }
    
    /**
     * TODO
     * @param lecture TODO
     */
    public void addLecture(final Lecture lecture) {
        if (!lectures.add(lecture))
            throw new IllegalArgumentException("This professor instance already has the given lecture assigned");
    }
}
