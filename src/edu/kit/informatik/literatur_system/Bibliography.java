package edu.kit.informatik.literatur_system;

import java.util.Collection;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class Bibliography {
    private final String articleId;
    private final short publicationYear;
    private final String articleTitle;
    //TODO refactor to ordered set
    private final Collection<AuthorNames> authors;
    
    /**
     * TODO add doc
     * @param authors TODO add doc
     * @param publicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @param articleId TODO add doc
     */
    protected Bibliography(
            final Collection<AuthorNames> authors, final short publicationYear, 
            final String articleTitle, final String articleId) {
        this.publicationYear = publicationYear;
        this.articleTitle = articleTitle;
        this.authors = authors;
        this.articleId = articleId;
    }

    /**
     * @return the publicationYear
     */
    public short getPublicationYear() {
        return publicationYear;
    }

    /**
     * @return the articleTitle
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * @return the authors
     */
    public Collection<AuthorNames> getAuthors() {
        return authors;
    }

    /**
     * @return the articleId
     */
    public String getArticleId() {
        return articleId;
    }
    
    public String firstAuthorLastName() {
        //TODO implement
        return null;
    }
}
