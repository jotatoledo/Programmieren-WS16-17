package edu.kit.informatik.student_portal.user;

public class User {
    private final String firstName;
    private final String lastName;
    
    /**
     * 
     * @param firstName
     * @param lastName
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
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    /**
     * 
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @return
     */
    public String getLastName() {
        return lastName;
    }
}
