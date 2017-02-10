package edu.kit.informatik.literatur_system;

import java.util.Objects;

/**
 * TODO add doc
 * @author JoseNote
 * @version 1.00
 */
public final class Autor implements Comparable<Autor> {
    private final String firstName;
    private final String lastName;
    
    /**
     * TODO add doc
     * @param firstName add doc
     * @param lastName add doc
     */
    public Autor(final String firstName,
            final String lastName) {
        //TODO test name valid
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Autor
                && compareTo((Autor) obj) == 0;
    }

    @Override
    public int compareTo(Autor o) {
        return lastName.compareTo(o.lastName) == 0 
                ? firstName.compareTo(o.firstName) : lastName.compareTo(o.lastName);
    }
}
