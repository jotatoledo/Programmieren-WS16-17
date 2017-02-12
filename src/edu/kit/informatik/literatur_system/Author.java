package edu.kit.informatik.literatur_system;

import java.util.Objects;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Author implements Comparable<Author> {
    private final String firstName;
    private final String lastName;
    
    /**
     * TODO add doc
     * @param firstName add doc
     * @param lastName add doc
     */
    public Author(final String firstName,
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
        return obj instanceof Author
                && compareTo((Author) obj) == 0;
    }

    @Override
    public int compareTo(Author o) {
        return lastName.compareTo(o.lastName) == 0 
                ? firstName.compareTo(o.firstName) : lastName.compareTo(o.lastName);
    }
    
    /**
     * Gets the value of the first name member
     * @return the value of fist name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the value of the last name member
     * @return the value of last name
     */
    public String getLastName() {
        return lastName;
    }
}
