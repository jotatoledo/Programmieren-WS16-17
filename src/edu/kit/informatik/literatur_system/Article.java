package edu.kit.informatik.literatur_system;

import java.util.Collection;

import edu.kit.informatik.Utilities;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Article extends Publication {
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
        // TODO test fields
        this.venue = venue;
        venue.addPublication(this);
    }
    
    /**
     * TODO add doc
     * @param id TODO add doc
     */
    public Article(final String id) {
        super(id);
        venue = null;
        // TODO Auto-generated constructor stub
    }

    @Override
    public Collection<Keyword> getKeywords() {
        //TODO filter repeated?
        return Utilities.unify(venue.getKeywords(), getKeywords());
    }
}
