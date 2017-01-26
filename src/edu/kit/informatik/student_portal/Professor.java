package edu.kit.informatik.student_portal;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * TODO add doc
 * @author JoseNote
 * @version 1.00
 */
public final class Professor extends User implements ICanEqual {
    private final Chair chair;
    private final Set<Lecture> lectures;
    
    /**
     * TODO add doc
     * @param chair TODO add doc
     * @param firstName TODO add doc
     * @param lastName TODO add doc
     */
    public Professor(final Chair chair, final String firstName, 
            final String lastName) {
        super(TestUtility.testStringLowerCaseNotNull(ErrorMessage.PROFESSOR_FIRSTNAME, firstName), 
                TestUtility.testStringLowerCaseNotNull(ErrorMessage.PROFESSOR_LASTNAME, lastName));    
        this.chair = chair;
        this.chair.addProfessor(this);
        lectures = new TreeSet<Lecture>();        
    }

    /**
     * TODO add doc
     * @param o TODO add doc
     * @return TODO add doc
     */
    public int compareTo(Professor o) {
        //TODO Add null-safe
        final int superComparission = super.compareTo(o);
        final int result = superComparission != 0 ? superComparission : chair.compareTo(o.getChair());
        return result;
    }
    
    @Override
    public int compareTo(User o) {
        if (o instanceof Professor)
            return compareTo((Professor) o);
        return super.compareTo(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), chair);
    }

    @Override
    public boolean equals(Object obj) {
        boolean value = obj instanceof Professor 
        && ((Professor) obj).canEqual(this)
        && compareTo((Professor) obj) == 0;
        return value;
    }

    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof Professor;
    }
    
    @Override
    public String toString() {
        return chair.getName().concat(" ")
        .concat(getFirstName()).concat(" ")
        .concat(getLastName().concat(" ")
        .concat(stringAverage()));
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public Chair getChair() {
        return chair;
    }
    
    /**
     * TODO add doc
     * @param lecture TODO add doc
     */
    public void addLecture(final Lecture lecture) {
        if (!lectures.add(lecture))
            throw new IllegalArgumentException("This professor instance already has the given lecture assigned");
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String stringAverage() {
        if (lectures.size() == 0 || lecturesWithMarks().size() == 0)
            return "none";
        return Double.toString(numericAverage());
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public double numericAverage() {
        //TODO aufrunden
        if (lectures.size() == 0 || lecturesWithMarks().size() == 0)
            //no lectures assigned or no lectures with marks
            return 0.0;
        int countCredtis = 0;
        double accumWeigtedAverage = 0.0;
        for (Lecture lect : lecturesWithMarks()) {
            countCredtis += lect.getCredits();
            accumWeigtedAverage += lect.getWeightedAverage();
        }
        return accumWeigtedAverage / countCredtis;
    }
    
    /**
     * Gets the lectures with at least one mark assigned
     * @return TODO add doc
     */
    public Collection<Lecture> lecturesWithMarks() {
        return lectures.stream()
                .filter(lect->lect.numberMarks() != 0)
                .collect(Collectors.toList());
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
}
