package edu.kit.informatik.literatur_system;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import edu.kit.informatik.Utilities;

/**
 * Abstract class used to represent elements tagged with keywords in the system
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class TagedElement implements ITagged {
    private final Set<String> keywords;
    
    /**
     * Creates a new instance
     */
    public TagedElement() {
        keywords = new TreeSet<String>();
    }
    
    @Override
    public void addKeyword(final String kw) {
        if (!isTagedWith(kw))
            keywords.add(kw);
    }
    
    @Override
    public void addKeyword(final Collection<String> kws) {
        final Collection<String> copy = new HashSet<String>(kws);
        // Remove all mutates the collection
        copy.removeAll(this.getKeywords());
        keywords.addAll(copy);
        
    }
    
    @Override
    public Collection<String> getKeywords() {
        return Collections.unmodifiableCollection(keywords);
    }
    
    @Override
    public boolean isTagedWith(final String kw) {
        return this.getKeywords().contains(kw);
    }
    
    @Override
    public boolean isTagedWith(final Collection<String> kws, final boolean matchType) {
//        this method mutates the keyword collection
//        final boolean collectionChange = kws.removeAll(this.getKeywords());
//        return matchType == ITagged.FULL_MATCH ? kws.isEmpty() : collectionChange;
        final int intersectSize = Utilities.intersectCollection(Arrays.asList(kws, this.getKeywords())).size();
        return matchType == ITagged.FULL_MATCH ? intersectSize == kws.size() : intersectSize > 0;
    }
}
