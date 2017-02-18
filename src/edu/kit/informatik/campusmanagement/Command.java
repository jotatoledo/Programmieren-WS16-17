package edu.kit.informatik.campusmanagement;

import static java.util.regex.Pattern.compile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.kit.informatik.Terminal;

/**
 * Command line implementation for the task.
 * 
 * @author  Tobias Bachert
 * @version 1.02, 2016/02/03
 * 
 * @see     "https://sdqweb.ipd.kit.edu/lehre/WS1617-Programmieren/assignment05.pdf"
 */
public enum Command implements CommandHandler.Command<CampusManagement> {
    /**
     * Implementation of the add-professor command as described in the task.
     */
//    ADD_PROFESSOR("add-professor <forename>;<surname>;<chair>") {
    ADD_PROFESSOR("add\\-professor ([a-z]+);([a-z]+);([a-z]+)") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            final Matcher m = matcher(pattern(), s);
            try {
                campusManagement.addProfessor(
                        forename(s, m.start(1), m.end(1)),
                        surname(s, m.start(2), m.end(2)),
                        chair(s, m.start(3), m.end(3)));
                Terminal.printLine("Ok");
            } catch (final IllegalStateException e) {
                Terminal.printLine("Error, " + e);
            }
        }
    },
    /**
     * Implementation of the list-professor command as described in the task.
     */
//    LIST_PROFESSOR("list-professor") {
    LIST_PROFESSOR("list\\-professor") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            campusManagement.professors().sorted()
                    .map(professor -> listing(
                            professor.chair(),
                            professor.forename(),
                            professor.surname(),
                            professor.grade()))
                    .forEachOrdered(Terminal::printLine);
        }
    },
    /**
     * Implementation of the summary-professor command as described in the task.
     */
//    SUMMARY_PROFESSOR("summary-professor <professor>") {
    SUMMARY_PROFESSOR("summary\\-professor ([a-z]+;[a-z]+;[a-z]+)") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            final Matcher m = matcher(pattern(), s);
            try {
                professor(s, m.start(1), m.end(1)).apply(campusManagement)
                .lectures().sorted()
                        .map(lecture -> listing(
                                lecture.id(),
                                lecture.name(),
                                lecture.grade()))
                        .forEachOrdered(Terminal::printLine);
            } catch (final IllegalArgumentException e) {
                Terminal.printLine("Error, " + e);
            }
        }
    },
    
    /**
     * Implementation of the add-student command as described in the task.
     */
//    ADD_STUDENT("add-student <forename>;<surname>;<matrnr>") {
    ADD_STUDENT("add\\-student ([a-z]+);([a-z]+);((?!0)\\d{6})") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            final Matcher m = matcher(pattern(), s);
            try {
                campusManagement.addStudent(
                        forename(s, m.start(1), m.end(1)),
                        surname(s, m.start(2), m.end(2)),
                        matrnr(s, m.start(3), m.end(3)));
                Terminal.printLine("Ok");
            } catch (final IllegalStateException e) {
                Terminal.printLine("Error, " + e);
            }
        }
    },
    /**
     * Implementation of the list-student command as described in the task.
     */
//    LIST_STUDENT("list-student") {
    LIST_STUDENT("list\\-student") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            campusManagement.students().sorted()
                    .map(student -> listing(
                            student.studentnumber(),
                            student.forename(),
                            student.surname(),
                            student.grade()))
                    .forEachOrdered(Terminal::printLine);
        }
    },
    /**
     * Implementation of the summary-student command as described in the task.
     */
//    SUMMARY_STUDENT("summary-student <studentFS>") {
    SUMMARY_STUDENT("summary\\-student ([a-z]+;[a-z]+;(?!0)\\d{6})") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            final Matcher m = matcher(pattern(), s);
            try {
                final Student student = studentFS(s, m.start(1), m.end(1)).apply(campusManagement);
                student.lectures().sorted()
                        .map(lecture -> listing(
                                lecture.id(),
                                lecture.name(),
                                student.grade(lecture)))
                        .forEachOrdered(Terminal::printLine);
            } catch (final IllegalArgumentException e) {
                Terminal.printLine("Error, " + e);
            }
        }
    },
    
    /**
     * Implementation of the add-module command as described in the task.
     */
//    ADD_MODULE("add-module <name>") {
    ADD_MODULE("add\\-module ([a-z]+)") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            final Matcher m = matcher(pattern(), s);
            campusManagement.addModule(name(s, m.start(1), m.end(1)));
            Terminal.printLine("Ok");
        }
    },
    /**
     * Implementation of the list-module command as described in the task.
     */
//    LIST_MODULE("list-module") {
    LIST_MODULE("list\\-module") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            campusManagement.modules().sorted()
                    .map(module -> listing(
                            module.id(),
                            module.name(),
                            module.credits(),
                            module.grade()))
                    .forEachOrdered(Terminal::printLine);
        }
    },
    /**
     * Implementation of the summary-module command as described in the task.
     */
