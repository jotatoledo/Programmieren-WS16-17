package edu.kit.informatik.literatur_system;

import java.util.Collection;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public class Article extends Publication {
    private final Venue venue;
    
    /**
     * TODO add doc
     * @param id TODO add doc
     * @param titel TODO add doc
     * @param year TODO add doc
     * @param venue TODO add doc
     */
    public Article(
            final String id, final String titel, 
            final short year, final Venue venue) {
        super(id, titel, year);
        // TODO test id
        this.venue = venue;
        venue.addPublication(this);
    }
    
    @Override
    public Collection<Keyword> getKeywords() {
        //TODO filter repeated?
        return Utilities.concatenatedList(venue.getKeywords(), getKeywords());
    }
}
