package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class Publication extends TagedElement {
    /**
     * TODO add doc
     */
    public static final boolean VALID = true;
    /**
     * TODO add doc
     */
    public static final boolean INVALID = false;
    private final String id;
    private final String titel;
    private final short publicationYear;
    private final Map<Author, Author> authors;
    private final Map<Publication, Publication> referenceToOther;
    private final Map<Publication, Publication> referenceToThis;
    
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
        authors = new LinkedHashMap<Author, Author>();
        referenceToOther = new HashMap<Publication, Publication>();
        referenceToThis = new HashMap<Publication, Publication>();
    }
    
    /**
     * TODO add doc
     * @param id TODO add doc
     */
    public Publication(final String id) {
        super();
        this.id = id;
        this.titel = null;
        this.publicationYear = 1000;
        authors = new LinkedHashMap<Author, Author>();
        referenceToOther = new HashMap<Publication, Publication>();
        referenceToThis = new HashMap<Publication, Publication>();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Publication
                && ((Publication) obj).id.compareTo(id) == 0;
    }
    
    /**
     * TODO add doc
     * @param author TODO add doc
     * @return TODO add doc
     */
    public Publication addAuthor(final Author author) {
        if (authors.containsKey(author))
            //TODO improve error message
            throw new IllegalArgumentException("this publication is already associated to the given author");
        authors.put(author, author);
        return this;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String getId() {
        return id;
    }
    
    /**
     * TODO add doc
     * @param p TODO add doc
     * @return TODO add doc
     */
    public Publication addReferenceToOther(final Publication p) {
        if (referenceToOther.containsKey(p))
            throw new IllegalArgumentException("p is already quoted by this");
        referenceToOther.put(p, p);
        return this;
    }
    
    /**
     * TODO add doc
     * @param p TODO add doc
     * @return TODO add doc
     */
    public Publication addReferenceToThis(final Publication p) {
        if (referenceToThis.containsKey(p))
            throw new IllegalArgumentException("this is already referenced by p");
        referenceToThis.put(p, p);
        return this;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public short getPublicationYear() {
        return publicationYear;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public int numberAuthors() {
        return authors.size();
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public boolean isValid() {
        return authors.size() > 0;
    }
    
    /**
     * Checks if the given author worked on this publication
     * @param author the author to check
     * @return {@code True} if the author worked on this. {@code False} otherwise
     */
    public boolean hasAuthor(final Author author) {
        return authors.containsKey(author);
    }
    
    /**
     * Gets the publications that reference to this, where the given author didnt worked
     * @param author the author to check
     * @return TODO add doc
     */
    public Collection<Publication> foreignReferencesWithoutAuthor(final Author author) {
        return referenceToThis.values().stream()
                .filter(x->!x.hasAuthor(author))
                .collect(Collectors.toList());
    }
}
