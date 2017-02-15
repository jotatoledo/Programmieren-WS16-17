package edu.kit.informatik.literatur_system;

import java.util.Collection;

public abstract class Bibliography {
    private final short publicationYear;
    private final String articleTitle;
    private final Collection<AuthorNames> authors;
    
    /**
     * TODO add doc
     * @param authors TODO add doc
     * @param publicationYear TODO add doc
     * @param articleTitle TODO add doc
     */
    protected Bibliography(
            final Collection<AuthorNames> authors, final short publicationYear, final String articleTitle) {
        this.publicationYear = publicationYear;
        this.articleTitle = articleTitle;
        this.authors = authors;
    }
}
