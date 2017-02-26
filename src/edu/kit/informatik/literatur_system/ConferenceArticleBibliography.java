package edu.kit.informatik.literatur_system;

import java.util.List;

/**
 * Class used to represent the bibliography of a conference article
 * @author JoseNote
 * @version %I%, %G%
 */
public final class ConferenceArticleBibliography extends ArticleBibliography {
    private final String conferenceSeriesName;
    private final String conferenceLocation;
    private final short conferenceYear;
    
    /**
     * Creates a new instance
     * @param conferenceSeriesName the conference series name
     * @param conferenceLocation the conference location
     * @param conferenceYear the conference year
     * @param authors the authors of the article
     * @param publicationYear the publication year of the article
     * @param articleTitle the title of the article
     * @param articleId the article ID
     */
    public ConferenceArticleBibliography(
            final String conferenceSeriesName, final String conferenceLocation, final short conferenceYear,
            final List<AuthorNames> authors, final short publicationYear, final String articleTitle,
            final String articleId) {
        super(authors, publicationYear, articleTitle, articleId);
        // FIXME validate fields
        this.conferenceLocation = conferenceLocation;
        this.conferenceSeriesName = conferenceSeriesName;
        this.conferenceYear = conferenceYear;
    }

    @Override
    public String formatToSimplifiedIEEE(int index) {
        return String.format(
                "[%1$d] %2$s, \"%3$s,\" in Proceedings of %4$s, %5$s, %6$d.", 
                index,
                formatAuthorsToSimplifiedIEEE(),
                getArticleTitle(),
                conferenceSeriesName,
                conferenceLocation,
                conferenceYear);
    }

    @Override
    public String formatToSimplifiedChicago() {
        return String.format(
                "(%1$s, %2$d) %3$s. \"%4$s.\" Paper presented at %5$s, %6$d, %7$s.", 
                firstAuthorLastName(),
                getPublicationYear(),
                formatAuthorsToSimplifiedChicago(),
                getArticleTitle(),
                conferenceSeriesName,
                conferenceYear,
                conferenceLocation);
    }
}
