package edu.kit.informatik.literatur_system;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class ConferenceSeries extends TagedElement {
    private final String name;
    //TODO refactor to allow multiple conferences in one year
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

//    @Override
//    public int compareTo(ConferenceSeries o) {
//        return name.compareTo(o.name);
//    }
    
    /**
     * TODO add doc
     * @param c TODO add doc
     * @return TODO add doc
     */
    public ConferenceSeries addConference(final Conference c) {
        //TODO check wrapping of short value
        if (conferences.containsKey(c.getYear()))
            throw new IllegalArgumentException("there is already a conference in this year for this series");
        conferences.put(c.getYear(), c);
        return this;
    }
    
    /**
     * TODO add doc
     * @param year TODO add doc
     * @return TODO add doc
     */
    public Conference getConferenceInYear(final short year) {
        return conferences.get(year);
    }
}
