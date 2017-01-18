package edu.kit.informatik.student_portal.user;

public final class Student extends User implements Comparable<Student> {
    private final int enrolmentNumber;
    
    /**
     * 
     * @param enrolmentNumber
     * @param firstName
     * @param lastName
     * @throws Exception 
     */
    public Student(final int enrolmentNumber, 
            final String firstName, final String lastName) throws IllegalArgumentException {
        super(firstName, lastName);
        if (enrolmentNumber < 0)
            //TODO add text to exception
            throw new IllegalArgumentException();
        if (String.valueOf(enrolmentNumber).length() != 6)
            throw new IllegalArgumentException();
        this.enrolmentNumber = enrolmentNumber;
    }

    @Override
    public int compareTo(Student o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }
}
