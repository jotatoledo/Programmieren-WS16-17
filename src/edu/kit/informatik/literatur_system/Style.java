package edu.kit.informatik.literatur_system;

import java.util.Arrays;

import edu.kit.informatik.Utilities;

/**
 * An enumeration of the possible formating styles available in the literature system
 * @author JoseNote
 * @version %I%, %G%
 */
public enum Style {
    /**
     * Simplified IEEE format
     */
    IEEE("ieee"),
    /**
     * Simplified chicago format
     */
    CHICAGO("chicago");
    
    private final String strRep;
    
    private Style(final String strRep) {
        this.strRep = strRep;
    }
    
    /**
     * Gets a enumeration element whose string representation matches a given string
     * @param strRep the string representation of one enumeration element
     * @return the matching enumeration element
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
