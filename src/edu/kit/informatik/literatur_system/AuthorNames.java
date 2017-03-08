package edu.kit.informatik.literatur_system;

import java.util.Objects;

/**
 * Used to store the names of an author
 * @author JoseNote
 * @version %I%, %G%
 */
public final class AuthorNames implements Comparable<AuthorNames> {
    private final String firstName;
    private final String lastName;
    
    /**
     * Instantiates a new object 
     * @param firstName the first name value
     * @param lastName the last name value
     * @throws NullPointerException if any of the parameters is {@code null}
     */
    public AuthorNames(final String firstName, final String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof AuthorNames
                && compareTo((AuthorNames) obj) == 0;
    }
    
    @Override
    public int compareTo(AuthorNames o) {
        return lastName.compareTo(o.lastName) == 0 
                ? firstName.compareTo(o.firstName) : lastName.compareTo(o.lastName);
    }
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Gets the representation of this in simplified IEEE format
     * @return the simplified IEEE representation of this
     */
    public String formatIEEE() {
        return getFirstName().toUpperCase().charAt(0) + ". " + getLastName();
    }
    
    /**
     * Gets the representation of this in simplified chicago format
     * @return the simplified chicago representation of this
     */
    public String formatChicago() {
        return getLastName() + "," + getFirstName();
    }
    
    @Override
    public String toString() {
        return getLastName() + " " + getFirstName();
    }

   
}
