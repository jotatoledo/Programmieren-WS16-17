package edu.kit.informatik.student_portal.user;

import java.util.HashSet;
import java.util.Set;
import edu.kit.informatik.student_portal.common.ICanEqual;
import edu.kit.informatik.student_portal.common.ExaminationMark;

public final class Student extends User implements ICanEqual {
    private final int enrolmentNumber;
    private final Set<ExaminationMark> marks;
    
    /**
     * TODO
     * @param enrolmentNumber TODO
     * @param firstName TODO
     * @param lastName TODO
     * @throws IllegalArgumentException TODO
     */
    public Student(final int enrolmentNumber, 
            final String firstName, final String lastName) throws IllegalArgumentException {
        super(firstName, lastName);
        if (enrolmentNumber < 100000 || enrolmentNumber > 999999)
            throw new IllegalArgumentException("Invalid enrolment number");
        this.enrolmentNumber = enrolmentNumber;
        marks = new HashSet<ExaminationMark>();
    }

    /**
     * TODO
     * @param o TODO
     * @return TODO
     */
    public int compareTo(Student o) {
        //TODO add null-safe
        return Integer.compare(enrolmentNumber, o.enrolmentNumber);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(enrolmentNumber);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Student 
                && ((Student) obj).canEqual(this)
                && compareTo((Student) obj) == 0;
    }
    
    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof Student;
    }
    
    /**
     * TODO
     * @param mark TODO
     */
    public void addMark(final ExaminationMark mark) {
        //TODO check for dupplicate
        marks.add(mark);
    }

    /**
     * 
     * @return TODO
     */
    public int getEnrolmentNumber() {
        return enrolmentNumber;
    }
}
