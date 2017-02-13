package edu.kit.informatik.literatur_system;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class Venue extends TagedElement {
    /**
     * TODO add doc
     */
    protected final Set<Publication> publications;
    
    /**
     * TODO add doc
     */
    public Venue() {
        super();
        publications = new HashSet<Publication>();
    }
    
    /**
     * TODO add doc
     * @param p TODO add doc
     */
    void addPublication(final Publication p) {
        if (!publications.add(p))
            //TODO improve message
            throw new IllegalArgumentException("this publication is already assigned to this venue");
    }
    
}
