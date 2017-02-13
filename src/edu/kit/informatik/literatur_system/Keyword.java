package edu.kit.informatik.literatur_system;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public final class Keyword implements Comparable<Keyword> {
    private final String word;
    private final Set<ITaged> tagedElements;
    
    /**
     * TODO add doc
     * @param word TODO add doc
     */
    public Keyword(final String word) {
        this.word = word;
        //TODO edit set
        tagedElements = new HashSet<ITaged>();
   }
    
    @Override
    public int compareTo(final Keyword o) {
        return word.compareTo(o.word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Keyword
                && compareTo((Keyword) obj) == 0;
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public String getWord() {
        return word;
    }
}
