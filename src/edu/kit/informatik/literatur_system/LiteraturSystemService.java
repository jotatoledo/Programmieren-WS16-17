package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    
    /**
     * TODO add doc
     * @param type TODO add doc
     * @param arg TODO add doc
     * @param args TODO add doc
     * @return TODO add doc
     */
    private IllegalArgumentException noSuch(
            final Class<?> type,
            final Object arg,
            final Object... args) {
        ////
        return new IllegalArgumentException(Stream.concat(Stream.of(arg), Stream.of(args)).map(String::valueOf)
                .collect(Collectors.joining(", ", "No such " + type.getSimpleName() + ": ", "")));
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
            noSuch(Journal.class, name);
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
            final String conferenceSeriesId, final short year, final String location) {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public Article addArticleToSeries(
            final String seriesId, final String articleId, 
            final short articlePublicationYear, final String articleTitle) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Article addArticleToJournal(
            final String journalId, final String articleId, 
            final short articlePublicationYear, final String articleTitle) {
        // TODO Auto-generated method stub
        return null;
    }
}
