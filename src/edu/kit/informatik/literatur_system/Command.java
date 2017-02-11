package edu.kit.informatik.literatur_system;

import static java.util.regex.Pattern.compile;

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
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc
     * add journal <name>,<publisher>
     */
    ADD_JOURNAL("add journal ([a-zA-Z]+),([a-zA-Z]+)") {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
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
     * TODO add doc
     * add article to <series/journal>:<id>,<year>,<title>
     */
    ADD_ARTICLE("add article to ([a-zA-Z]+):([a-zA-Z]+),((?!0)\\d{4}),([a-zA-Z]+)") {
        @Override
        public void execute(final ILiteraturSystemService service, 
                            final String string) {
            // TODO Auto-generated method stub
            
        }
    },
    /**
     * TODO add doc
     * written-by <publication>,<list of author names>
     */
    WRITTEN_BY("") {
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
        ////
        stringrep = format;
        pattern = compile(format);
    }
    
    /**
     * TODO add doc
     * @return TODO add doc
     */
    public boolean isQuit() {
        return this == QUIT;
    }

    /**
     * TODO add doc
     * @return TODO add doc
     */
    public static CommandHandler<ILiteraturSystemService, Command> handler() {
        ////
        return CommandHandler.of(new LiteraturSystemService(), values());
    }

    @Override
    public Pattern pattern() {
        return pattern;
    }
}
