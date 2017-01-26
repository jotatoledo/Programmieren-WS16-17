package edu.kit.informatik.student_portal;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a lecture in the system
 * @author JoseNote
 * @version 1.00
 */
public final class Lecture extends Course implements ICanEqual {
    private final Module module;
    private final Professor professor;
    private final Set<ExaminationMark> marks;
    
    /**
     * TODO add doc
     * @param professor TODO add doc
     * @param module TODO add doc
     * @param credits TODO add doc
     * @param name TODO add doc
     */
    public Lecture(final Professor professor, final Module module, 
            final int credits, final String name) {
        super(TestUtility.testValidCredits(credits), 
                TestUtility.testStringLowerCaseNotNull(ErrorMessage.LECTURE_NAME, name));
        this.professor = professor;
        professor.addLecture(this);
        this.module = module; 
        module.addLecture(this);
        marks = new HashSet<ExaminationMark>();
    }

    /**
     * TODO add doc
     * @param o TODO add doc
     * @return TODO add doc
     */
    public int compareTo(Lecture o) {
        return super.compareTo(o);
    }
    
    @Override
    public int compareTo(Course o) {
        if (o instanceof Lecture)
            return compareTo((Lecture) o);
        return super.compareTo(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Lecture
                && ((Lecture) obj).canEqual(this)
                && compareTo((Lecture) obj) == 0;
    }
    
    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof Lecture;
    }
    
    @Override
    public String toString() {
        return Integer.toString(getId()).concat(" ")
                .concat(getName()).concat(" ")
                .concat(Integer.toString(getCredits())).concat(" ")
                .concat(stringAverage());
    }
    
    /**
     * TODO complete doc
     * Used in summary-professor
     * @return TODO add doc
     */
    public String toStringNoCredit() {
        return Integer.toString(getId()).concat(" ")
                .concat(getName()).concat(" ")
                .concat(stringAverage());
    }
    /**
     * TODO add doc
     * @param mark TODO add doc
     */
    public void addMark(final ExaminationMark mark) {
        if (!marks.add(mark))
            throw new IllegalArgumentException("there is already a mark associated for this student and lecture");
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String stringAverage() {
        if (marks.size() == 0)
            return "none";
        return Double.toString(numericAverage());
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public ExaminationMark[] getMarks() {
        return marks.stream()
                .sorted((m1, m2) -> Integer.compare(m1.getStudentEnrolmentNumber(), m2.getStudentEnrolmentNumber()))
                .toArray(size -> new ExaminationMark[size]);
    }
    
    /**
     * TODO add doc 
     * @return TODO add doc
     */
    public int numberMarks() {
        return marks.size();
    }
    
    /**
     * TODO Add doc
     * @return Add doc
     */
    public double numericAverage() {
        //TODO round
        if (marks.size() == 0)
            return 0;
        return totalMarks() / marks.size();
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public double totalMarks() {
        if (marks.size() == 0)
            return 0;
        return marks.stream()
            .mapToDouble(x->x.getMark())
            .reduce(0, (a, b) -> a + b);
    }
}
