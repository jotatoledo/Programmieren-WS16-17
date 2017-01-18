package edu.kit.informatik.student_portal.course;

import java.util.Set;
import java.util.TreeSet;

public final class Module extends Course implements Comparable<Module> {
    private final Set<Lecture> lectures;
    
    /**
     * 
     * @param name
     * @throws IllegalArgumentException 
     */
    public Module(final String name) throws IllegalArgumentException {
        super(name);
        lectures = new TreeSet<Lecture>();
    }

    @Override
    public int compareTo(Module o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    public void addLecture(final Lecture lecture) {
        //TODO check number of credits before adding
        if (!lectures.add(lecture))
            //TODO add exception text
            throw new IllegalArgumentException();
    }
}
