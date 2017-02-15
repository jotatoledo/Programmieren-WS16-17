package edu.kit.informatik.literatur_system;

import java.util.Arrays;
import java.util.Collection;

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
     */
    public ConferenceArticle(
            final String conferenceSeriesName, final String conferenceLocation, final short conferenceYear,
            final Collection<AuthorNames> authors, final short publicationYear, final String articleTitle) {
        super(authors, publicationYear, articleTitle);
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
     * @param authors TODO add doc
     */
    public ConferenceArticle(
            final String conferenceSeriesName, final String conferenceLocation, final short conferenceYear,
            final short publicationYear, final String articleTitle, final AuthorNames... authors) {
        super(Arrays.asList(authors), publicationYear, articleTitle);
        //TODO validate fields
        this.conferenceLocation = conferenceLocation;
        this.conferenceSeriesName = conferenceSeriesName;
        this.conferenceYear = conferenceYear;
    }
}
