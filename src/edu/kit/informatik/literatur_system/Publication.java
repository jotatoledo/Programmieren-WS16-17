package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.kit.informatik.InvalidRelationException;
import edu.kit.informatik.Utilities;

/**
 * Class used to represent a publication entity
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
     * Instantiates a new object
     * @param id the id value
     * @param title the title value
     * @param publicationYear the publication year value
     */
    public Publication(
            final String id, final String title, final short publicationYear) {
        // FIXME test id
        super();
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        authors = new LinkedHashMap<Author, Author>();
        referenceToOther = new HashMap<Publication, Publication>();
        referenceToThis = new HashMap<Publication, Publication>();
    }
    
    /**
     * Instantiates a partially complete object.
     * This is just a hot fix for the direct print methods on the first final assignment
     * TODO re factor/deprecate method
     * @param id the id value
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
     * Associates a new author to this
     * @param author the author to associate to this
     */
    public void addAuthor(final Author author) {
        if (authors.containsKey(author))
            //TODO improve error message
            throw new IllegalArgumentException("this publication is already associated to the given author");
        authors.put(author, author);
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * Adds a reference to other publication
     * @param p the entity being quoted by this
     * @throws InvalidRelationException if the given entity was already quoted by this
     */
    public void addReferenceToOther(final Publication p) {
        if (referenceToOther.containsKey(p))
            throw Utilities.invalidRelation(this.getClass(), p.getClass());
        referenceToOther.put(p, p);
    }
    
    /**
     * Adds a reference to this
     * @param p the entity that quotes this
     * @throws InvalidRelationException if the entity already quoted this
     */
    public void addReferenceToThis(final Publication p) {
        if (referenceToThis.containsKey(p))
            throw Utilities.invalidRelation(this.getClass(), p.getClass());
        referenceToThis.put(p, p);
    }
    
    /**
     * @return the publication year
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
     * Gets the number of authors of this
     * @return the number of authors
     */
    public int numberAuthors() {
        return authors.size();
    }
    
    /**
     * Checks if this is a valid publication
     * @return {@linkplain #VALID} or {@linkplain #INVALID}
     */
    public boolean isValid() {
        return authors.size() > 0 ? VALID : INVALID;
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
     * Gets the publications that reference to this, where the given author didn't worked
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
     * Gets the number of publications that quotes this
     * @return the number of references to this
     */
    public int numberReferencesToThis() {
        return referenceToThis.size();
    }
    
    /**
     * Generates an instance of {@linkplain ArticleBibliography} for this
     * @return an instance of {@linkplain ArticleBibliography] with the information of this
     */
    public abstract ArticleBibliography toBibliography();
    
    /**
     * Gets information from the authors of this
     * @return a collection with information of the authors of this
     */
    protected List<AuthorNames> getAuthorNames() {
        return authors.values().stream()
                .map(a-> new AuthorNames(a.getFirstName(), a.getLastName()))
                .collect(Collectors.toList());
    }
}
