package edu.kit.informatik.literatur_system;

import java.util.Collection;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public interface ITaged {
    /**
     * TODO add doc
     * @return TODO add doc
     */
    Collection<Keyword> getKeywords();
    
    /**
     * TODO add doc
     * @param kw TODO add doc
     */
    void addKeyword(Keyword kw);
}
