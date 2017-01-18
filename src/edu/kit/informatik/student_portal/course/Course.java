package edu.kit.informatik.student_portal.course;

public class Course {
    private static int counter = 1;
    private final int id;
    private final String name;
    private int credits;
    
    /**
     * 
     * @param credits
     * @param name
     * @throws IllegalArgumentException 
     */
    public Course(final int credits, final String name) throws IllegalArgumentException {
        this(name);
        if (credits <= 0 || credits > 9)
            //TODO add exception message
            throw new IllegalArgumentException();
        this.credits = credits;
    }
    
    /**
     * 
     * @param name
     * @throws IllegalArgumentException 
     */
    public Course(final String name) throws IllegalArgumentException {
        if (!name.matches("\\p{javaLowerCase}*"))
            //TODO add exception message
            throw new IllegalArgumentException();
        this.name = name;
        id = counter++;
    }
}
