package edu.kit.informatik.student_portal.common;

public enum Command {
    /**
     * TODO
     */
    ADD_PROFESSOR("add-professor"),
    /**
     * TODO
     */
    LIST_PROFESSOR("list-professor"),
    /**
     * TODO
     */
    SUMMARY_PROFESSOR("summary-professor"),
    /**
     * TODO
     */
    ADD_STUDENT("add-student"),
    /**
     * TODO
     */
    LIST_STUDENT("list-student"),
    /**
     * TODO
     */
    SUMMARY_STUDENT("summary-student"),
    /**
     * TODO
     */
    ADD_MODULE("add-module"),
    /**
     * TODO
     */
    LIST_MODULE("list-module"),
    /**
     * TODO
     */
    SUMMARY_MODULE("summary-module"),
    /**
     * TODO
     */
    ADD_LECTURE("add-lecture"),
    /**
     * TODO
     */
    LIST_LECTURE("list-lecture"),
    /**
     * TODO
     */
    SUMMARY_LECTURE("summary-lecture"),
    /**
     * TODO
     */
    EXAMINATION_MARKING("examination-marking"),
    /**
     * TODO
     */
    QUIT("quit"),
    /**
     * TODO
     */
    RESET("reset");
    
    private final String commandText;
    private final int length;
    
    /**
     * 
     * @param commandText TODO
     */
    Command(final String commandText) {
        this.commandText = commandText;
        length = commandText.length();
    }

    /**
     * 
     * @return TODO
     */
    public String getCommandText() {
        return commandText;
    }

    /**
     * 
     * @return TODO
     */
    public int getLength() {
        return length;
    }
}
