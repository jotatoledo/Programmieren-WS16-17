package edu.kit.informatik.literatur_system;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class ArticleBibliography implements Comparable<ArticleBibliography> {
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
    protected ArticleBibliography(
            final List<AuthorNames> authors, final short publicationYear, 
            final String articleTitle, final String articleId) {
        this.publicationYear = publicationYear;
        this.articleTitle = articleTitle;
        this.authors = authors;
        this.articleId = articleId;
    }
    
    @Override
    public int compareTo(ArticleBibliography obj) {
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
    
    public abstract String formatToSimplifiedIEEE(int index);
    
    public abstract String formatToSimplifiedChicago();
    
    protected String formatAuthorsToSimplifiedChicago() {
        //TODO add case size 0
        //TODO check if stream keeps order
        Stream<String> str = authors.stream()
                .map(x->x.formatChicago());
        if (authors.size() == 2)
            return str.collect(Collectors.joining(", and "));
        return str.collect(Collectors.joining(", "));
    }
    
    protected String formatAuthorsToSimplifiedIEEE() {
        //TODO add case size 0
        //TODO check if stream keeps order
        if (authors.size() == 2)
            return authors.stream()
                    .map(x-> x.formatIEEE())
                    .collect(Collectors.joining(" and "));
        else {
            AuthorNames first = authors.get(0);
            if (authors.size() == 1)
                return first.formatIEEE();
            return first.formatIEEE() + " et al.";
        }
    }
}
