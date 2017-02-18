package edu.kit.informatik.literatur_system;

import java.util.List;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class Bibliography implements Comparable<Bibliography> {
    private final String articleId;
    private final short publicationYear;
    private final String articleTitle;
    //TODO refactor to ordered set
    private final List<AuthorNames> authors;
    
    /**
     * TODO add doc
     * @param authors TODO add doc
     * @param publicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @param articleId TODO add doc
     */
    protected Bibliography(
            final List<AuthorNames> authors, final short publicationYear, 
            final String articleTitle, final String articleId) {
        this.publicationYear = publicationYear;
        this.articleTitle = articleTitle;
        this.authors = authors;
        this.articleId = articleId;
    }
    
    @Override
    public int compareTo(Bibliography obj) {
        //TODO implement
        return 0;
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
    public List<AuthorNames> getAuthors() {
        return authors;
    }

    /**
     * @return the articleId
     */
    public String getArticleId() {
        return articleId;
    }
    
    public String firstAuthorLastName() {
        return authors.get(0).getLastName();
    }
}
