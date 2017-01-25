package edu.kit.informatik.student_portal;

/**
 * TODO add doc
 * @author JoseNote
 * @version 1.00
 */
public abstract class Course implements Comparable<Course>, ICanEqual {
    private static int instanceCounter = 1;
    private final int id;
    private final String name;
    private int credits;

    /**
     * TODO add doc
     * @param credits TODO add doc
     * @param name TODO add doc
     */
    public Course(final int credits, 
            final String name) {
        this(name);
        ValueTester.testValidCredits(credits);
        this.credits = credits;
    }

    /**
     * TODO add doc
     * @param name TODO add doc
     */
    public Course(final String name) {
        this.name = name;
        id = instanceCounter++;
    }

    @Override
    public int hashCode() {
       return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Course 
                && ((Course) obj).canEqual(this)
                && compareTo((Course) obj) == 0;
    }

    @Override
    public int compareTo(Course o) {
        return Integer.compare(id, o.id);
    }

    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof Course;
    }

    /**
     * TODO add doc
     * @return TODO add doc
     */
    public int getId() {
        return id;
    }

    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String getName() {
        return name;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public int getCredits() {
        return credits;
    }
}
