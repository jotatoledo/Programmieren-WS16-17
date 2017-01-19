package edu.kit.informatik.student_portal.professorial_chair;

import java.util.HashSet;
import java.util.Set;
import edu.kit.informatik.student_portal.user.Professor;

public final class ProfessorialChair implements Comparable<ProfessorialChair> {
    private final String name;
    private final Set<Professor> professors;
    
    /**
     * TODO
     * @param name The name for the new instance
     * @throws IllegalArgumentException TODO
     */
    public ProfessorialChair(final String name) throws IllegalArgumentException {
        if (!name.matches("\\p{javaLowerCase}*"))
            //TODO add exception message
            throw new IllegalArgumentException();
        this.name = name;
        this.professors = new HashSet<Professor>();
    }
    
    /**
     * TODO
     * @param professor TODO
     */
    public void addProfessor(final Professor professor) {
        if (!professors.add(professor))
            throw new IllegalArgumentException();
    }

    @Override
    public int compareTo(ProfessorialChair o) {
        return name.compareTo(o.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((professors == null) ? 0 : professors.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ProfessorialChair
                && compareTo((ProfessorialChair) obj) == 0;
    }
}
