package edu.kit.informatik.literatur_system;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class AuthorNames {
    private final String firstName;
    private final String lastName;
    
    /**
     * TODO add doc
     * @param firstName TODO add doc
     * @param lastName TODO add doc
     */
    public AuthorNames(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
    
    public String formatIEEE() {
        return getFirstName().toUpperCase().charAt(0) + ". " + getLastName();
    }
    
    public String formatChicago() {
        return getLastName() + "," + getFirstName();
    }
}
