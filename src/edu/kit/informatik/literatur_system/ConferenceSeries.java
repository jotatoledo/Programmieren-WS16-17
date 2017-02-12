package edu.kit.informatik.literatur_system;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class ConferenceSeries extends Venue {
    private final String name;
    //TODO refactor to map<year,Collection<conference>)
    private final Set<Conference> conferences;
    
    /**
     * Creates a new instance
     * @param name the name value for the new instance
     */
    public ConferenceSeries(final String name) {
        super();
        this.name = name;
        conferences = new HashSet<Conference>();
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
     * @param c add doc
     * @return add doc
     */
    public ConferenceSeries addConference(final Conference c) {
        if (!conferences.add(c))
            //TODO improve message
            throw new IllegalArgumentException("the conference is already assigned to this series");
        return this;
    }
}
