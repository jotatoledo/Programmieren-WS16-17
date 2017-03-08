package edu.kit.informatik.literatur_system;

import java.util.HashMap;
import java.util.Map;

import edu.kit.informatik.InvalidRelationException;

/**
 * Represents a conference series entity in the system
 * @author JoseNote
 * @version %I%, %G%
 */
public final class ConferenceSeries extends TagedElement {
    private final String name;
    private final Map<Short, Conference> conferences;
    
    /**
     * Creates a new instance
     * @param name the name value for the new instance
     */
    public ConferenceSeries(final String name) {
        super();
        this.name = name;
        conferences = new HashMap<Short, Conference>();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ConferenceSeries
                && ((ConferenceSeries) obj).name.compareTo(name) == 0;
    }
    
    /**
     * Adds a {@linkplain Conference} entity to this
     * @param c the {@linkplain Conference} entity to add
     * @throws InvalidRelationException if there is already a {@linkplain Conference} entity 
     * associated to the year of the given one
     */
    public void addConference(final Conference c) {
        if (conferences.containsKey(c.getYear()))
            throw new InvalidRelationException("there is already a conference in this year for this series");
        conferences.put(c.getYear(), c);
    }
    
    /**
     * Gets the {@linkplain Conference} entity associated to a given year in this
     * @param year the year associated to the searched entity
     * @return the {@linkplain Conference} entity associated to the given year.
     * {@code Null} if there is no entity associated to the year in this
     */
    public Conference getConferenceInYear(final short year) {
        // FIXME consistent get -> add throw if null
        return conferences.get(year);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
