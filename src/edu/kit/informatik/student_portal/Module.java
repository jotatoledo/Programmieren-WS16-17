package edu.kit.informatik.student_portal;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * TODO add doc
 * @author JoseNote
 * @version
 */
public final class Module extends Course implements ICanEqual {
    private final Set<Lecture> lectures;
    
    /**
     * TODO add doc
     * @param name TODO add doc
     */
    public Module(final String name) {
        super(name);
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
                .concat(average());
    }
    
    /**
     * TODO add doc
     * @param lecture TODO add doc
     */
    public void addLecture(final Lecture lecture) {
        if ((totalCredits() + lecture.getCredits()) > 45)
            throw new IllegalArgumentException("cant exceed 45 credits on the module");
        if (!lectures.add(lecture))
            //TODO add exception text
            throw new IllegalArgumentException("lecture wasnt added to the module");
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String average() {
        //TODO implement
        return "none";
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
