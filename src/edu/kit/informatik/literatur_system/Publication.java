package edu.kit.informatik.literatur_system;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class Publication extends TagedElement {
    private final String id;
    private final String titel;
    private final short publicationYear;
    private final Set<Author> authors;
    
    /**
     * TODO add doc
     * @param id TODO add doc
     * @param titel TODO add doc
     * @param publicationYear TODO add doc
     */
    public Publication(
            final String id, final String titel, final short publicationYear) {
        // TODO test id
        super();
        this.id = id;
        this.titel = titel;
        this.publicationYear = publicationYear;
        authors = new LinkedHashSet<Author>();
    }
    
    /**
     * TODO add doc
     * @param author TODO add doc
     * @return TODO add doc
     */
    public Publication addAuthor(final Author author) {
        if (!authors.add(author))
            //TODO improve error message
            throw new IllegalArgumentException("this publication is already associated to the given author");
        return this;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String getId() {
        return id;
    }
}
