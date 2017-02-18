package edu.kit.informatik.literatur_system;

import java.util.Arrays;
import java.util.List;

public final class ConferenceArticle extends Bibliography {
    private final String conferenceSeriesName;
    private final String conferenceLocation;
    private final short conferenceYear;
    
    /**
     * TODO add doc
     * @param conferenceSeriesName TODO add doc
     * @param conferenceLocation TODO add doc
     * @param conferenceYear TODO add doc
     * @param authors TODO add doc
     * @param publicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @param articleId TODO add doc
     */
    public ConferenceArticle(
            final String conferenceSeriesName, final String conferenceLocation, final short conferenceYear,
            final List<AuthorNames> authors, final short publicationYear, final String articleTitle,
            final String articleId) {
        super(authors, publicationYear, articleTitle, articleId);
        //TODO validate fields
        this.conferenceLocation = conferenceLocation;
        this.conferenceSeriesName = conferenceSeriesName;
        this.conferenceYear = conferenceYear;
    }
    
    /**
     * TODO add doc
     * @param conferenceSeriesName TODO add doc
     * @param conferenceLocation TODO add doc
     * @param conferenceYear TODO add doc
     * @param publicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @param articleId TODO add doc
     * @param authors TODO add doc
     */
    public ConferenceArticle(
            final String conferenceSeriesName, final String conferenceLocation, final short conferenceYear,
            final short publicationYear, final String articleTitle, final String articleId,
            final AuthorNames... authors) {
        super(Arrays.asList(authors), publicationYear, articleTitle, articleId);
        //TODO validate fields
        this.conferenceLocation = conferenceLocation;
        this.conferenceSeriesName = conferenceSeriesName;
        this.conferenceYear = conferenceYear;
    }

    /**
     * @return the conferenceSeriesName
     */
    public String getConferenceSeriesName() {
        return conferenceSeriesName;
    }

    /**
     * @return the conferenceLocation
     */
    public String getConferenceLocation() {
        return conferenceLocation;
    }

    /**
     * @return the conferenceYear
     */
    public short getConferenceYear() {
        return conferenceYear;
    }
}