//    SUMMARY_MODULE("summary-module <module>") {
    SUMMARY_MODULE("summary\\-module (\\d+)") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            final Matcher m = matcher(pattern(), s);
            try {
                module(s, m.start(1), m.end(1))
                .apply(campusManagement)
                .lectures()
                .sorted()
                        .map(lecture -> listing(
                                lecture.id(),
                                lecture.name(),
                                lecture.credits(),
                                lecture.grade()))
                        .forEachOrdered(Terminal::printLine);
            } catch (final IllegalArgumentException e) {
                Terminal.printLine("Error, " + e);
            }
        }
    },
    
    /**
     * Implementation of the add-lecture command as described in the task.
     */
//    ADD_LECTURE("add-lecture <name>;<module>;<professor>;<credits>") {
    ADD_LECTURE("add\\-lecture ([a-z]+);(\\d+);([a-z]+;[a-z]+;[a-z]+);(\\d+)") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            final Matcher m = matcher(pattern(), s);
            try {
                campusManagement.addLecture(
                        name(s, m.start(1), m.end(1)),
                        module(s, m.start(2), m.end(2)).apply(campusManagement),
                        professor(s, m.start(3), m.end(3)).apply(campusManagement),
                        credits(s, m.start(4), m.end(4)));
                Terminal.printLine("Ok");
            } catch (final IllegalArgumentException | IllegalStateException e) {
                Terminal.printLine("Error, " + e);
            }
        }
    },
    /**
     * Implementation of the list-lecture command as described in the task.
     */
//    LIST_LECTURE("list-lecture") {
    LIST_LECTURE("list\\-lecture") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            campusManagement.lectures().sorted()
                    .map((lecture) -> listing(
                            lecture.id(),
                            lecture.name(),
                            lecture.credits(),
                            lecture.grade()))
                    .forEachOrdered(Terminal::printLine);
        }
    },
    /**
     * Implementation of the summary-lecture command as described in the task.
     */
//    SUMMARY_LECTURE("summary-lecture <lecture>") {
    SUMMARY_LECTURE("summary\\-lecture (\\d+)") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            final Matcher m = matcher(pattern(), s);
            try {
                final Lecture lecture = lecture(s, m.start(1), m.end(1))
                        .apply(campusManagement);
                lecture.students().sorted()
                        .map((student) -> listing(
                                student.studentnumber(),
                                student.forename(),
                                student.surname(),
                                student.grade(lecture)))
                        .forEachOrdered(Terminal::printLine);
            } catch (final IllegalArgumentException e) {
                Terminal.printLine("Error, " + e);
            }
        }
    },
    
    /**
     * Implementation of the examination-marking command as described in the task.
     */
//    EXAMINATION_MARKING("examination-marking <lecture>;<student>;<grade>") {
    EXAMINATION_MARKING("examination\\-marking (\\d+);((?!0)\\d{6});(\\d?(?:\\.\\d+)?)") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            final Matcher m = matcher(pattern(), s);
            try {
                campusManagement.examinationMarking(
                        lecture(s, m.start(1), m.end(1)).apply(campusManagement),
                        student(s, m.start(2), m.end(2)).apply(campusManagement),
                        grade(s, m.start(3), m.end(3)));
                Terminal.printLine("Ok");
            } catch (final IllegalArgumentException | IllegalStateException e) {
                Terminal.printLine("Error, " + e);
            }
        }
    },
    
    /**
     * Implementation of the reset command as described in the task.
     */
//    RESET("reset") {
    RESET("reset") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
            campusManagement.clear();
            Terminal.printLine("Ok");
        }
    },
    
    /**
     * Implementation of the quit command as described in the task.
     */
//    QUIT("quit") {
    QUIT("quit") {
        
        @Override
        public void apply(
                final CampusManagement campusManagement,
                final String s) {
            ////
        }
        
        @Override
        boolean isQuit() {
            ////
            return true;
        }
    };
    
    private final String stringrep;
    private final Pattern pattern;
    
    Command(
            final String format) {
        ////
        stringrep = format;
        pattern = compile(format);
    }
    
    private static String listing(
            final Object... values) {
        ////
        return Stream.of(values).map(String::valueOf).collect(Collectors.joining(" "));
    }
    
    public static void main(
            final String[] args) {
        ////
        final CommandHandler<?, Command> h = Command.handler(Command.values());
        Command c;
        do {
            c = h.accept(Terminal.readLine(), s -> Terminal.printLine("Error, no such command: '" + s + "'"));
        } while (c == null || !c.isQuit());
    }
    
    public static CommandHandler<?, Command> handler(
            final Command... commands) {
        ////
        return CommandHandler.of(new CampusManagement(), commands);
    }
    
    @SuppressWarnings("static-method")
    boolean isQuit() {
        ////
        return false;
    }
    
    @Override
    public Pattern pattern() {
        ////
        return pattern;
    }
    
    @Override
    public String toString() {
        ////
        return stringrep;
    }
    
