package edu.kit.informatik.literatur_system;

import static java.util.regex.Pattern.compile;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.Utilities;

/**
 * FIXME add doc
 * @see http://www.ocpsoft.org/tutorials/regular-expressions/java-visual-regex-tester/
 * @author JoseNote
 * @version %I%, %G%
 */
public enum Command implements ICommand<ILiteraturSystemService> {
    // FIXME implement direct prints
    // FIXME check if all functions are created
    // FIXME check whitespaces and shit on the regular expressions
    /**
     * Implementation of the {@code add author} command as described in the task.
     */
    //add author <first name>,<last name>
    ADD_AUTHOR("add author ([a-zA-Z]+),([a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.addAuthor(
                    input.substring(m.start(1), m.end(1)), 
                    input.substring(m.start(2), m.end(2)));
        }
    },
    /**
     * Implementation of the {@code add journal} command as described in the task.
     */
    //add journal <name>,<publisher>
    ADD_JOURNAL("add journal ([a-zA-Z]+),([a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.addJournal(
                    input.substring(m.start(1), m.end(1)), 
                    input.substring(m.start(2), m.end(2)));
            
        }
    },
    /**
     * Implementation of the {@code add conference series} command as described in the task.
     */
    //add conference series <name>
    ADD_CONFERENCE_SERIES("add conference series ([a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.addConferenceSeries(input.substring(m.start(1), m.end(1)));
        }
    },
    /**
     * Implementation of the {@code add conference} command as described in the task.
     */
    //add conference <series>,<year>,<location>
    ADD_CONFERENCE("add conference ([a-zA-Z]+),((?!0)\\d{4}),([a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.addConference(
                    input.substring(m.start(1), m.end(1)),
                    Short.parseShort(input.substring(m.start(2), m.end(2))),
                    input.substring(m.start(3), m.end(3)));
            
        }
    },
    /**
     * Implementation of the {@code add article to series} command as described in the task.
     */
    //add article to series <name>:<id>,<year>,<title>
    ADD_ARTICLE_TO_SERIES("add article to series ([a-zA-Z]+):([a-z0-9]+),((?!0)\\d{4}),([a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.addArticleToSeries(
                    input.substring(m.start(1), m.end(1)), 
                    input.substring(m.start(2), m.end(2)), 
                    Short.parseShort(input.substring(m.start(3), m.end(3))), 
                    input.substring(m.start(4), m.end(4)));
        }
    },
    /**
     * Implementation of the {@code add article to journal} command as described in the task.
     */
    //add article to journal <name>:<id>,<year>,<title>
    ADD_ARTICLE_TO_JOURNAL("add article to journal ([a-zA-Z]+):([a-z0-9]+),((?!0)\\d{4}),([a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.addArticleToJournal(
                    input.substring(m.start(1), m.end(1)), 
                    input.substring(m.start(2), m.end(2)), 
                    Short.parseShort(input.substring(m.start(3), m.end(3))), 
                    input.substring(m.start(4), m.end(4)));
        }
    },
    /**
     * Implementation of the {@code add keywords to journal} command as described in the task.
     */
    //add keywords to journal <name>:<list of keywords>
    ADD_KEYWORDS_TO_JOURNAL("add keywords to journal ([a-zA-Z]+):([a-z]+)(;[a-z]+)*", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.addKeywordsToJournal(
                    input.substring(m.start(1), m.end(1)), 
                    listKeywords(input, ";", m.start(2), m.end(3)));
        }
    },
    /**
     * Implementation of the {@code add keywords to conference} command as described in the task.
     */
    //add keywords to conference <name>,<year>:<list of keywords>
    ADD_KEYWORDS_TO_CONFERENCE("add keywords to conference ([a-zA-Z]+),((?!0)\\d{4}):([a-z]+)(;[a-z]+)*", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.addKeywordsToConference(
                    input.substring(m.start(1), m.end(1)), 
                    Short.parseShort(input.substring(m.start(2), m.end(2))), 
                    listKeywords(input, ";", m.start(3), m.end(4)));
            
        }
    },
    /**
     * Implementation of the {@code add keywords to series} command as described in the task.
     */
    //add keywords to series <name>:<list of keywords>
    ADD_KEYWORDS_TO_CONFERNCE_SERIES("add keywords to series ([a-zA-Z]+):([a-z]+)(;[a-z]+)*", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.addKeywordsToConferenceSeries(
                    input.substring(m.start(1), m.end(1)), 
                    listKeywords(input, ";", m.start(2), m.end(3)));
            
        }
    },
    /**
     * Implementation of the {@code add keywords to publication} command as described in the task.
     */
    //add keywords to pub <id>:<list of keywords>
    ADD_KEYWORDS_TO_PUBLICATION("add keywords to pub ([a-z0-9]+):([a-z]+)(;[a-z]+)*", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.addKeywordsToPublication(
                    input.substring(m.start(1), m.end(1)), 
                    listKeywords(input, ";", m.start(2), m.end(3)));
        }
    },
    /**
     * Implementation of the {@code written by} command as described in the task C6.
     */
    //written-by <publication>,<list of author names>
    WRITTEN_BY("written-by ([a-z0-9]+),([a-zA-Z]+ [a-zA-Z]+)(;[a-zA-Z]+ [a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.writtenBy(
                    input.substring(m.start(1), m.end(1)), 
                    listAuthorNames(input, ";", m.start(2), m.end(3)));
        }
    },
    /**
     * Implementation of the {@code cites} command as described in the task C7.
     */
    //cites <publication 1>,<publication 2>
    CITES("cites ([a-z0-9]+),([a-z0-9]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            service.cites(
                    input.substring(m.start(1), m.end(1)), 
                    input.substring(m.start(2), m.end(2)));
        }
    },
    /**
     * Implementation of the {@code list publications} command as described in the task C9.
     */
    //all publications
    ALL_PUBLICATIONS("all publications", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            Collection<Publication> result = service.getPublication();
            result.stream()
                .forEach(x -> Terminal.printLine(x.getId()));
        }
    },
    /**
     * Implementation of the {@code list invalid publications} command as described in the task C10.
     */
    //list invalid publications
    LIST_INVALID_PUBLICATIONS("list invalid publications", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
             Collection<Publication> result = service.getPublication(Publication.INVALID);
             result.stream()
                 .forEach(x -> Terminal.printLine(x.getId()));
        }
    },
    /**
     * Implementation of the {@code publications by} command as described in the task C11.
     */
    //publications by <list of authors>
    PUBLICATIONS_BY("publications by ([a-zA-Z]+ [a-zA-Z]+)(;[a-zA-Z]+ [a-zA-Z]+)", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            Collection<Publication> result = service.getPublication(
                    listAuthorNames(input, ";", m.start(1), m.end(2)));
            result.stream()
                .forEach(x -> Terminal.printLine(x.getId()));
        }
    },
    /**
     * Implementation of the {@code in proceedings} command as described in the task C12.
     */
    //in proceedings <series>,<year>
    IN_PROCEEDINGS("in proceedings ([a-zA-Z]+),((?!0)\\d{4})", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            Collection<Publication> result = service.inProceedings(
                    input.substring(m.start(1), m.end(1)), 
                    Short.parseShort(input.substring(m.start(2), m.end(2))));
            result.forEach(x -> Terminal.printLine(x.getId()));
                
        }
    },
    /**
     * Implementation of the {@code find keywords} command as described in the task C13.
     */
    //find keywords <list of keywords>
    FIND_KEYWORDS("find keywords ([a-z]+)(;[a-z]+)*", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            //FIXME implement
        }
    },
    /**
     * Implementation of the {@code jaccard} command as described in the task C14.
     */
    //jaccard <list of words 1> <list of words 2>
    JACCARD("jaccard ([a-z]+)(;[a-z]+)* ([a-z]+)(;[a-z]+)*", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            float result = service.jaccard(
                    listKeywords(input, ";", m.start(1), m.end(2)), 
                    listKeywords(input, ";", m.start(3), m.end(4)));
            Terminal.printLine(String.format(Locale.ROOT, "%.3f", Utilities.roundDown3(result)));
        }
    },
    /**
     * Implementation of the {@code similarity} command as described in the task C15.
     */
    //similarity <publication 1>,<publication 2>
    SIMILARITY("similarity ([a-z0-9]+),([a-z0-9]+)", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            float result = service.similarity(
                    input.substring(m.start(1), m.end(1)), 
                    input.substring(m.start(2), m.end(2)));
            Terminal.printLine(String.format(Locale.ROOT, "%.3f", Utilities.roundDown3(result)));
        }
    },
    /**
     * Implementation of the {@code direct h-index} command as described in the task C16.
     */
    //direct h-index <list of citation counts>
    //FIXME check regex ("[1-9]\\d*")
    DIRECT_H_INDEX("direct h-index ((?!0)\\d+);((?!0)\\d+)", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            //FIXME implement
        }
    },
    /**
     * Implementation of the {@code h-index} command as described in the task C17.
     */
    //h-index <first name> <last name>
    H_INDEX("h-index ([a-zA-Z]+) ([a-zA-Z]+)", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            //FIXME implement
        }
    },
    /**
     * Implementation of the {@code coauthors of} command as described in the task C18.
     */
    //coauthors of <firstname> <lastname>
    COAUTHORS_OF("coauthors of ([a-zA-Z]+) ([a-zA-Z]+)", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            //FIXME implement
        }
    },
    /**
     * Implementation of the {@code foreign citations of} command as described in the task C19.
     */
    //foreign citations of <firstname> <lastname>
    FOREIGN_CITATIONS_OF("foreign citations of ([a-zA-Z]+) ([a-zA-Z]+)", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            Collection<String> result = service.foreignCitationsOf(
                    input.substring(m.start(1), m.end(1)), 
                    input.substring(m.start(2), m.end(2)));
            result.forEach(x->Terminal.printLine(x));
            //FIXME implement
        }
    },
    /**
     * Implementation of the {@code direct print conference} command as described in the task C20.
     */
    // direct print conference <style>:
    // <author 1>,<author 2>,<author 3>,
    // <title>,<conference series name>,
    // <location>,<year>
    DIRECT_PRINT_CONFERENCE(
            "direct print conference (ieee|chicago):"
            + "([a-zA-Z]+ [a-zA-Z]+),([a-zA-Z]+ [a-zA-Z]+)?,([a-zA-Z]+ [a-zA-Z]+)?"
            + "([a-zA-Z\\s]+),([a-zA-Z]+)"
            + "([a-zA-Z\\s]+),((?!0)\\d{4})", 
            false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            //FIXME implement
        }
    },
    /**
     * Implementation of the {@code direct print journal} command as described in the task C21.
     */
    // direct print conference <style>:
    // <author 1>,<author 2>,<author 3>,
    // <title>,<journal title>,<year>
    DIRECT_PRINT_JOURNAL(
            "direct print journal (ieee|chicago):"
            + "([a-zA-Z]+ [a-zA-Z]+),([a-zA-Z]+ [a-zA-Z]+)?,([a-zA-Z]+ [a-zA-Z]+)?"
            + "([a-zA-Z\\s]+),([a-zA-Z]+),((?!0)\\d{4})",
            false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            //FIXME implement
        }
    },
    /**
     * Implementation of the {@code print bibliography} command as described in the task C22.
     */
    //print bibliography <style>:<list publication ids>
    PRINT_BIBLIOGRAPHY("print bibliography (ieee|chicago):([a-z0-9]+)(;[a-z0-9]+)*", false) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) {
            final Matcher m = Utilities.matcher(pattern(), input);
            //FIXME implement
        }
    },
    /**
     * Implementation of the {@code quit} command as described in the task C0.
     */
    //quit
    QUIT("quit", true) {
        @Override
        public void execute(final ILiteraturSystemService service, final String input) { }
    };
    private final Pattern pattern;
    private final boolean okMessage;
    
    private Command(
            final String format, final boolean okMessaage) {
        pattern = compile(format);
        this.okMessage = okMessaage;
    }
    
    @Override
    public boolean isQuit() {
        return this == QUIT;
    }
    
    @Override
    public boolean okMessage() {
        return okMessage;
    }

    /**
     * FIXME add doc
     * @return FIXME add doc
     */
    public static CommandHandler<ILiteraturSystemService, ICommand<ILiteraturSystemService>> handler() {
        return CommandHandler.createFor(new LiteraturSystemService(), values());
    }

    @Override
    public Pattern pattern() {
        return pattern;
    }
    
    /**
     * FIXME add doc
     * @param input FIXME add doc
     * @param delimiter FIXME add doc
     * @param start FIXME add doc
     * @param end FIXME add doc
     * @return FIXME add doc
     */
    private static List<AuthorNames> listAuthorNames(
            final String input, final String delimiter,
            final int start, final int end) {
        return Arrays.stream(input.substring(start, end).split(delimiter))
                .map(x -> x.split(" "))
                .map(x -> new AuthorNames(x[0], x[1]))
                .collect(Collectors.toList());
    }
    
    /**
     * FIXME add doc
     * @param input FIXME add doc
     * @param delimiter FIXME add doc
     * @param start FIXME add doc
     * @param end FIXME add doc
     * @return FIXME add doc
     */
    private static Collection<String> listKeywords(
            final String input, final String delimiter, 
            final int start, final int end) {
        return Arrays.asList(input.substring(start, end).split(delimiter));
    }
}
