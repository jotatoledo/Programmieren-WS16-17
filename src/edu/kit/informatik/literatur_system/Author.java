package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class used to represent an author entity
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Author implements Comparable<Author> {
    private final String firstName;
    private final String lastName;
    private final Map<Publication, Publication> publications;
    
    /**
     * Creates a new instance
     * @param firstName the value for the first name member
     * @param lastName the value for the last name member
     */
    public Author(
            final String firstName,
            final String lastName) {
        Objects.requireNonNull(firstName, "null first name");
        Objects.requireNonNull(lastName, "null last name");
        this.firstName = firstName;
        this.lastName = lastName;
        publications = new HashMap<Publication, Publication>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Author
                && compareTo((Author) obj) == 0;
    }

    @Override
    public int compareTo(Author o) {
        return lastName.compareTo(o.lastName) == 0 
                ? firstName.compareTo(o.firstName) : lastName.compareTo(o.lastName);
    }
    
    /**
     * Associates a new publication to this author
     * @param publication the publication in which this worked
     */
    public void addPublication(final Publication publication) {
        if (publications.putIfAbsent(publication, publication) != null)
            // FIXME improve error message
            throw new IllegalArgumentException("this author is already associated to the given publication");
    }
    
    /**
     * Gets a set of publications in which this author didn't worked, that
     * quote at least one of this author publications.
     * @return a collection of publications
     */
    public Collection<Publication> getForeignPublications() {
        return publications.values().stream()
                .map(x -> x.foreignReferencesWithoutAuthor(this))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * @return the publications
     */
    public Map<Publication, Publication> getPublications() {
        return publications;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * @return a new instance of {@linkplain AuthorNames} using the information of this
     */
    public AuthorNames toAuthorNames() {
        return new AuthorNames(firstName, lastName);
    }
}
