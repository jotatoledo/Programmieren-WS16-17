package edu.kit.informatik.student_portal;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a module on the portal system
 * @author JoseNote
 * @version 1.00
 */
public final class Module extends Course implements ICanEqual {
    private final Set<Lecture> lectures;
    
    /**
     * TODO add doc
     * @param name TODO add doc
     */
    public Module(final String name) {
        super(TestUtility.testStringLowerCaseNotNull(ErrorMessage.MODULE_NAME, name));
        lectures = new TreeSet<Lecture>();
    }

    /**
     * TODO add doc
     * @param o TODO add doc
     * @return TODO add doc
     */
    public int compareTo(Module o) {
        return super.compareTo(o);
    }
    
    @Override
    public int compareTo(Course o) {
        if (o instanceof Module)
            return compareTo((Module) o);
        return super.compareTo(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Module 
                && ((Module) obj).canEqual(this)
                && compareTo((Module) obj) == 0;
    }
    
    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof Module;
    }
    
    @Override
    public String toString() {
        return Integer.toString(getId()).concat(" ")
                .concat(getName()).concat(" ")
                .concat(Integer.toString(totalCredits())).concat(" ")
                .concat(stringAverage());
    }
    
    /**
     * TODO add doc
     * @param lecture TODO add doc
     */
    public void addLecture(final Lecture lecture) {
        if ((totalCredits() + lecture.getCredits()) > 45)
            throw new IllegalArgumentException("cant exceed 45 credits on the module");
        if (!lectures.add(lecture))
            throw new IllegalArgumentException("lecture wasnt added to the module");
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String stringAverage() {
        if (lectures.size() == 0 || lecturesWithNotes() == 0)
            return "none";
        return Double.toString(numericAverage());
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    private double numericAverage() {
        //TODO aufrunden auf 2 Komma
        if (lectures.size() == 0 || lecturesWithNotes() == 0)
            return 0.0;
        int countMarks = 0;
        double acummAggregatedMarks = 0.0;
        for (Lecture lec : lectures) {
            if (lec.numberMarks() == 0)
                continue;
            countMarks += lec.numberMarks();
            acummAggregatedMarks += lec.numberMarks() * lec.totalMarks();
        }
        return acummAggregatedMarks / countMarks;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public long lecturesWithNotes() {
        return lectures.stream()
                .filter(x-> x.numberMarks() != 0)
                .count();
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public int totalCredits() {
        return lectures.stream()
                .mapToInt(x->x.getCredits())
                .reduce(0, (a, b) -> a + b);
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public Collection<Lecture> getLectures() {
        return Collections.unmodifiableCollection(lectures);
    }
    
    /**
     * TODO add doc
     * @param student TODO add doc
     * @return TODO add doc
     */
    public double getWeightedLecturesAverage(final Student student) {
        return 0.0;
//        return lectures.stream()
//                .filter(l->l.getMarks().)
//                .mapToInt(x->x.get())
//                .reduce(0, (a, b) -> a + b);
    }
}
