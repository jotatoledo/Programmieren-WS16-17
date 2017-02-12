package edu.kit.informatik.literatur_system;

import static java.util.regex.Pattern.compile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO add doc
 * @author JoseNote
 * @version %I%, %G%
 */
public enum Command implements ICommand<ILiteraturSystemService> {
    /**
     * TODO add doc
     * add author <first name>,<last name>
     */
    ADD_AUTOR("add autor ([a-zA-Z]+),([a-zA-Z]+)") {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String input) {
            // TODO implement
            final Matcher m = matcher(pattern(), input);
            
        }
    },
    /**
     * TODO add doc
     * add journal <name>,<publisher>
     */
    ADD_JOURNAL("add journal ([a-zA-Z]+),([a-zA-Z]+)") {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String input) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc
     * add conference series <name>
     */
    ADD_CONFERENCE_SERIES("add conference series ([a-zA-Z]+)") {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc
     * add conference <series>,<year>,<location>
     */
    ADD_CONFERENCE("add conference ([a-zA-Z]+),((?!0)\\d{4}),([a-zA-Z]+)") {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc. Change id to accept numbers
     * add article to <series/journal>:<id>,<year>,<title>
     */
    ADD_ARTICLE("add article to ([a-zA-Z]+):([a-z0-9]+),((?!0)\\d{4}),([a-zA-Z]+)") {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc complete
     * written-by <publication>,<list of author names>
     */
    WRITTEN_BY("written-by ([a-zA-Z]+),()((;())*)") {
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
    CITES("cites ([a-zA-Z]+),([a-zA-Z]+)") {
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
    ADD_KEYWORDS_TO("add keywords to ([a-zA-Z]+)()") {
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
    ALL_PUBLICATIONS("all publications") {
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
    LIST_INVALID_PUBLICATIONS("list invalid publications") {
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
    PUBLICATIONS_BY("publications by ()") {
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
    IN_PROCEEDINGS("in proceedings ([a-zA-Z]+),((?!0)\\d{4})") {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc
     */
    QUIT("quit") {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
        }
    };
    
    private final String stringrep;
    private final Pattern pattern;
    
    
    private Command(
            final String format) {
        stringrep = format;
        pattern = compile(format);
    }
    
    @Override
    public boolean isQuit() {
        return this == QUIT;
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
