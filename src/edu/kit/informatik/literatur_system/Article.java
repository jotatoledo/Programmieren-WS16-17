package edu.kit.informatik.literatur_system;

import java.util.Collection;

import edu.kit.informatik.Utilities;

/**
 * FIXME add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Article extends Publication {
    private final Venue venue;
    
    /**
     * FIXME add doc
     * @param id FIXME add doc
     * @param titel FIXME add doc
     * @param year FIXME add doc
     * @param venue FIXME add doc
     */
    public Article(
            final String id, final String titel, 
            final short year, final Venue venue) {
        super(id, titel, year);
        // FIXME test fields
        this.venue = venue;
        venue.addPublication(this);
    }
    
    /**
     * FIXME add doc
     * @param id FIXME add doc
     */
    public Article(final String id) {
        super(id);
        venue = null;
        // TODO Auto-generated constructor stub
    }

    @Override
    public Collection<String> getKeywords() {
        // FIXME filter repeated?
        return Utilities.unify(venue.getKeywords(), getKeywords());
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
