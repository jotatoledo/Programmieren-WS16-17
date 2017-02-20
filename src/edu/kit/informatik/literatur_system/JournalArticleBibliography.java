package edu.kit.informatik.literatur_system;

import java.util.Arrays;
import java.util.List;

import edu.kit.informatik.Utilities;

public final class JournalArticleBibliography extends ArticleBibliography {
    private final String journalTitle;
    
    /**
     * TODO add doc
     * @param authors TODO add doc
     * @param publicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @param journalTitle TODO add doc
     * @param articleId TODO add doc
     */
    public JournalArticleBibliography(
            final List<AuthorNames> authors, final short publicationYear, 
            final String articleTitle, final String journalTitle, final String articleId) {
        super(authors, publicationYear, articleTitle, articleId);
        //TODO validate fields
        this.journalTitle = journalTitle;
    }
    
    /**
     * TODO add doc
     * @param publicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @param journalTitle TODO add doc
     * @param articleId TODO add doc
     * @param authors TODO add doc
     */
    public JournalArticleBibliography(
            final short publicationYear, final String articleTitle, 
            final String journalTitle, final String articleId,
            final AuthorNames... authors) {
        super(Arrays.asList(authors), publicationYear, articleTitle, articleId);
        //TODO validate fields
        this.journalTitle = journalTitle;
    }

    @Override
    public String formatToSimplifiedIEEE(int index) {
        return String.format(
                "[%1$] %2$, \"%3$,\" %4$, %5$.", 
                index, 
                formatAuthorsToSimplifiedIEEE(),
                getArticleTitle(), 
                getJournalTitle(),
                getPublicationYear());
    }

    @Override
    public String formatToSimplifiedChicago() {
        return String.format(
                "(%1$, %2$) %3$. \"%4$.\" %5$ (%6$).", 
                firstAuthorLastName(),
                getPublicationYear(),
                formatAuthorsToSimplifiedChicago(),
                getArticleTitle(),
                getJournalTitle(),
                getPublicationYear());
    }
    
    /**
     * @return the journalTitle
     */
    public String getJournalTitle() {
        return journalTitle;
    }
}
