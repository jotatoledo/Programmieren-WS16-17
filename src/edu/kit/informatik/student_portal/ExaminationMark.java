package edu.kit.informatik.student_portal;

import java.util.Objects;

/**
 * Represents a mark associated to a lecture and a student in the portal system
 * @author JoseNote
 * @version 1.00
 */
public final class ExaminationMark implements Comparable<ExaminationMark> {
    private final Lecture lecture;
    private final Student student;
    private final double mark;

    /**
     * Creates a new instance
     * @param lecture TODO add doc
     * @param student TODO add doc
     * @param mark TODO add doc
     */
    public ExaminationMark(final Lecture lecture, final Student student, 
            final double mark) {
        TestUtility.testValidMark(mark);
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
                .concat(student.stringAverage());
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
    
    /**
     * Forward method for get lecture module
     * @return TODO add doc
     */
    public Module getLectureModule() {
        return lecture.getModule();
    }
    
    /**
     * Gets the weighted mark
     * @return TODO add doc
     */
    public double getWeightedMark() {
        //TODO aufrunden
        return mark * lecture.getCredits();
    }
    
    /**
     * TODO add doc
     * @param student TODO add doc
     * @return TODO add doc
     */
    public boolean matchStudent(final Student student) {
        return this.student.equals(student);
    }
}
