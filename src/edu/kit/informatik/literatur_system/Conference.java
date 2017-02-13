package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.Objects;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Conference extends Venue {
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
        serie.addConference(this);
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

//    @Override
//    public int compareTo(Conference o) {
//        // TODO Auto-generated method stub
//        return 0;
//    }

    @Override
    public Collection<Keyword> getKeywords() {
        //TODO filter repeated?
        return Utilities.concatenatedList(getKeywords(), serie.getKeywords());
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String getLocation() {
        return location;
    }

    /**
     * TODO add doc
     * @return TODO add doc
     */
    public short getYear() {
        return year;
    }
}
