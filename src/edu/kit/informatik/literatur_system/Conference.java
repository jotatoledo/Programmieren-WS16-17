package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.Objects;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Conference extends TagedElement implements Comparable<Conference> {
    private final String location;
    private final short year;
    private final ConferenceSeries serie;
    
    /**
     * TODO add doc
     * @param location the location value for the new instance
     * @param year the year value for the new instance
     * @param serie TODO add doc
     */
    public Conference(
            final String location, final short year, final ConferenceSeries serie) {
        super();
        this.location = location;
        this.year = year;
        this.serie = serie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serie, year);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Conference
                && compareTo((Conference) obj) == 0;
    }

    @Override
    public int compareTo(Conference o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Keyword> getKeywords() {
        //TODO filter repeated?
        return Utilities.concatenatedList(getKeywords(), serie.getKeywords());
    }
}
