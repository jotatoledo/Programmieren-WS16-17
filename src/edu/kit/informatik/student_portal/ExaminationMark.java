package edu.kit.informatik.student_portal;

import java.util.Objects;

/**
 * TODO add doc
 * @author JoseNote
 * @version 1.00
 */
public final class ExaminationMark implements Comparable<ExaminationMark> {
    private final Lecture lecture;
    private final Student student;
    private final double mark;
    
    /**
     * 
     * @param lecture TODO
     * @param student TODO
     * @param mark TODO
     */
    public ExaminationMark(final Lecture lecture, final Student student, final double mark) {
        if (mark < 1.00 || mark > 5.00)
            throw new IllegalArgumentException("invalid mark value");
        this.student = student;
        this.lecture = lecture;        
        this.mark = mark;
        student.addMark(this);
        lecture.addMark(this);
    }

    @Override
    public int compareTo(ExaminationMark o) {
        final int compareLecture = lecture.compareTo(o.lecture);
        if (compareLecture != 0)
            return compareLecture;
        return student.compareTo(o.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecture, student);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExaminationMark
                && compareTo((ExaminationMark) obj) == 0;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String infoLecture() {
        return Integer.toString(lecture.getId()).concat(" ")
                .concat(lecture.getName()).concat(" ")
                .concat(student.average());
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String infoStudent() {
        return Integer.toString(getStudentEnrolmentNumber()).concat(" ")
                .concat(student.getFirstName()).concat(" ")
                .concat(student.getLastName()).concat(" ")
                .concat(Double.toString(mark));
    }
    
    /**
     * TODO add doc
     * @param lecture TODO add doc
     * @param student TODO add doc
     * @return TODO add doc
     */
    public boolean matchMembers(final Lecture lecture, final Student student) {
        return this.lecture.compareTo(lecture) == 0
                && this.student.compareTo(student) == 0;
    }

    /**
     * TODO add doc
     * @return TODO add doc
     */
    public double getMark() {
        return mark;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public int getLectureId() {
        return lecture.getId();
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public int getStudentEnrolmentNumber() {
        return student.getEnrolmentNumber();
    }
}
