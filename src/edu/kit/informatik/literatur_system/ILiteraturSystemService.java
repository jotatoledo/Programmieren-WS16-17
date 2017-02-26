package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.List;

import edu.kit.informatik.EntityAlreadyExistsException;
import edu.kit.informatik.NoSuchEntityException;

/**
 * Contract for the literature system service
 * @author JoseNote
 * @version %I%, %G%
 */
public interface ILiteraturSystemService {    
    /**
     * Adds a new conference to the system.
     * @param seriesName the name of the conference series in which the new conference will be added
     * @param year the year value for the new entity
     * @param location the location value for the new entity
     * @return the new entity
     * @throws EntityAlreadyExistsException if there is already an entity in the system with the given values
     */
    Conference addConference(String seriesName, short year, String location);
    
    /**
     * Adds a new author to the system.
     * @param firstName the first name value for the new entity
     * @param lastName the last name value for the new entity
     * @return the new entity
     * @throws EntityAlreadyExistsException if there is already an entity in the system with the given values
     */
    Author addAuthor(String firstName, String lastName);
    
    /**
     * Gets a list of authors which names matches the given names.
     * @param names the author names to match
     * @return a list of the authors that match the given names
     */
    Collection<Author> getAuthor(Collection<AuthorNames> names);
    
    /**
     * Gets the author entity in the system that matches the given parameters.
     * @param firstName the first name value to match
     * @param lastName the last name value to match
     * @return the matching entity
     * @throws NoSuchEntityException if there is no matching entity in the system
     */
    Author getAuthor(String firstName, String lastName);
    
    /**
     * Checks for the existence of an author entity with the given parameters in the system
     * @param firstName the first name value to match
     * @param lastName the last name value to match
     * @return {@code True} if there is a matching entity. {@code False} otherwise
     */
    boolean existAuthor(String firstName, String lastName);
    
    /**
     * Adds a new journal to the system.
     * @param name the name value for the new entity
     * @param publisher the publisher value for the new entity
     * @return the new entity
     * @throws EntityAlreadyExistsException if there is already an entity in the system with the given values
     */
    Journal addJournal(String name, String publisher);
    
    /**
     * Checks for the existence of a journal entity with the given parameters in the system
     * @param name the name value to match against
     * @return {@code True} if there is a matching entity. {@code False} otherwise
     */
    boolean existJournal(String name);
    
    /**
     * Gets the journal entity in the system that matches the given parameters.
     * @param name the name value to match
     * @return the matching entity 
     * @throws NoSuchEntityException if there is no matching entity in the system
     */
    Journal getJournal(String name);
    
    /**
     * Adds a new conference series to the system.
     * @param name the name value for the new entity
     * @return the new entity
     * @throws EntityAlreadyExistsException if there is already an entity in the system with the given values
     */
    ConferenceSeries addConferenceSeries(String name);
    
    /**
     * Checks for the existence of a conference series entity with the given parameters in the system
     * @param name the name value to match
     * @return {@code True} if there is a matching entity. {@code False} otherwise
     */
    boolean existConferenceSeries(String name);
    
    /**
     * Gets the conference series entity in the system that matches the given parameters.
     * @param name the name value to match
     * @return the matching entity
     * @throws NoSuchEntityException if there is no matching entity in the system
     */
    ConferenceSeries getConferenceSeries(String name);
    
    /**
     * Adds new writers to the given publication.
     * @param publicationId the publication id to match
     * @param authors the author names to match
     * @throws NoSuchEntityException if there is no matching publication entity
     * or at least no match for one of the given names
     */
    void writtenBy(String publicationId, Collection<AuthorNames> authors);
    
    /**
     * Gets the publication entity in the system that matches the given parameters.
     * @param id the id value to match
     * @return the matching entity
     * @throws NoSuchEntityException if there is no matching entity in the system
     */
    Publication getPublication(String id);
    
    /**
     * Gets all the publication entities in the system
     * @return a collection of publication entities
     */
    Collection<Publication> getPublication();
    
    /**
     * Gets all the valid or invalid publication entities in the system
     * @param type either {@linkplain Publication#INVALID invalid} or {@linkplain Publication#VALID valid}
     * @return a collection of publication entities
     */
    Collection<Publication> getPublication(boolean type);
    
    /**
     * FIXME add doc
     * C9
     * @param authors FIXME add doc
     * @return FIXME add doc
     */
    Collection<Publication> getPublication(Collection<AuthorNames> authors);
    
    /**
     * Adds a quote relation between two publications in the system.
     * @param quoterPublicationId the id of the publication that makes the quote
     * @param referencePublicationId the id of the publication that is quoted
     * @throws InvalidRelationException if the referenced publication wasn't published before the quoter
     * or if the relation already existed
     * @throws NoSuchEntityException if either there is no matching entity for 
     * the quoter publication or the referenced publication
     */
    void cites(String quoterPublicationId, String referencePublicationId);
    
