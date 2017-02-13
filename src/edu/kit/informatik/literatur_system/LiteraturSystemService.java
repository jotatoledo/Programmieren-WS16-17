package edu.kit.informatik.literatur_system;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
            throw Utilities.alreadyExist(Author.class, firstName, lastName);
        Author entity = new Author(firstName, lastName);
        if (authors.putIfAbsent(entity, entity) != null)
            //TODO remove double check
            throw new IllegalArgumentException("error by addition");
        return entity;
    }

    @Override
    public boolean existAuthor(final String firstName, final String lastName) {
        //TODO validate fields
        return authors.containsKey(new Author(firstName, lastName));
    }

    @Override
    public Journal addJournal(final String name, final String publisher) {
        //TODO validate fields
        if (existJournal(name))
            //TODO improve error message
            throw Utilities.alreadyExist(Journal.class, name);
        Journal entity = new Journal(name, publisher);
        if (journals.putIfAbsent(entity, entity) != null)
            //TODO remove double check
            throw new IllegalArgumentException("error by addition");
        return entity;
    }

    @Override
    public boolean existJournal(final String name) {
        //TODO validate fields
        return journals.containsKey(new Journal(name));
    }
    
    @Override
    public Journal getJournal(final String name) {
        //TODO validate fields
        Journal entity = journals.get(new Journal(name));
        if (entity == null)
            throw Utilities.noSuch(Journal.class, name);
        return entity;
    }

    @Override
    public ConferenceSeries addConferenceSeries(final String name) {
        // TODO validate fields
        if (existConferenceSeries(name))
            //TODO improve error message
            throw Utilities.alreadyExist(ConferenceSeries.class, name);
        ConferenceSeries entity = new ConferenceSeries(name);
        if (conferenceSeries.putIfAbsent(entity, entity) != null)
            //TODO remove double check
            throw new IllegalArgumentException("error by addition");
        return entity;
    }

    @Override
    public boolean existConferenceSeries(final String name) {
        //TODO validate fields
        return conferenceSeries.containsKey(new ConferenceSeries(name));
    }
    
    @Override
    public ConferenceSeries getConferenceSeries(final String name) {
        //TODO validate fields
        ConferenceSeries entity = conferenceSeries.get(new ConferenceSeries(name));
        if (entity == null)
            throw Utilities.noSuch(ConferenceSeries.class, name);
        return entity;
    }
    
    @Override
    public void writtenBy(final String publicationId, final Collection<AuthorNames> authors) {
        // TODO validate fields
        // TODO implement
    }

    @Override
    public Collection<Author> getAuthor(final Collection<AuthorNames> names) {
        // TODO validate fields
        // TODO implement
        return null;
    }

    @Override
    public void cites(final String quoter, final String reference) {
        //TODO check input
        Publication pQuoter = getPublication(quoter);
        Publication pReference = getPublication(reference);
        if (pQuoter.getPublicationYear() <= pReference.getPublicationYear())
            throw new IllegalArgumentException("the referenced publication wasnt published before the one quoting it");
        pQuoter.addReferenceToOther(pReference);
        pReference.addReferenceToThis(pQuoter);
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
        //TODO validate fields
        Journal journal = getJournal(journalName);
        if (existPublication(articleId))
            throw Utilities.alreadyExist(Article.class, articleId);
        Article newArticle = new Article(articleId, articleTitle, articlePublicationYear, journal);
        publications.put(newArticle, newArticle);
        return newArticle;
    }

    @Override
    public boolean existPublication(final String id) {
        // TODO validate fields
        return publications.containsKey(new Article(id));
    }
    
    @Override
    public Collection<Publication> getPublication() {
        return Collections.unmodifiableCollection(publications.values());
    }
    
    @Override
    public Collection<Publication> getPublication(final boolean onlyValid) {
        if (Publication.VALID == onlyValid)
            return publications.values()
                    .stream()
                    .filter(p-> p.numberAuthors() > 0)
                    .collect(Collectors.toList());
        else 
            return publications.values()
                    .stream()
                    .filter(p-> p.numberAuthors() == 0)
                    .collect(Collectors.toList());
    }

    @Override
    public Collection<Publication> getPublication(final Collection<AuthorNames> authors) {
        // TODO validate fields
        // TODO implement
        return null;
    }
    
    @Override
    public Publication getPublication(final String id) {
        //TODO validate fields
        Publication entity = publications.get(new Article(id));
        if (entity == null)
            throw Utilities.noSuch(Publication.class, id);
        return entity;
    }

    @Override
    public Publication addKeywordsToPublication(
            final String publicationId, final Collection<String> keywords) {
        // TODO validate fields
        // TODO implement
        Publication pub = getPublication(publicationId);
        return pub;
    }

    @Override
    public ConferenceSeries addKeywordsToConferenceSeries(
            final String seriesName, final Collection<String> keywords) {
        // TODO validate fields
        // TODO implement
        ConferenceSeries serie = getConferenceSeries(seriesName);
        return serie;
    }

    @Override
    public Collection<Publication> inProceedings(final String seriesName, final short year) {
        //TODO validate fields
        ConferenceSeries series = getConferenceSeries(seriesName);
        Conference conf = series.getConferenceInYear(year);
        if (conf == null)
            throw Utilities.noSuch(Conference.class, year);
        return conf.getPublications();
    }

    @Override
    public float jaccard(final Collection<String> firstGroupWords, final Collection<String> secondGroupWords) {
        //TODO validate fields
        Collection<String> union = Utilities.unifyNoRepetition(firstGroupWords, secondGroupWords);
        if (union.size() == 0)
            return 1;
        Collection<String> intersection = Utilities.intersect(firstGroupWords, secondGroupWords);
        return ((float) intersection.size()) / union.size();
    }

    @Override
    public float similarity(final String firstPublicationId, final String secondPublicationId) {
        return jaccard(
                getPublication(firstPublicationId).getKeywordsValues(), 
                getPublication(secondPublicationId).getKeywordsValues());
    }
}
