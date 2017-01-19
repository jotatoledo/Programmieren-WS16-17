package edu.kit.informatik.student_portal.course;

import java.util.Set;
import java.util.TreeSet;

public final class Module extends Course {
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
        //TODO implement
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((lectures == null) ? 0 : lectures.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO implement
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Module other = (Module) obj;
        if (lectures == null) {
            if (other.lectures != null)
                return false;
        } else if (!lectures.equals(other.lectures))
            return false;
        return true;
    }
}
