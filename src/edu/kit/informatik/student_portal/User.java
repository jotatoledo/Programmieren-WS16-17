package edu.kit.informatik.student_portal;

import java.util.Objects;

/**
 * TODO add doc
 * @author JoseNote
 * @version 1.00
 */
public class User implements Comparable<User>, ICanEqual {
    private final String firstName;
    private final String lastName;
    
    /**
     * TODO add doc
     * @param firstName TODO add doc
     * @param lastName TODO add doc
     */
    public User(final String firstName, final String lastName) {
        if (!firstName.matches("\\p{javaLowerCase}*"))
            throw new IllegalArgumentException("first name istn made only of lower cases");   
        if (!lastName.matches("\\p{javaLowerCase}*"))
            throw new IllegalArgumentException("lastname isnt made only of lower cases");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User 
                && ((User) obj).canEqual(this) 
                && compareTo((User) obj) == 0;
    }
    
    @Override
    public int compareTo(User o) {
        //TODO null safe
        final int compareFirstName = firstName.compareTo(o.getFirstName());
        return compareFirstName != 0 ? compareFirstName : lastName.compareTo(o.getLastName());        
    }
    
    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof User;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String getLastName() {
        return lastName;
    }
}
