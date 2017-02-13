package edu.kit.informatik.literatur_system;

import java.util.Collection;

/**
 * Contract for the literature system service
 * @author JoseNote
 * @version %I%, %G%
 */
public interface ILiteraturSystemService {
    /**
     * TODO add doc
     * C4
     * @param conferenceSeriesId TODO add doc
     * @param year TODO add doc
     * @param location TODO add doc
     * @return TODO add doc
     */
    Conference addConference(String conferenceSeriesId, short year, String location);
    
    /**
     * Creates a new author entity and persists it in the service
     * C1
     * @param firstName the first name value for the new entity
     * @param lastName the last name value for the new entity
     * @return the new entity
     */
    Author addAuthor(String firstName, String lastName);
    
    /**
     * TODO add doc
     * @param names TODO add doc
     * @return TODO add doc
     */
    Collection<Author> getAuthor(Collection<AuthorNames> names);
    
    /**
     * Checks for the existence of an author entity
     * @param firstName the first name value to match against
     * @param lastName the last name value to match against
     * @return {@code True} if there is an author entity with the given parameters. {@code False} otherwise
     */
    boolean existAuthor(String firstName, String lastName);
    
    /**
     * Creates a new journal entity and persists it in the service
     * C2
     * @param name the name value for the new entity
     * @param publisher the publisher value for the new entity
     * @return the new entity
     */
    Journal addJournal(String name, String publisher);
    
    /**
     * Checks for the existence of a journal entity
     * @param name the name value to match against
     * @return {@code True} if there is a journal entity with the given parameters. {@code False} otherwise
     */
    boolean existJournal(String name);
    
    /**
     * Creates a new conference series entity and persists it in the service
     * C3
     * @param name the name value for the new entity
     * @return the new entity
     */
    ConferenceSeries addConferenceSeries(String name);
    
    /**
     * TODO add doc
     * C6
     * @param publicationId TODO add doc
     * @param authors TODO add doc
     */
    void writtenBy(String publicationId, Collection<AuthorNames> authors);
    
    /**
     * TODO add doc
     * @param id TODO add doc
     * @return TODO add doc
     */
    Publication getPublication(String id);
    
    /**
     * TODO add doc
     * C10
     * @param onlyValid TODO add doc
     * @return TODO add doc
     */
    Collection<Publication> getPublication(boolean onlyValid);
    
    /**
     * TODO add doc
     * C9
     * @param authors TODO add doc
     * @return TODO add doc
     */
    Collection<Publication> getPublication(Collection<AuthorNames> authors);
    
    /**
     * TODO add doc
     * C7
     * @param quoter TODO add doc
     * @param reference TODO add doc
     */
    void cites(String quoter, String reference);
    
    /**
     * TODO add doc
     * Part of C5
     * @param seriesId TODO add doc
     * @param articleId TODO add doc
     * @param articlePublicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @return TODO add doc
     */
    Article addArticleToSeries(String seriesId, String articleId, short articlePublicationYear, String articleTitle);
    
    /**
     * TODO add doc
     * Part of C5
     * @param journalId TODO add doc
     * @param articleId TODO add doc
     * @param articlePublicationYear TODO add doc
     * @param articleTitle TODO add doc
     * @return TODO add doc
     */
    Article addArticleToJournal(String journalId, String articleId, short articlePublicationYear, String articleTitle);
}
