package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * FIXME add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class Publication extends TagedElement {
    /**
     * Valid publication: has at least one author
     */
    public static final boolean VALID = true;
    /**
     * Invalid publication: has no authors
     */
    public static final boolean INVALID = false;
    
    private final String id;
    private final String title;
    private final short publicationYear;
    private final Map<Author, Author> authors;
    private final Map<Publication, Publication> referenceToOther;
    private final Map<Publication, Publication> referenceToThis;
    
    /**
     * FIXME add doc
     * @param id FIXME add doc
     * @param titel FIXME add doc
     * @param publicationYear FIXME add doc
     */
    public Publication(
            final String id, final String titel, final short publicationYear) {
        // FIXME test id
        super();
        this.id = id;
        this.title = titel;
        this.publicationYear = publicationYear;
        authors = new LinkedHashMap<Author, Author>();
        referenceToOther = new HashMap<Publication, Publication>();
        referenceToThis = new HashMap<Publication, Publication>();
    }
    
    /**
     * FIXME add doc
     * @param id FIXME add doc
     */
    public Publication(final String id) {
        super();
        this.id = id;
        this.title = null;
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
     * FIXME add doc
     * @param author FIXME add doc
     * @return FIXME add doc
     */
    public Publication addAuthor(final Author author) {
        if (authors.containsKey(author))
            //TODO improve error message
            throw new IllegalArgumentException("this publication is already associated to the given author");
        authors.put(author, author);
        return this;
    }
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    public String getId() {
        return id;
    }
    
    /**
     * FIXME add doc
     * @param p FIXME add doc
     * @return FIXME add doc
     */
    public Publication addReferenceToOther(final Publication p) {
        if (referenceToOther.containsKey(p))
            throw new IllegalArgumentException("p is already quoted by this");
        referenceToOther.put(p, p);
        return this;
    }
    
    /**
     * FIXME add doc
     * @param p FIXME add doc
     * @return FIXME add doc
     */
    public Publication addReferenceToThis(final Publication p) {
        if (referenceToThis.containsKey(p))
            throw new IllegalArgumentException("this is already referenced by p");
        referenceToThis.put(p, p);
        return this;
    }
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    public short getPublicationYear() {
        return publicationYear;
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    public int numberAuthors() {
        return authors.size();
    }
    
    /**
     * FIXME add doc
     * @return FIXME add doc
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
     * @return FIXME add doc
     */
    public Collection<Publication> foreignReferencesWithoutAuthor(final Author author) {
        return referenceToThis.values().stream()
                .filter(x->!x.hasAuthor(author))
                .collect(Collectors.toList());
    }

    /**
     * @return the authors
     */
    public Map<Author, Author> getAuthors() {
        return authors;
    }
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    public int numberReferencesToThis() {
        return referenceToThis.size();
    }
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    public abstract ArticleBibliography toBibliography();
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    protected List<AuthorNames> getAuthorNames() {
        return authors.values().stream()
                .map(a-> new AuthorNames(a.getFirstName(), a.getLastName()))
                .collect(Collectors.toList());
    }
}
