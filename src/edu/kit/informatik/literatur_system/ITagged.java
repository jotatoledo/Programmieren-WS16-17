package edu.kit.informatik.literatur_system;

import java.util.Collection;

/**
 * FIXME add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public interface ITagged {
    /**
     * FIXME add doc
     */
    boolean FULL_MATCH = true;
    /**
     * FIXME add doc
     */
    boolean PARTIAL_MATCH = false; 
    
    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    Collection<String> getKeywords();
    
    /**
     * FIXME add doc
     * @param kw FIXME add doc
     */
    void addKeyword(String kw);
    
    /**
     * FIXME add doc
     * @param kws FIXME add doc
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
