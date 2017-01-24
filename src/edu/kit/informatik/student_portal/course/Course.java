package edu.kit.informatik.student_portal.course;

public class Course implements Comparable<Course> {
    private static int counter = 0;
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
        id = ++counter;
    }

    @Override
    public int hashCode() {
        //TODO implement
        final int prime = 31;
        int result = 1;
        result = prime * result + credits;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Course 
                && compareTo((Course) obj) == 0;
    }

    @Override
    public int compareTo(Course o) {
        return Integer.compare(id, o.id);
    }
}
