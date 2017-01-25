package edu.kit.informatik.student_portal;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO add doc
 * @author JoseNote
 * @version 1.00
 */
public final class Student extends User implements ICanEqual {
    private final int enrolmentNumber;
    private final Set<ExaminationMark> marks;
    
    /**
     * TODO add doc
     * @param enrolmentNumber TODO add doc
     * @param firstName TODO add doc
     * @param lastName TODO add doc
     */
    public Student(final int enrolmentNumber, 
            final String firstName, final String lastName) {
        super(firstName, lastName);
        if (enrolmentNumber < 100000 || enrolmentNumber > 999999)
            throw new IllegalArgumentException("Invalid enrolment number");
        this.enrolmentNumber = enrolmentNumber;
        marks = new HashSet<ExaminationMark>();
    }

    /**
     * TODO add doc
     * @param o TODO add doc
     * @return TODO add doc
     */
    public int compareTo(Student o) {
        //TODO add null-safe
        return Integer.compare(enrolmentNumber, o.enrolmentNumber);
    }

    @Override
    public int compareTo(User o) {
        if (o instanceof Student)
            return compareTo((Student) o);
        return super.compareTo(o);
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
    
    @Override
    public String toString() {
        return Integer.toString(enrolmentNumber).concat(" ")
                .concat(getFirstName()).concat(" ")
                .concat(getLastName()).concat(" ")
                .concat(average());
    }
    
    /**
     * TODO add doc
     * @param mark TODO add doc
     */
    public void addMark(final ExaminationMark mark) {
        //TODO check for dupplicate
        marks.add(mark);
    }

    /**
     * TODO add doc
     * @return TODO add doc
     */
    public int getEnrolmentNumber() {
        return enrolmentNumber;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String average() {
        return "none";
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public ExaminationMark[] getMarks() {
        return marks.stream()
                .sorted((m1, m2) -> Integer.compare(m1.getLectureId(), m2.getLectureId()))
                .toArray(size -> new ExaminationMark[size]);
    }
}
