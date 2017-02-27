package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.Objects;

import edu.kit.informatik.Utilities;

/**
 * FIXME add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Conference extends Venue {
    private final String location;
    private final short year;
    private final ConferenceSeries serie;
    
    /**
     * FIXME add doc
     * @param location the location value for the new instance
     * @param year the year value for the new instance
     * @param serie FIXME add doc
     */
    public Conference(
            final String location, final short year, final ConferenceSeries serie) {
        super();
        // FIXME check input
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
                && ((Conference) obj).year == year
                && ((Conference) obj).serie.equals(serie);
    }

    @Override
    public Collection<String> getKeywords() {
        // FIXME filter repeated?
        return Utilities.unify(getKeywords(), serie.getKeywords());
    }
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    public String getLocation() {
        return location;
    }

    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    public short getYear() {
        return year;
    }
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    public String getConferenceSeriesName() {
        return serie.getName();
    }
}
