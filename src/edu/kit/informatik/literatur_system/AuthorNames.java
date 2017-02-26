package edu.kit.informatik.literatur_system;

/**
 * FIXME add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class AuthorNames {
    private final String firstName;
    private final String lastName;
    
    /**
     * FIXME add doc
     * @param firstName FIXME add doc
     * @param lastName FIXME add doc
     */
    public AuthorNames(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AuthorNames
                && ((AuthorNames) obj).getFirstName() == getFirstName()
                && ((AuthorNames) obj).getLastName() == getLastName();
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
     * FIXME add doc
     * @return FIXME add doc
     */
    public String formatIEEE() {
        return getFirstName().toUpperCase().charAt(0) + ". " + getLastName();
    }
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    public String formatChicago() {
        return getLastName() + "," + getFirstName();
    }
}
