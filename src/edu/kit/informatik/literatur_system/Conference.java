package edu.kit.informatik.literatur_system;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Conference {
    private final String location;
    private final short year;
    private final ConferenceSerie serie;
    
    /**
     * TODO add doc
     * @param location the location value for the new instance
     * @param year the year value for the new instance
     * @param serie TODO add doc
     */
    public Conference(
            final String location, final short year, final ConferenceSerie serie) {
        this.location = location;
        this.year = year;
        this.serie = serie;
    }
}
