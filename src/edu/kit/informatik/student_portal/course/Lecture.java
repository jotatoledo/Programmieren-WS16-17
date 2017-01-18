package edu.kit.informatik.student_portal.course;

import edu.kit.informatik.student_portal.user.Professor;

public final class Lecture extends Course implements Comparable<Lecture> {
    private final Module module;
    private final Professor professor;
    
    /**
     * 
     * @param professor
     * @param module
     * @param credits
     * @param name
     * @throws IllegalArgumentException
     */
    public Lecture(final Professor professor, final Module module, 
            final int credits, final String name) throws IllegalArgumentException {
        super(credits, name);
        this.professor = professor;
        if (!professor.addLecture(this))
            //TODO add exception text
            throw new IllegalArgumentException();
        this.module = module; 
        module.addLecture(this);
    }

    @Override
    public int compareTo(Lecture o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
