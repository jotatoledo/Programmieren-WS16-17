package edu.kit.informatik.student_portal.common;

import java.util.Objects;

import edu.kit.informatik.student_portal.course.Lecture;
import edu.kit.informatik.student_portal.user.Student;

public final class ExaminationMark implements Comparable<ExaminationMark> {
    private final Lecture lecture;
    private final Student student;
    private final double grade;
    
    /**
     * 
     * @param lecture TODO
     * @param student TODO
     * @param grade TODO
     */
    public ExaminationMark(final Lecture lecture, final Student student, final double grade) {
        this.student = student;
        this.lecture = lecture;
        this.grade = grade;
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
     * 
     * @param lecture TODO
     * @param student TODO
     * @return TODO
     */
    public boolean matchMembers(final Lecture lecture, final Student student) {
        return this.lecture.compareTo(lecture) == 0
                && this.student.compareTo(student) == 0;
    }
}
