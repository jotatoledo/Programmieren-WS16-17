package edu.kit.informatik.literatur_system;

import java.util.Arrays;
import java.util.Collection;

public final class JournalArticle extends Bibliography {
    private final String journalTitle;
    
    /**
     * TODO add doc
     * @param authors TODO add doc
     * @param publicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @param journalTitle TODO add doc
     */
    public JournalArticle(
            final Collection<AuthorNames> authors, final short publicationYear, 
            final String articleTitle, final String journalTitle) {
        super(authors, publicationYear, articleTitle);
        //TODO validate fields
        this.journalTitle = journalTitle;
    }
    
    /**
     * TODO add doc
     * @param publicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @param journalTitle TODO add doc
     * @param authors TODO add doc
     */
    public JournalArticle(
            final short publicationYear, final String articleTitle, 
            final String journalTitle, final AuthorNames... authors) {
        super(Arrays.asList(authors), publicationYear, articleTitle);
        //TODO validate fields
        this.journalTitle = journalTitle;
    }
}