    /**
     * Adds a new article to a conference series in the system
     * @param seriesName the id of the conference series
     * @param articleId the id  of the new article
     * @param articlePublicationYear the publication year of the new article
     * @param articleTitle the title of the new article
     * @throws NoSuchEntityException if there is no matching {@linkplain ConferenceSeries} in the system
     * @throws EntityAlreadyExistsException if there is already an entity in the system with the given values
     */
    void addArticleToSeries(String seriesName, String articleId, short articlePublicationYear, String articleTitle);
    
    /**
     * Adds a new article to a journal in the system
     * @param journalName the name of the journal
     * @param articleId the id  of the new article
     * @param articlePublicationYear the publication year of the new article
     * @param articleTitle the title of the new article
     * @throws NoSuchEntityException if there is no matching journal in the system
     * @throws EntityAlreadyExistsException if there is already an entity in the system with the given values
     */
    void addArticleToJournal(
            String journalName, String articleId, 
            short articlePublicationYear, String articleTitle);
    
    /**
     * Checks for the existence of a publication entity with the given parameters in the system
     * @param id the id value to match
     * @return {@code True} if there is a matching entity. {@code False} otherwise
     */
    boolean existPublication(String id);
    
    /**
     * Adds a set of keywords to a publication entity in the system
     * @param publicationId the id of the publication entity to match
     * @param keywords the keywords to add
     * @throws NoSuchEntityException if there is no matching publication in the system
     */
    void addKeywordsToPublication(String publicationId, Collection<String> keywords);
    
    /**
     * Adds a set of keywords to a journal entity in the system
     * @param name the name of the journal to match
     * @param keywords the keywords to add
     * @throws NoSuchEntityException if there is no matching journal entity in the system
     */
    void addKeywordsToJournal(String name, Collection<String> keywords);
    
    /**
     * Adds a set of keywords to a conference series entity in the system
     * @param seriesName the name of the conference series to match
     * @param keywords the keywords to add
     * @throws NoSuchEntityException if there is no matching conference series entity in the system
     */
    void addKeywordsToConferenceSeries(String seriesName, Collection<String> keywords);

    /**
     * Adds a set of keywords to a conference entity in the system
     * @param seriesName the name of the conference to match
     * @param year the year of the conference to match
     * @param keywords the keywords to add
     * @throws NoSuchEntityException if there is no matching conference entity in the system
     */
    void addKeywordsToConference(String seriesName, short year, Collection<String> keywords);
    
    /**
     * Gets the publications published in the given conference series in the given year
     * @param seriesName the conference series name to match
     * @param year the year in the conference series to match
     * @return a collection of publications
     * @throws NoSuchEntityException if there is no matching conference series 
     * or no matching conference for the given conference series in the given year
     */
    Collection<Publication> inProceedings(String seriesName, short year);
    
    /**
     * Gets all the publications marked with the given set of keywords
     * @param keywords a set of keywords to match against
     * @return a collection of publications
     */
    Collection<Publication> findKeywords(Collection<String> keywords);
    
    /**
     * Calculates the similarity coefficient of two publications in the system
     * @param firstPublicationId the id of a publication
     * @param secondPublicationId the id of a publication
     * @return the similarity coefficient
     * @throws NoSuchEntityException if there is no matching publication entity for at least one of the given ids
     */
    float similarity(String firstPublicationId, String secondPublicationId);
    
    /**
     * Calculates the H-Index coefficient of an author in the system 
     * @param firstName the first name of the author to match
     * @param lastName the last name of the author to match
     * @return the h-index coefficient for the matched author
     * @throws NoSuchEntityException if there is no matching author entity
     */
    int hIndex(String firstName, String lastName);
    
    /**
     * Gets the coauthors of an author in the system
     * @param firstName the first name of the author to match
     * @param lastName the last name of the author to match
     * @return the coauthors of the matched author
     * @throws NoSuchEntityException if there is no matching author entity
     */
    Collection<Author> coAuthorsOf(String firstName, String lastName);
    
    /**
     * Gets the foreign citations of an author in the system
     * @param firstName the first name of the author to match
     * @param lastName the last name of the author to match
     * @return a string collection with the IDs of the publications which have 
     * foreign citations of the matched author
     * @throws NoSuchEntityException if there is no matching author entity
     */
    Collection<String> foreignCitationsOf(String firstName, String lastName);
    
    /**
     * Gets the bibliographies for a set of publications in the system
     * @param publicationIds a set of publication IDs
     * @return a collection of bibliographies for the matching publications
     * @throws NoSuchEntityException if there is no matching publication entity
     * for at least one of the given IDs
     */
    List<ArticleBibliography> getBibliography(Collection<String> publicationIds);
}
