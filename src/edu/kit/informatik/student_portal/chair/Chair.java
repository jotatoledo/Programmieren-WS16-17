package edu.kit.informatik.student_portal.chair;

import java.util.HashSet;
import java.util.Set;
import edu.kit.informatik.student_portal.user.Professor;

public final class Chair implements Comparable<Chair> {
    private final String name;
    private final Set<Professor> professors;
    
    /**
     * TODO
     * @param name The name for the new instance
     * @throws IllegalArgumentException TODO
     */
    public Chair(final String name) throws IllegalArgumentException {
        if (name == null)
            throw new IllegalArgumentException("The value of the parameter 'name' is null");
        if (!name.matches("\\p{javaLowerCase}*"))
            throw new IllegalArgumentException("Name of the chair isnt made exclusively of lowcase letters");
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
    public int compareTo(Chair o) {
        return name.compareTo(o.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Chair
                && compareTo((Chair) obj) == 0;
    }

    /**
     * 
     * @return TODO
     */
    public String getName() {
        return name;
    }
}
