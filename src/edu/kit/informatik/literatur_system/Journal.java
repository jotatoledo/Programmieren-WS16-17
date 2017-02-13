package edu.kit.informatik.literatur_system;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Journal extends Venue {
    private final String name;
    private final String publisher;
    
    /**
     * Creates a new instance
     * @param name TODO add doc
     * @param publisher TODO add doc
     */
    public Journal(final String name, final String publisher) {
        super();
        this.name = name;
        this.publisher = publisher;
    }

    /**
     * TODO add doc
     * @param name TODO add doc
     */
    public Journal(final String name) {
        super();
        this.name = name;
        publisher = null;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Journal
                && ((Journal) obj).name.compareTo(name) == 0;
    }
}
