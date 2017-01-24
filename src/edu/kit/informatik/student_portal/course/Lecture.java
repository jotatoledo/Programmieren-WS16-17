package edu.kit.informatik.student_portal.course;

import java.util.HashSet;
import java.util.Set;

import edu.kit.informatik.student_portal.common.ICanEqual;
import edu.kit.informatik.student_portal.common.ExaminationMark;
import edu.kit.informatik.student_portal.user.Professor;

public final class Lecture extends Course implements ICanEqual {
    private final Module module;
    private final Professor professor;
    private final Set<ExaminationMark> marks;
    
    /**
     * TODO
     * @param professor TODO
     * @param module TODO
     * @param credits TODO
     * @param name TODO
     * @throws IllegalArgumentException TODO
     */
    public Lecture(final Professor professor, final Module module, 
            final int credits, final String name) throws IllegalArgumentException {
        super(credits, name);
        this.professor = professor;
        professor.addLecture(this);
        this.module = module; 
        module.addLecture(this);
        marks = new HashSet<ExaminationMark>();
    }

    /**
     * TODO
     * @param o TODO
     * @return TODO
     */
    public int compareTo(Lecture o) {
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
    
    /**
     * TODO
     * @param mark TODO
     */
    public void addMark(final ExaminationMark mark) {
        //TODO check for dupplicate
        marks.add(mark);
    }
}
