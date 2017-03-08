package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.Objects;

import edu.kit.informatik.Utilities;

/**
 * Represents an article entity in the system
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Article extends Publication {
    private final Venue venue;
    
    /**
     * Instantiates a new entity
     * @param id the id value
     * @param title the title
     * @param publicationYear the publication year value
     * @param venue a venue entity, which represents the venue where the new entity was published
     * @throws NullPointerException if any of the passed parameters is {@code null}
     */
    public Article(
            final String id, final String title, 
            final short publicationYear, final Venue venue) {
        super(id, title, publicationYear);
        Objects.requireNonNull(venue, "null venue");
        this.venue = venue;
        venue.addPublication(this);
    }
    
    /**
     * Instantiates a new entity, partially complete.
     * Hot fix method for direct print methods in first final assignment
     * FIXME deprecate/remove
     * @param id the id value
     */
    public Article(final String id) {
        super(id);
        venue = null;
    }

    @Override
    public Collection<String> getKeywords() {
        return Utilities.unifyNoRepetition(venue.getKeywords(), super.getKeywords());
    }
    
    @Override
    public ArticleBibliography toBibliography() {
        if (venue instanceof Journal)
            return new JournalArticleBibliography(
                    getAuthorNames(), 
                    getPublicationYear(), 
                    getTitle(), 
                    ((Journal) venue).getName(), 
                    getId());
        else
            return new ConferenceArticleBibliography(
                    ((Conference) venue).getConferenceSeriesName(), 
                    ((Conference) venue).getLocation(), 
                    getPublicationYear(), 
                    getAuthorNames(), 
                    getPublicationYear(), 
                    getTitle(), 
                    getId());
    }
}
