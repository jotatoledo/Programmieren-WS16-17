package edu.kit.informatik.student_portal.course;

import edu.kit.informatik.student_portal.common.ICanEqual;

public class Course implements Comparable<Course>, ICanEqual {
    private static int instanceCounter = 1;
    private final int id;
    private final String name;
    private int credits;

    

    /**
     * 
     * @param credits TODO
     * @param name TODO
     * @throws IllegalArgumentException TODO
     */
    public Course(final int credits, 
            final String name) throws IllegalArgumentException {
        this(name);
        if (credits < 0 || credits > 9)
            throw new IllegalArgumentException(credits < 0 ? "Smaller 0" : "Larger 9");
        this.credits = credits;
    }

    /**
     * 
     * @param name TODO
     * @throws IllegalArgumentException TODO
     */
    public Course(final String name) throws IllegalArgumentException {
        if (!name.matches("\\p{javaLowerCase}*"))
            throw new IllegalArgumentException("Name isnt only lowercase letters");
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
