package edu.kit.informatik.literatur_system;

import java.util.Collection;

/**
 * Contract for entities that can be marked with keywords
 * @author JoseNote
 * @version %I%, %G%
 */
public interface ITagged {
    /**
     * All the given keywords
     */
    boolean FULL_MATCH = true;
    /**
     * At least one of the given keywords
     */
    boolean PARTIAL_MATCH = false; 
    
    /**
     * Gets the keywords of this
     * @return the keywords
     */
    Collection<String> getKeywords();
    
    /**
     * Marks this with a new keyword. If this is already marked with the given keyword, nothing happens.
     * @param kw the keyword to mark this with
     */
    void addKeyword(String kw);
    
    /**
     * Marks this with the given collection of keywords. 
     * If this is already marked with one of the given keyword, the word is ignored.
     * @param kws the keywords to mark this with
     */
    void addKeyword(Collection<String> kws);
    
    /**
     * Checks if this is tagged with the given keywords
     * @param kw the keyword to match
     * @return {@code True} if this is tagged with the given keyword. {@code False} otherwise.
     */
    boolean isTagedWith(String kw);
    
    /**
     * Checks if this is tagged with all the given keywords
     * @param kws a set of keywords
     * @param matchType either {@linkplain #FULL_MATCH} or {@linkplain #PARTIAL_MATCH}
     * @return {@code True} if the keywords of this matches the given set in the given mode. {@code False} otherwise
     */
    boolean isTagedWith(Collection<String> kws, boolean matchType);
}
