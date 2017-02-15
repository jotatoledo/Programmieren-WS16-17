package edu.kit.informatik.literatur_system;

import java.util.Arrays;

import edu.kit.informatik.Utilities;

public enum Style {
    /**
     * TODO add doc
     */
    IEEE("iee"),
    /**
     * TODO add doc
     */
    CHICAGO("chicago");
    
    private final String strRep;
    
    private Style(final String strRep) {
        this.strRep = strRep;
    }
    
    /**
     * TODO add doc
     * @param strRep TODO add doc
     * @return TODO add doc
     * @throws IllegalStateException string ambiguity
     * @throws IllegalArgumentException no style with the given string representation
     */
    public static Style getStyle(final String strRep) {
        return Arrays.stream(Style.values())
                .filter(x->x.strRep.equals(strRep))
                .reduce((l, r) -> { 
                    throw new IllegalStateException("ambiguous: '" + strRep + "'"); 
                }).orElseThrow(() -> Utilities.noSuch(Style.class, strRep));
    }
}
