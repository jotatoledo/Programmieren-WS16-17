package edu.kit.informatik.student_portal.course;

import edu.kit.informatik.student_portal.user.Professor;

public final class Lecture extends Course {
    private final Module module;
    private final Professor professor;
    
    /**
     * 
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
    }

    /**
     * 
     * @param o TODO
     * @return TODO
     */
    public int compareTo(Lecture o) {
        return super.compareTo(o);
    }

    @Override
    public int hashCode() {
        //TODO implement
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((module == null) ? 0 : module.hashCode());
        result = prime * result + ((professor == null) ? 0 : professor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Lecture
                && super.equals(obj)
                && module.equals(((Lecture) obj).module);
    }
}
