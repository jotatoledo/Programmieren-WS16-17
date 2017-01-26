package edu.kit.informatik.student_portal;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a chair in the university system
 * @author JoseNote
 * @version 1.00
 */
public final class Chair implements Comparable<Chair> {
    private final String name;
    private final Set<Professor> professors;
    
    /**
     * Creates a new instance
     * @param name The name for the new instance
     */
    public Chair(final String name) {
        if (name == null)
            throw new IllegalArgumentException("the value of the parameter 'name' is null");
        if (!name.matches("\\p{javaLowerCase}*"))
            throw new IllegalArgumentException("name of the chair isnt made exclusively of lowcase letters");
        this.name = name;
        this.professors = new HashSet<Professor>();
    }
    
    /**
     * TODO add doc
     * @param professor TODO add doc
     */
    public void addProfessor(final Professor professor) {
        if (!professors.add(professor))
            throw new IllegalArgumentException("there is already a professor with this name on this chair");
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
     * Getter for name member
     * @return the value of the name member
     */
    public String getName() {
        return name;
    }
}
