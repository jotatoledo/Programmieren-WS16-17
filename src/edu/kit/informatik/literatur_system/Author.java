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
        //TODO test name valid
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
     * Gets the value of the first name member
     * @return the value of fist name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the value of the last name member
     * @return the value of last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Associates a new publication to this author
     * @param publication the publication in which this worked
     * @return this
     */
    public Author addPublication(final Publication publication) {
        if (publications.putIfAbsent(publication, publication) != null)
            //TODO improve error message
            throw new IllegalArgumentException("this author is already associated to the given publication");
        return this;
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
}
