package edu.kit.informatik.student_portal;

/**
 * TODO add doc
 * @author s_toledonavarro
 * @version 1.00
 */
public enum ErrorMessage {
	/**
	 * TODO add doc
	 */
	PROFESSOR_FIRSTNAME("professor firstname"),
	/**
	 * TODO add doc
	 */
	PROFESSOR_LASTNAME("professor lastname"),	
	
	/**
	 * TODO add doc
	 */
	STUDENT_FIRSTNAME("student firstname"),
	/**
	 * TODO add doc
	 */
	STUDENT_LASTNAME("student lastname"),
	
	/**
	 * TODO add doc
	 */
	CHAIR_NAME("chair name"),
	
	/**
	 * TODO add doc
	 */
	MODULE_NAME("module name"),
	
	/**
	 * TODO add doc
	 */
	LECTURE_NAME("lecture name");
	
	private final String nullErrorMessage;
	private final String notLowerCaseErrorMessage;
	
	/**
	 * TODO add doc
	 * @param propertyName TODO add doc
	 */
	ErrorMessage(final String propertyName){
		this.nullErrorMessage = propertyName.concat(" is null");
		this.notLowerCaseErrorMessage = propertyName.concat(" isnt made only of lowercase letters");
	}

	/**
	 * TODO add doc
	 * @return TODO add doc
	 */
	public String getNullErrorMessage() {
		return nullErrorMessage;
	}

	/**
	 * TODO add doc
	 * @return TODO add doc
	 */
	public String getNotLowerCaseErrorMessage() {
		return notLowerCaseErrorMessage;
	}
}
