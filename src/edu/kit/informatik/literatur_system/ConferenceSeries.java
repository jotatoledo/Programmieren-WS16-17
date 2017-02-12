package edu.kit.informatik.literatur_system;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class ConferenceSeries {
    private final String name;
    
    /**
     * Creates a new instance
     * @param name the name value for the new instance
     */
    public ConferenceSeries(final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        //TODO implement
        return true;
    }
}
