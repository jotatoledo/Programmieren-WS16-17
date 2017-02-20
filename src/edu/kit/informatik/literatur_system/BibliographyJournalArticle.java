package edu.kit.informatik.literatur_system;

import java.util.Arrays;
import java.util.List;

import edu.kit.informatik.Utilities;

public final class BibliographyJournalArticle extends Bibliography {
    private final String journalTitle;
    
    /**
     * TODO add doc
     * @param authors TODO add doc
     * @param publicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @param journalTitle TODO add doc
     * @param articleId TODO add doc
     */
    public BibliographyJournalArticle(
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
    public BibliographyJournalArticle(
            final short publicationYear, final String articleTitle, 
            final String journalTitle, final String articleId,
            final AuthorNames... authors) {
        super(Arrays.asList(authors), publicationYear, articleTitle, articleId);
        //TODO validate fields
        this.journalTitle = journalTitle;
    }

    @Override
    public String formatToSimplifiedIEEE(int index) {
        return Utilities.formatToIEEESimplified(index, this);
    }

    @Override
    public String formatToSimplifiedChicago() {
        return Utilities.formatToChicagoSimplified(this);
    }
    
    /**
     * @return the journalTitle
     */
    public String getJournalTitle() {
        return journalTitle;
    }
}
