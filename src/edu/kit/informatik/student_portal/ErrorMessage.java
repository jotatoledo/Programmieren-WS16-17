package edu.kit.informatik.student_portal;

/**
 * Error messages for string members of different classes
 * @author s_toledonavarro
 * @version 1.00
 */
public enum ErrorMessage {
	/**
	 * Error messages for first name member of class professor
	 */
	PROFESSOR_FIRSTNAME("professor firstname"),
	/**
	 * Error messages for last name member of class professor
	 */
	PROFESSOR_LASTNAME("professor lastname"),	
	
	/**
	 * Error messages for first name member of class student
	 */
	STUDENT_FIRSTNAME("student firstname"),
	/**
	 * Error messages for last name member of class student
	 */
	STUDENT_LASTNAME("student lastname"),
	
	/**
	 * Error messages for name member of class chair
	 */
	CHAIR_NAME("chair name"),
	
	/**
	 * Error messages for name member of class module
	 */
	MODULE_NAME("module name"),
	
	/**
	 * Error messages for name member of class lecture
	 */
	LECTURE_NAME("lecture name");
	
	private final String nullErrorMessage;
	private final String notLowerCaseErrorMessage;
	
	/**
	 * Creates a new instance
	 * @param propertyName the name of the property associated to this enumeration element
	 */
	ErrorMessage(final String propertyName){
		this.nullErrorMessage = propertyName.concat(" is null");
		this.notLowerCaseErrorMessage = propertyName.concat(" isnt made only of lowercase letters");
	}

	/**
	 * Get the error message for null value
	 * @return TODO the error message for null value
	 */
	public String getNullErrorMessage() {
		return nullErrorMessage;
	}

	/**
	 * Get the error message for value not made exclusively of lower cases
	 * @return TODO the error message for not lower cases value
	 */
	public String getNotLowerCaseErrorMessage() {
		return notLowerCaseErrorMessage;
	}
}
