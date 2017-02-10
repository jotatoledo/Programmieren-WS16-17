package edu.kit.informatik.literatur_system;

/**
 * 
 * @author JoseNote
 * @version 1.00
 */
public abstract class Publication {
    private final String id;
    private final String titel;
    private final short publicationYear;
    
    
    public Publication(final String id, 
            final String titel, 
            final short publicationYear) {
        // TODO test id
        this.id = id;
        this.titel = titel;
        this.publicationYear = publicationYear;
    }
}
