package edu.kit.informatik.student_portal.user;

import java.util.Objects;

import edu.kit.informatik.student_portal.common.ICanEqual;

public class User implements Comparable<User>, ICanEqual {
    private final String firstName;
    private final String lastName;
    
    /**
     * TODO
     * @param firstName TODO
     * @param lastName TODO
     * @throws IllegalArgumentException 
     */
    public User(final String firstName, final String lastName) throws IllegalArgumentException {
        if (!firstName.matches("\\p{javaLowerCase}*"))
            //TODO add exception message
            throw new IllegalArgumentException();   
        if (!lastName.matches("\\p{javaLowerCase}*"))
            //TODO add exception message
            throw new IllegalArgumentException();
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

    /**
     * TODO
     * @return TODO
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * TODO
     * @return TODO
     */
    public String getLastName() {
        return lastName;
    }

    @Override
    public int compareTo(User o) {
        //TODO null safe
        final int compareFirstName = firstName.compareTo(o.getFirstName());
        if (compareFirstName != 0)
            return compareFirstName;
        return lastName.compareTo(o.getLastName());        
    }
    
    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof User;
    }
}