//  static final class Tokens {
//
//      static final Token<String> lowercase = regex("[a-z]+");
//      static final Token<String> forename  = lowercase.copy();
//      static final Token<String> surname   = lowercase.copy();
//      static final Token<String> chair     = lowercase.copy();
//      static final IntToken      matrnr    = regex("(?!0)\\d{6}")
//              .mapToInt(Integer::parseInt);
//      static final Token<String> name      = lowercase.copy();
//      static final IntToken      id        = regex("\\d+")
//              .mapToInt(Integer::parseInt);
//      static final IntToken      credits   = regex("\\d+")
//              .mapToInt(Integer::parseInt);
//      static final Token<Grade>  grade     = regex("\\d?(\\.\\d+)?")
//              .mapToDouble(Double::parseDouble).mapToObj(Grade::new);
//
//      static final Token<Accessor<Professor>> professor = of("<forename>;<surname>;<chair>")
//              .map(m -> campus -> campus.professor(m.get(forename), m.get(surname), m.get(chair)));
//      static final Token<Accessor<Student>>   studentFS = of("<forename>;<surname>;<matrnr>")
//              .map(m -> campus -> campus.student(m.get(forename), m.get(surname), m.get(matrnr)));
//      static final Token<Accessor<Student>>   student   = of("<matrnr>")
//              .map(m -> campus -> campus.student(m.get(matrnr)));
//      static final Token<Accessor<Module>>    module    = of("<id>")
//              .map(m -> campus -> campus.module(m.get(id)));
//      static final Token<Accessor<Lecture>>   lecture   = of("<id>")
//              .map(m -> campus -> campus.lecture(m.get(id)));
//  }
    
    /*
     * Note: Please don't implement the command line like below as it is a nightmare to change the input format of any
     * argument, the format of the arguments shouldn't be hard coded into each pattern but should be defined once - done
     * this way to keep it simple and short as everything else would exceed the requirements of the programming lecture.
     * A possible alternative approach (and the source of the patterns/methods) is outlined in the commented parts
     * above.
     */
    private static String lowercase(String string, int s, int e) {
        return string.substring(s, e);
    }
    
    private static String forename(String string, int s, int e) {
        return lowercase(string, s, e);
    }
    
    private static String surname(String string, int s, int e) {
        return lowercase(string, s, e);
    }
    
    private static String chair(String string, int s, int e) {
        return lowercase(string, s, e);
    }
    
    private static int matrnr(String string, int s, int e) {
        return Integer.parseInt(string.substring(s, e));
    }
    
    private static String name(String string, int s, int e) {
        return lowercase(string, s, e);
    }
    
    private static int id(String string, int s, int e) {
        return Integer.parseInt(string.substring(s, e));
    }
    
    private static int credits(String string, int s, int e) {
        return Integer.parseInt(string.substring(s, e));
    }
    
    private static Grade grade(String string, int s, int e) {
        return new Grade(Double.parseDouble(string.substring(s, e)));
    }
    
    private static final Pattern pattern$professor$0 = compile("([a-z]+);([a-z]+);([a-z]+)");
    private static Accessor<Professor> professor(String string, int s, int e) {
        final Matcher m = matcher(pattern$professor$0, string, s, e);
        return campus -> campus.professor(
                forename(string, m.start(1), m.end(1)),
                surname(string, m.start(2), m.end(2)),
                chair(string, m.start(3), m.end(3)));
    }
    
    private static final Pattern pattern$studentFS$0 = compile("([a-z]+);([a-z]+);((?!0)\\d{6})");
    private static Accessor<Student> studentFS(String string, int s, int e) {
        final Matcher m = matcher(pattern$studentFS$0, string, s, e);
        return campus -> campus.student(
                forename(string, m.start(1), m.end(1)),
                surname(string, m.start(2), m.end(2)),
                matrnr(string, m.start(3), m.end(3)));
    }
    
    private static final Pattern pattern$student$0 = compile("((?!0)\\d{6})");
    private static Accessor<Student> student(String string, int s, int e) {
        final Matcher m = matcher(pattern$student$0, string, s, e);
        return campus -> campus.student(
                matrnr(string, m.start(1), m.end(1)));
    }
    
    private static final Pattern pattern$module$0 = compile("(\\d+)");
    private static Accessor<Module> module(String string, int s, int e) {
        final Matcher m = matcher(pattern$module$0, string, s, e);
        return campus -> campus.module(
                id(string, m.start(1), m.end(1)));
    }
    
    private static final Pattern pattern$lecture$0 = compile("(\\d+)");
    private static Accessor<Lecture> lecture(String string, int s, int e) {
        final Matcher m = matcher(pattern$lecture$0, string, s, e);
        return campus -> campus.lecture(
                id(string, m.start(1), m.end(1)));
    }
    
    private static Matcher matcher(Pattern pattern, String string) {
        final Matcher m = pattern.matcher(string);
        if (!m.matches())
            throw new IllegalStateException("No match available");
        return m;
    }
//    
    private static Matcher matcher(Pattern pattern, String string, int s, int e) {
        final Matcher m = pattern.matcher(string).region(s, e);
        if (!m.matches())
            throw new IllegalStateException("No match available");
        return m;
    }
    
    private interface Accessor<T> {
        /**
         * Accessor implementation of apply
         * @param campusManagement
         * @return
         */
        T apply(CampusManagement campusManagement);
    }
}
