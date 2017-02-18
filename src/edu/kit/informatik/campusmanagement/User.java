
package edu.kit.informatik.campusmanagement;

/**
 * A user of a campus management system.
 * 
 * @author  Tobias Bachert
 * @version 1.00, 2016/08/11
 */
abstract class User {
    
    private final String forename;
    private final String surname;
    
    /**
     * Creates a new {@code User} with the provided arguments.
     * 
     * @param forename the forename
     * @param surname the surname
     */
    protected User(
            final String forename,
            final String surname) {
        ////
        this.forename = forename;
        this.surname  = surname;
    }
    
    /**
     * Returns the forename.
     * 
     * @return the forename
     */
    public final String forename() {
        ////
        return forename;
    }
    
    /**
     * Returns the surname.
     * 
     * @return the surname
     */
    public final String surname() {
        ////
        return surname;
    }
    
    @Override
    public String toString() {
        ////
        return getClass().getSimpleName() + "[" + surname() + "," + forename() + "]";
    }
}
