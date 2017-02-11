package edu.kit.informatik.literatur_system;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class Publication {
    private final String id;
    private final String titel;
    private final short publicationYear;
    
    /**
     * TODO add doc
     * @param id TODO add doc
     * @param titel TODO add doc
     * @param publicationYear TODO add doc
     */
    public Publication(final String id, 
            final String titel, 
            final short publicationYear) {
        // TODO test id
        this.id = id;
        this.titel = titel;
        this.publicationYear = publicationYear;
    }
}
