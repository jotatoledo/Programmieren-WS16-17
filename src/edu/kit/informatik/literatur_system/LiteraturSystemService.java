package edu.kit.informatik.literatur_system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import edu.kit.informatik.InvalidRelationException;
import edu.kit.informatik.Utilities;

/**
 * Implementation of the literature system service
 * @author JoseNote
 * @version %I%, %G%
 */
public class LiteraturSystemService implements ILiteraturSystemService {
    // FIXME rework get methods?
    private final Map<Author, Author> authors;
    private final Map<Publication, Publication> publications;
    private final Map<ConferenceSeries, ConferenceSeries> conferenceSeries;
    private final Map<Journal, Journal> journals;
    
    /**
     * Creates a new instance of the service.
     * Note: good point to use dependency injection + repository pattern/ORM
     */
    public LiteraturSystemService() {
        authors = new HashMap<Author, Author>();
        publications = new HashMap<Publication, Publication>();
        conferenceSeries = new HashMap<ConferenceSeries, ConferenceSeries>();
        journals = new HashMap<Journal, Journal>();
    }

    @Override
    public void addAuthor(final String firstName, final String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        final Author entity = new Author(firstName, lastName);
        if (authors.put(entity, entity) != null)
            throw Utilities.alreadyExist(Author.class, firstName, lastName);
    }
    
    @Override
    public Author getAuthor(final String firstName, final String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        final Author entity = authors.get(new Author(firstName, lastName));
        if (entity == null)
            throw Utilities.noSuch(Author.class, firstName, lastName);
        return entity;
    }
    
    @Override
    public Collection<Author> getAuthor(final Collection<AuthorNames> names) {
        Objects.requireNonNull(names);
        final Collection<Author> authorEntities = new ArrayList<Author>();
        names.forEach(x -> authorEntities.add(getAuthor(x.getFirstName(), x.getLastName())));
        return authorEntities;
    }

    @Override
    public boolean existAuthor(final String firstName, final String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        return authors.containsKey(new Author(firstName, lastName));
    }

