package edu.kit.informatik.student_portal.common;

public enum Command {
    /**
     * Command used to add a new professor to the system
     */
    ADD_PROFESSOR("add-professor"),
    /**
     * Command used to list the professor stored in the system
     */
    LIST_PROFESSOR("list-professor"),
    /**
     * Command used to display the information of a professor stored in the system
     */
    SUMMARY_PROFESSOR("summary-professor"),
    /**
     * Command used to add a new student to the system
     */
    ADD_STUDENT("add-student"),
    /**
     * Command used to list the students stored in the system
     */
    LIST_STUDENT("list-student"),
    /**
     * Command used to display the information of a student stored in the system
     */
    SUMMARY_STUDENT("summary-student"),
    /**
     * Command used to add a new module to the system
     */
    ADD_MODULE("add-module"),
    /**
     * Command used to list the modules stored in the system
     */
    LIST_MODULE("list-module"),
    /**
     * Command used to display the information of a module stored in the system
     */
    SUMMARY_MODULE("summary-module"),
    /**
     * Command used to add a new lecture to the system
     */
    ADD_LECTURE("add-lecture"),
    /**
     * Command used to list the lectures stored in the system
     */
    LIST_LECTURE("list-lecture"),
    /**
     * Command used to display the information of a lecture stored in the system
     */
    SUMMARY_LECTURE("summary-lecture"),
    /**
     * Command used to add a new mark
     */
    EXAMINATION_MARKING("examination-marking"),
    /**
     * Command used to quit
     */
    QUIT("quit"),
    /**
     * Command used to reset the information stored in the system
     */
    RESET("reset");
    
    private final String commandText;
    private final int length;
    
    /**
     * Creates a new instance
     * @param commandText The text representation of a console command
     */
    Command(final String commandText) {
        this.commandText = commandText;
        length = commandText.length();
    }

    /**
     * Get command text
     * @return Text prefix value
     */
    public String getCommandText() {
        return commandText;
    }

    /**
     * Get length of the text command
     * @return Length of the text prefix
     */
    public int getLength() {
        return length;
    }
}
