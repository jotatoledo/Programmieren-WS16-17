package edu.kit.informatik.literatur_system;

import java.util.List;

/**
 * Class used to represent the bibliography of a journal article
 * @author JoseNote
 * @version %I%, %G%
 */
public final class JournalArticleBibliography extends ArticleBibliography {
    private final String journalTitle;
    
    /**
     * Creates a new instance
     * @param authors the authors
     * @param publicationYear the publication´s year
     * @param articleTitle the article title
     * @param journalTitle the journal title
     * @param articleId the article id
     */
    public JournalArticleBibliography(
            final List<AuthorNames> authors, final short publicationYear, 
            final String articleTitle, final String journalTitle, final String articleId) {
        super(authors, publicationYear, articleTitle, articleId);
        //TODO validate fields
        this.journalTitle = journalTitle;
    }

    @Override
    public String formatToSimplifiedIEEE(int index) {
        return String.format(
                "[%1$d] %2$s, \"%3$s,\" %4$s, %5$d.", 
                index, 
                formatAuthorsToSimplifiedIEEE(),
                getArticleTitle(), 
                journalTitle,
                getPublicationYear());
    }

    @Override
    public String formatToSimplifiedChicago() {
        return String.format(
                "(%1$s, %2$d) %3$s. \"%4$s.\" %5$s (%6$d).", 
                firstAuthorLastName(),
                getPublicationYear(),
                formatAuthorsToSimplifiedChicago(),
                getArticleTitle(),
                journalTitle,
                getPublicationYear());
    }
}
