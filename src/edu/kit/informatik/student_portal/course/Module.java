package edu.kit.informatik.student_portal.course;

import java.util.Set;
import java.util.TreeSet;

import edu.kit.informatik.student_portal.common.ICanEqual;

public final class Module extends Course implements ICanEqual {
    private final Set<Lecture> lectures;
    
    /**
     * TODO
     * @param name TODO
     * @throws IllegalArgumentException TODO
     */
    public Module(final String name) throws IllegalArgumentException {
        super(name);
        lectures = new TreeSet<Lecture>();
    }

    /**
     * 
     * @param o TODO
     * @return TODO
     */
    public int compareTo(Module o) {
        return super.compareTo(o);
    }
    
    /**
     * TODO
     * @param lecture TODO
     */
    public void addLecture(final Lecture lecture) {
        //TODO check number of credits before adding
        if (!lectures.add(lecture))
            //TODO add exception text
            throw new IllegalArgumentException();
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
}
