package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class TagedElement implements ITaged {
    private final Set<Keyword> keywords;
    
    /**
     * TODO add doc
     */
    public TagedElement() {
        keywords = new TreeSet<Keyword>();
    }
    
    @Override
    public void addKeyword(final Keyword kw) {
        if (!keywords.add(kw))
            //TODO improve message
            throw new IllegalArgumentException("this element is already marked with this keyword");
    }
    
    @Override
    public Collection<Keyword> getKeywords() {
        return Collections.unmodifiableCollection(keywords);
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public Collection<String> getKeywordsValues() {
        return getKeywords().stream()
                .map(Keyword::getWord)
                .collect(Collectors.toList());
    }
}