    @Override
    public void addJournal(final String name, final String publisher) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(publisher);
        final Journal entity = new Journal(name, publisher);
        if (journals.putIfAbsent(entity, entity) != null)
            throw Utilities.alreadyExist(Journal.class, name);
    }

    @Override
    public boolean existJournal(final String name) {
        Objects.requireNonNull(name);
        return journals.containsKey(new Journal(name));
    }
    
    @Override
    public Journal getJournal(final String name) {
        Objects.requireNonNull(name);
        final Journal entity = journals.get(new Journal(name));
        if (entity == null)
            throw Utilities.noSuch(Journal.class, name);
        return entity;
    }

    @Override
    public void addConferenceSeries(final String name) {
        Objects.requireNonNull(name);
        final ConferenceSeries entity = new ConferenceSeries(name);
        if (conferenceSeries.putIfAbsent(entity, entity) != null)
            throw Utilities.alreadyExist(ConferenceSeries.class, name);
    }

    @Override
    public boolean existConferenceSeries(final String name) {
        Objects.requireNonNull(name);
        return conferenceSeries.containsKey(new ConferenceSeries(name));
    }
    
    @Override
    public ConferenceSeries getConferenceSeries(final String name) {
        Objects.requireNonNull(name);
        final ConferenceSeries entity = conferenceSeries.get(new ConferenceSeries(name));
        if (entity == null)
            throw Utilities.noSuch(ConferenceSeries.class, name);
        return entity;
    }
    
    @Override
    public void writtenBy(final String publicationId, final Collection<AuthorNames> authors) {
        Objects.requireNonNull(publicationId);
        Objects.requireNonNull(authors);
        
        final Publication publication = getPublication(publicationId);
        // Filter repeated names
        final Collection<AuthorNames> filteredNames = filterRepeated(authors);
        // Gets the associated authors. Note: Exception in no-match for any of the names
        final Collection<Author> authorEntities = getAuthor(filteredNames);
        // Checks existence of relation author-publication
        authorEntities.forEach(a -> {
            if (publication.hasAuthor(a))
                // FIXME re factor into Utilities.invalidRelation();
                // FIXME improve error message
                throw new InvalidRelationException("the publication has already a relation to an author");
        });
        authorEntities.forEach(a -> {
            publication.addAuthor(a);
            a.addPublication(publication);
        });
    }

    @Override
    public void cites(final String quoterPublicationId, final String referencePublicationId) {
        Objects.requireNonNull(quoterPublicationId);
        Objects.requireNonNull(referencePublicationId);
        final Publication pQuoter = getPublication(quoterPublicationId);
        final Publication pReference = getPublication(referencePublicationId);
        if (pQuoter.getPublicationYear() <= pReference.getPublicationYear())
            // FIXME re factor into Utilities.invalidRelation();
            throw new InvalidRelationException("the referenced publication wasnt published before the one quoting it");
        pQuoter.addReferenceToOther(pReference);
        pReference.addReferenceToThis(pQuoter);
    }

    @Override
    public void addConference(
            final String seriesName, final short year, final String location) {
        Objects.requireNonNull(seriesName);
        Objects.requireNonNull(location);
        Objects.requireNonNull(year);
        final ConferenceSeries series = getConferenceSeries(seriesName);
        // FIXME consistent get -> throw inside function
        if (series.getConferenceInYear(year) != null)
            throw Utilities.alreadyExist(Conference.class, seriesName, year);
        series.addConference(new Conference(location, year, series));
    }
    
    @Override
    public void addArticleToSeries(
            final String seriesName, final String articleId, 
            final short articlePublicationYear, final String articleTitle) {
        Objects.requireNonNull(seriesName);
        Objects.requireNonNull(articleId);
        Objects.requireNonNull(articlePublicationYear);
        Objects.requireNonNull(articleTitle);
        final ConferenceSeries series = getConferenceSeries(seriesName);
        if (existPublication(articleId))
            throw Utilities.alreadyExist(Article.class, articleId);
        final Conference conference = series.getConferenceInYear(articlePublicationYear);
        // FIXME consistent get -> throw inside function
        if (conference == null)
            throw Utilities.noSuch(Conference.class, articlePublicationYear);
        final Article newArticle = new Article(articleId, articleTitle, articlePublicationYear, conference);
        publications.put(newArticle, newArticle);
    }

    @Override
    public void addArticleToJournal(
            final String journalName, final String articleId, 
            final short articlePublicationYear, final String articleTitle) {
        Objects.requireNonNull(journalName);
        Objects.requireNonNull(articleId);
        Objects.requireNonNull(articlePublicationYear);
        Objects.requireNonNull(articleTitle);
        final Journal journal = getJournal(journalName);
        if (existPublication(articleId))
            throw Utilities.alreadyExist(Article.class, articleId);
        final Article newArticle = new Article(articleId, articleTitle, articlePublicationYear, journal);
        publications.put(newArticle, newArticle);
    }

    @Override
    public boolean existPublication(final String id) {
        Objects.requireNonNull(id);
        return publications.containsKey(new Article(id));
    }
    
    @Override
    public Collection<Publication> getPublication() {
        return Collections.unmodifiableCollection(publications.values());
    }
    
    @Override
    public Collection<Publication> getPublication(final boolean type) {
        if (Publication.VALID == type)
            return publications.values()
                    .stream()
                    .filter(p-> p.isValid() == true)
                    .collect(Collectors.toList());
        else 
            return publications.values()
                    .stream()
                    .filter(p-> p.isValid() == false)
                    .collect(Collectors.toList());
    }

    @Override
    public Collection<Publication> getPublication(final Collection<AuthorNames> authors) {
        Objects.requireNonNull(authors);
        // Gets the author associated author entities. Note: Exception for any no match case
        final Collection<Author> authorEntities = getAuthor(authors);
        return authorEntities.stream()
                .map(a -> a.getPublications().values())
                .flatMap(p -> p.stream())
                .distinct()
                .collect(Collectors.toList());
    }
    
    @Override
    public Collection<Publication> getPublicationsById(Collection<String> ids) {
        Objects.requireNonNull(ids);
        final Collection<Publication> publicationEntities = new ArrayList<Publication>();
        ids.forEach(x -> publicationEntities.add(getPublication(x)));
        return publicationEntities;
    }
    
    @Override
    public Publication getPublication(final String id) {
        Objects.requireNonNull(id);
        final Publication entity = publications.get(new Article(id));
        if (entity == null)
            throw Utilities.noSuch(Publication.class, id);
        return entity;
    }
    
    @Override
    public Collection<Publication> inProceedings(final String seriesName, final short year) {
        Objects.requireNonNull(seriesName);
        Objects.requireNonNull(year);
        final ConferenceSeries series = getConferenceSeries(seriesName);
        final Conference conf = series.getConferenceInYear(year);
        if (conf == null)
            throw Utilities.noSuch(Conference.class, year);
        return conf.getPublications();
    }

    @Override
    public float similarity(final String firstPublicationId, final String secondPublicationId) {
        Objects.requireNonNull(firstPublicationId);
        Objects.requireNonNull(secondPublicationId);
        return Utilities.jaccard(
                getPublication(firstPublicationId).getKeywords(), 
                getPublication(secondPublicationId).getKeywords());
    }     
    
    @Override
    public int hIndex(final String firstName, final String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        final Author author = getAuthor(firstName, lastName);
        final Collection<Publication> authorPublications = author.getPublications().values();
        return Utilities.directHIndex(authorPublications.stream()
                .map(p->p.numberReferencesToThis())
                .collect(Collectors.toList()));
    }

    @Override
    public Collection<Author> coAuthorsOf(final String firstName, final String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);        
        final Author author = getAuthor(firstName, lastName);
        final Collection<Publication> authorPublications = author.getPublications().values();
        return authorPublications.stream()
                .map(p -> p.getAuthors().values())
                .flatMap(as -> as.stream())
                .distinct()
                .filter(a -> !a.equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<String> foreignCitationsOf(final String firstName, final String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        final Author author = getAuthor(firstName, lastName);
        return author.getForeignPublications().stream()
                .map(Publication::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleBibliography> getBibliography(final Collection<String> publicationIds) {
        Objects.requireNonNull(publicationIds);
        // Filter repeated IDs
        final Collection<String> filteredIds = filterRepeated(publicationIds);
        // Gets the associated publication entities. Note: exception in first no match case
        final Collection<Publication> publications = getPublicationsById(filteredIds);
        // Test that all the publications are valid
        publications.forEach(p -> {
            if (!p.isValid())
                // FIXME improve message
                throw new IllegalArgumentException("the publication " + p.getId() + " isnt valid");
        });
        return publications.stream()
                .map(x-> x.toBibliography())
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void addKeywordsToPublication(
            final String publicationId, Collection<String> keywords) {
        Objects.requireNonNull(publicationId);
        Objects.requireNonNull(keywords);
        final Publication publication = getPublication(publicationId);
        publication.addKeyword(filterRepeated(keywords));
    }
    
    @Override
    public void addKeywordsToConferenceSeries(
            final String seriesName, Collection<String> keywords) {
        Objects.requireNonNull(seriesName);
        Objects.requireNonNull(keywords);
        final ConferenceSeries serie = getConferenceSeries(seriesName);
        serie.addKeyword(filterRepeated(keywords));
    }
    
    @Override
    public void addKeywordsToJournal(final String name, final Collection<String> keywords) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(keywords);
        final Journal journal = getJournal(name);
        journal.addKeyword(filterRepeated(keywords));
    }

    @Override
    public void addKeywordsToConference(final String seriesName, final short year, final Collection<String> keywords) {
        Objects.requireNonNull(seriesName);
        Objects.requireNonNull(year);
        Objects.requireNonNull(keywords);
        final Conference conference = getConferenceSeries(seriesName).getConferenceInYear(year);
        // FIXME consistent get -> throw inside function
        if (conference == null)
            throw Utilities.noSuch(Conference.class, seriesName, year);
        conference.addKeyword(filterRepeated(keywords));
    }

    @Override
    public Collection<Publication> findKeywords(final Collection<String> keywords) {
        Objects.requireNonNull(keywords);
        final Collection<String> filtered = filterRepeated(keywords);
        return publications.values().stream()
                .filter(pub -> pub.isTagedWith(filtered, ITagged.FULL_MATCH))
                .collect(Collectors.toList());
    }
    
    private <T> Collection<T> filterRepeated(final Collection<T> elements) {
        return Utilities.filterRepeated(elements);
    }
}
