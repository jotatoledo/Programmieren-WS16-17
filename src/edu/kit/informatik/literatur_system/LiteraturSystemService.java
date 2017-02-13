package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the literature system service
 * @author JoseNote
 * @version %I%, %G%
 */
public class LiteraturSystemService implements ILiteraturSystemService {
    private final Map<Author, Author> authors;
    private final Map<Publication, Publication> publications;
    private final Map<ConferenceSeries, ConferenceSeries> conferenceSeries;
    private final Map<Journal, Journal> journals;
    private final Map<Keyword, Keyword> keywords;
    
    /**
     * Creates a new instance of the service.
     * Note: good point to use dependency injection + repository pattern/ORM
     */
    public LiteraturSystemService() {
        authors = new HashMap<Author, Author>();
        publications = new HashMap<Publication, Publication>();
        conferenceSeries = new HashMap<ConferenceSeries, ConferenceSeries>();
        journals = new HashMap<Journal, Journal>();
        keywords = new HashMap<Keyword, Keyword>();
    }

    @Override
    public Author addAuthor(final String firstName, final String lastName) {
        //TODO validate fields
        if (existAuthor(firstName, lastName))
            throw new IllegalArgumentException("author already exist");
        Author entity = new Author(firstName, lastName);
        if (authors.putIfAbsent(entity, entity) != null)
            //TODO remove double check
            throw new IllegalArgumentException("error by addition");
        return entity;
    }

    @Override
    public boolean existAuthor(final String firstName, final String lastName) {
        return authors.containsKey(new Author(firstName, lastName));
    }

    @Override
    public Journal addJournal(final String name, final String publisher) {
        //TODO validate input
        if (existJournal(name))
            //TODO improve error message
            throw new IllegalArgumentException("journal already exist");
        Journal entity = new Journal(name, publisher);
        if (journals.putIfAbsent(entity, entity) != null)
            //TODO remove double check
            throw new IllegalArgumentException("error by addition");
        return entity;
    }

    @Override
    public boolean existJournal(final String name) {
        //TODO validate input
        return journals.containsKey(new Journal(name));
    }
    
    @Override
    public Journal getJournal(final String name) {
        Journal entity = journals.get(new Journal(name));
        if (entity == null)
            throw Utilities.noSuch(Journal.class, name);
        return entity;
    }

    @Override
    public ConferenceSeries addConferenceSeries(final String name) {
        // TODO validate input
        if (existConferenceSeries(name))
            //TODO improve error message
            throw new IllegalArgumentException("conference series already exist");
        ConferenceSeries entity = new ConferenceSeries(name);
        if (conferenceSeries.putIfAbsent(entity, entity) != null)
            //TODO remove double check
            throw new IllegalArgumentException("error by addition");
        return entity;
    }

    @Override
    public boolean existConferenceSeries(final String name) {
        //TODO validate input
        return conferenceSeries.containsKey(new ConferenceSeries(name));
    }
    
    @Override
    public ConferenceSeries getConferenceSeries(final String name) {
        //TODO validate input
        ConferenceSeries entity = conferenceSeries.get(new ConferenceSeries(name));
        if (entity == null)
            throw Utilities.noSuch(ConferenceSeries.class, name);
        return entity;
    }
    
    @Override
    public void writtenBy(final String publicationId, final Collection<AuthorNames> authors) {
        // TODO Auto-generated method stub
    }

    @Override
    public Publication getPublication(final String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Author> getAuthor(Collection<AuthorNames> names) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void cites(final String quoter, final String reference) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Conference addConference(
            final String seriesName, final short year, final String location) {
        // TODO check input
        ConferenceSeries series = getConferenceSeries(seriesName);
        if (series.getConferenceInYear(year) != null)
            //TODO improve message
            throw Utilities.alreadyExist(Conference.class, year);
        Conference newConference = new Conference(location, year, series);
        return newConference;
    }
    
    @Override
    public Article addArticleToSeries(
            final String seriesName, final String articleId, 
            final short articlePublicationYear, final String articleTitle) {
        //TODO check input
        ConferenceSeries series = getConferenceSeries(seriesName);
        if (existPublication(articleId))
            throw Utilities.alreadyExist(Article.class, articleId);
        Conference conference = series.getConferenceInYear(articlePublicationYear);
        if (conference == null)
            throw Utilities.noSuch(Conference.class, articlePublicationYear);
        Article newArticle = new Article(articleId, articleTitle, articlePublicationYear, conference);
        publications.put(newArticle, newArticle);
        return newArticle;
    }

    @Override
    public Article addArticleToJournal(
            final String journalName, final String articleId, 
            final short articlePublicationYear, final String articleTitle) {
        //TODO check input
        Journal journal = getJournal(journalName);
        if (existPublication(articleId))
            throw Utilities.alreadyExist(Article.class, articleId);
        Article newArticle = new Article(articleId, articleTitle, articlePublicationYear, journal);
        publications.put(newArticle, newArticle);
        return newArticle;
    }

    @Override
    public boolean existPublication(final String id) {
        return publications.containsKey(new Article(id));
    }
    
    @Override
    public Collection<Publication> getPublication(boolean onlyValid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Publication> getPublication(Collection<AuthorNames> authors) {
        // TODO Auto-generated method stub
        return null;
    }
}
