package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.Objects;

import edu.kit.informatik.Utilities;

/**
 * Represents a conference in the system
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Conference extends Venue {
    private final String location;
    private final short year;
    private final ConferenceSeries serie;
    
    /**
     * Instantiates a new entity
     * @param location the location value for the new instance
     * @param year the year value for the new instance
     * @param serie the conference series entity to which this conference is associated
     */
    public Conference(
            final String location, final short year, final ConferenceSeries serie) {
        super();
        Objects.requireNonNull(location);
        Objects.requireNonNull(serie);
        // FIXME test valid year
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
        return Utilities.unifyNoRepetition(super.getKeywords(), serie.getKeywords());
    }
    
    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the year
     */
    public short getYear() {
        return year;
    }
    
    /**
     * @return the name of the conference series
     */
    public String getConferenceSeriesName() {
        return serie.getName();
    }
}
