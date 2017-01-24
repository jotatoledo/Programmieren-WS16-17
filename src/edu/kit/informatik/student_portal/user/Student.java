package edu.kit.informatik.student_portal.user;

import java.util.Map;
import java.util.TreeMap;

import edu.kit.informatik.student_portal.course.Lecture;

public final class Student extends User implements Comparable<Student> {
    private final int enrolmentNumber;
    private final Map<Lecture, Double> lectureGrades;
    
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
        if (enrolmentNumber < 0)
            //TODO add text to exception
            throw new IllegalArgumentException();
        if (String.valueOf(enrolmentNumber).length() != 6)
            throw new IllegalArgumentException();
        this.enrolmentNumber = enrolmentNumber;
        lectureGrades = new TreeMap<Lecture, Double>();
    }

    @Override
    public int compareTo(Student o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Student 
                && compareTo((Student) obj) == 0;
    }
}
