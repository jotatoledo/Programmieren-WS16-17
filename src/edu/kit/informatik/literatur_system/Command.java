package edu.kit.informatik.literatur_system;

import static java.util.regex.Pattern.compile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FIXME add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public enum Command implements ICommand<ILiteraturSystemService> {
    //FIXME implement direct prints
    //FIXME check if all functions are created
    /**
     * Implementation of the {@code add author} command as described in the task.
     */
    //add author <first name>,<last name>
    ADD_AUTHOR("add author ([a-zA-Z]+),([a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String input) {
            final Matcher m = matcher(pattern(), input);
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
        public void execute(final ILiteraturSystemService service, 
                            final String input) {
            final Matcher m = matcher(pattern(), input);
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
        public void execute(final ILiteraturSystemService service, 
                            final String input) {
            final Matcher m = matcher(pattern(), input);
            service.addConferenceSeries(input.substring(m.start(1), m.end(1)));
        }
    },
    /**
     * Implementation of the {@code add conference} command as described in the task.
     */
    //add conference <series>,<year>,<location>
    ADD_CONFERENCE("add conference ([a-zA-Z]+),((?!0)\\d{4}),([a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String input) {
            final Matcher m = matcher(pattern(), input);
            service.addConference(
                    input.substring(m.start(1), m.end(1)),
                    Short.parseShort(input.substring(m.start(2), m.end(2))),
                    input.substring(m.start(3), m.end(3)));
            
        }
    },
    /**
     * Implementation of the {@code add article to} command as described in the task.
     */
    //add article to <series/journal>:<id>,<year>,<title>
    ADD_ARTICLE_TO("add article to (series|journal) ([a-zA-Z]+):([a-z0-9]+),((?!0)\\d{4}),([a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String input) {
            final Matcher m = matcher(pattern(), input);
            final String option = input.substring(m.start(1), m.end(1));
            //FIXME refactor
            if (option.equals("series"))
                service.addArticleToSeries(
                        input.substring(m.start(2), m.end(2)), 
                        input.substring(m.start(3), m.end(3)), 
                        Short.parseShort(input.substring(m.start(4), m.end(4))), 
                        input.substring(m.start(5), m.end(5)));
            else if (option.equals("journal"))
                service.addArticleToJournal(
                        input.substring(m.start(2), m.end(2)), 
                        input.substring(m.start(3), m.end(3)), 
                        Short.parseShort(input.substring(m.start(4), m.end(4))), 
                        input.substring(m.start(5), m.end(5)));
        }
    },
    /**
     * TODO add doc complete
     * written-by <publication>,<list of author names>
     */
    WRITTEN_BY("written-by ([a-zA-Z]+),()((;())*)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc
     * cites <publication 1>,<publication 2>
     */
    CITES("cites ([a-zA-Z]+),([a-zA-Z]+)", true) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc. List expression
     * add keywords to <entity>:<list of keywords>
     */
    ADD_KEYWORDS_TO("add keywords to ([a-zA-Z]+)()", true) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc
     * all publications
     */
    ALL_PUBLICATIONS("all publications", false) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc
     * list invalid publications
     */
    LIST_INVALID_PUBLICATIONS("list invalid publications", false) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc. List expression
     * publications by <list of authors>
     */
    PUBLICATIONS_BY("publications by ()", false) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc
     * in proceedings <series>,<year>
     */
    IN_PROCEEDINGS("in proceedings ([a-zA-Z]+),((?!0)\\d{4})", false) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc
     */
    QUIT("quit", true) {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
        }
    };
    
    private final String stringrep;
    private final Pattern pattern;
    private final boolean okMessage;
    
    
    private Command(
            final String format, final boolean okMessaage) {
        stringrep = format;
        pattern = compile(format);
        this.okMessage = okMessaage;
    }
    
    @Override
    public boolean isQuit() {
        return this == QUIT;
    }
    
    @Override
    public boolean printOkMessage() {
        return okMessage;
    }

    /**
     * TODO add doc
     * @return TODO add doc
     */
    public static CommandHandler<ILiteraturSystemService, ICommand<ILiteraturSystemService>> handler() {
        ////
        return CommandHandler.createFor(new LiteraturSystemService(), values());
    }

    @Override
    public Pattern pattern() {
        return pattern;
    }
    
    /**
     * TODO add doc
     * @param pattern TODO add doc
     * @param input TODO add doc
     * @return TODO add doc
     */
    private static Matcher matcher(final Pattern pattern, final String input) {
        final Matcher m = pattern.matcher(input);
        if (!m.matches())
            throw new IllegalStateException("no match available");
        return m;
    }
}
