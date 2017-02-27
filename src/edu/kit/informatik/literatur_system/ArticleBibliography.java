package edu.kit.informatik.literatur_system;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class used to represent the bibliography of an article
 * @author JoseNote
 * @version %I%, %G%
 */
public abstract class ArticleBibliography implements Comparable<ArticleBibliography> {
    private final String articleId;
    private final short publicationYear;
    private final String articleTitle;
    // FIXME rework to ordered set
    private final List<AuthorNames> authors;
    
    /**
     * Creates a new instance
     * @param authors the authors of the article
     * @param publicationYear the publication year of the article
     * @param articleTitle the title of the article
     * @param articleId the ID of the article
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
    public int hashCode() {
        return Objects.hash(authors, articleTitle, publicationYear, articleId);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ArticleBibliography
                && compareTo((ArticleBibliography) obj) == 0;
    }

    @Override
    public int compareTo(ArticleBibliography obj) {
        int comp;
        for (int i = 0; i < Math.max(authors.size(), obj.authors.size()); i++) {
            comp = authors.get(i).getLastName().compareTo(obj.authors.get(i).getLastName());
            if ( comp != 0)
                return comp;
            comp = authors.get(i).getFirstName().compareTo(obj.authors.get(i).getFirstName());
            if (comp != 0)
                return comp;
        }
        if (authors.size() != obj.authors.size())
            // FIXME check
            return authors.size() - obj.authors.size();
        comp = articleTitle.compareTo(obj.articleTitle);
        if (comp != 0)
            return comp;
        comp = Short.compare(publicationYear, obj.publicationYear);
        if (comp != 0)
            return comp;
        return articleId.compareTo(obj.articleId);
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
    
    /**
     * Gets the last name of the first author of the article
     * @return the first authors last name
     */
    public String firstAuthorLastName() {
        return authors.get(0).getLastName();
    }
    
    /**
     * Generates the string representation of this in simplified IEEE format
     * @param index the index to use as bib identifier
     * @return the string representation of this in simplified IEEE format
     */
    public abstract String formatToSimplifiedIEEE(int index);
    
    /**
     * Generates the string representation of this in simplified Chicago format
     * @return the string representation of this in simplified Chicago format
     */
    public abstract String formatToSimplifiedChicago();
    
    /**
     * Generates the string representation of the authors of the article in simplified Chicago format
     * @return the string representation of the articles authors
     */
    protected String formatAuthorsToSimplifiedChicago() {
        // FIXME add case size 0
        // FIXME check if stream keeps order
        Stream<String> str = authors.stream()
                .map(x->x.formatChicago());
        if (authors.size() == 2)
            return str.collect(Collectors.joining(", and "));
        return str.collect(Collectors.joining(", "));
    }
    
    /**
     * Generates the string representation of the authors of the article in simplified IEEE format
     * @return the string representation of the articles authors
     */
    protected String formatAuthorsToSimplifiedIEEE() {
        // FIXME add case size 0
        // FIXME check if stream keeps order
        if (authors.size() == 2)
            return authors.stream()
                    .map(x-> x.formatIEEE())
                    .collect(Collectors.joining(" and "));
        else {
            final AuthorNames first = authors.get(0);
            if (authors.size() == 1)
                return first.formatIEEE();
            return first.formatIEEE() + " et al.";
        }
    }
}
