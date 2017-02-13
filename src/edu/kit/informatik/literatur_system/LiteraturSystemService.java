package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of the literature system service
 * @author JoseNote
 * @version %I%, %G%
 */
public class LiteraturSystemService implements ILiteraturSystemService {
    private final Set<Author> authors;
    private final Set<Publication> publications;
    private final Set<ConferenceSeries> conferenceSeries;
    private final Set<Journal> journals;
    private final Set<Keyword> keywords;
    
    /**
     * Creates a new instance of the service.
     * Note: good point to use dependency injection
     */
    public LiteraturSystemService() {
        authors = new HashSet<Author>();
        publications = new HashSet<Publication>();
        conferenceSeries = new HashSet<ConferenceSeries>();
        journals = new HashSet<Journal>();
        keywords = new TreeSet<Keyword>();
    }
    
//    private IllegalArgumentException noSuch(
//            final Class<?> type,
//            final Object arg,
//            final Object... args) {
//        ////
//        return new IllegalArgumentException(Stream.concat(Stream.of(arg), Stream.of(args)).map(String::valueOf)
//                .collect(Collectors.joining(", ", "No such " + type.getSimpleName() + ": ", "")));
//    }

    @Override
    public Author addAuthor(final String firstName, final String lastName) {
        //TODO validate fields
        if (existAuthor(firstName, lastName))
            throw new IllegalArgumentException("author already exist");
        Author entity = new Author(firstName, lastName);
        if (!authors.add(entity))
            throw new IllegalArgumentException("error by addition");
        return entity;
    }

    @Override
    public boolean existAuthor(final String firstName, final String lastName) {
        return authors.contains(new Author(firstName, lastName));
    }

    @Override
    public Journal addJournal(final String name, final String publisher) {
        //TODO validate input
        if (existJournal(name))
            throw new IllegalArgumentException("journal already exist");
        Journal entity = new Journal(name, publisher);
        //TODO add to collection
        return entity;
    }

    @Override
    public boolean existJournal(final String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ConferenceSeries addConferenceSeries(final String name) {
        // TODO Auto-generated method stub
        return null;
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
