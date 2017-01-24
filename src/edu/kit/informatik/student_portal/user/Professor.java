package edu.kit.informatik.student_portal.user;

import java.util.Set;
import java.util.TreeSet;

import edu.kit.informatik.student_portal.course.Lecture;
import edu.kit.informatik.student_portal.professorial_chair.ProfessorialChair;

public final class Professor extends User implements Comparable<Professor> {
    private final ProfessorialChair chair;
    private final Set<Lecture> lectures;
    
    /**
     * TODO
     * @param chair TODO
     * @param firstName TODO
     * @param lastName TODO
     * @throws IllegalArgumentException TODO
     */
    public Professor(final ProfessorialChair chair, final String firstName, 
            final String lastName) throws IllegalArgumentException {
        super(firstName, lastName);    
        this.chair = chair;
        chair.addProfessor(this);
        lectures = new TreeSet<Lecture>();
        
    }

    @Override
    public int compareTo(Professor o) {
        if (this == o)
            return 0;
        final int compareFirstName = this.getFirstName().compareTo(o.getFirstName());
        if (compareFirstName != 0)
            return compareFirstName;
        final int compareLastName = this.getLastName().compareTo(o.getLastName());
        if (compareLastName != 0)
            return compareLastName;
        return this.chair.compareTo(o.getChair());
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Professor 
                && compareTo((Professor) obj) == 0;
    }

    /**
     * TODO
     * @return TODO
     */
    public ProfessorialChair getChair() {
        return chair;
    }
    
    /**
     * TODO
     * @param lecture TODO
     */
    public void addLecture(final Lecture lecture) {
        if (!lectures.add(lecture))
            //TODO add exception text
            throw new IllegalArgumentException();
    }
}
