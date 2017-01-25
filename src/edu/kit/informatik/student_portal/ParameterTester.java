package edu.kit.informatik.student_portal;

/**
 * TODO add doc
 * @author s_toledonavarro
 * @version 1.00
 */
public class ParameterTester {
	/**
	 * TODO add doc
	 * @param message TODO add doc
	 * @param input TODO add doc
	 */
	public static void testStringNotNullAndLowercase(final ErrorMessage message, final String input){
		if(input == null)
			throw new IllegalArgumentException(message.getNullErrorMessage());
		if(!input.matches("\\p{javaLowerCase}*"))
			throw new IllegalArgumentException(message.getNotLowerCaseErrorMessage());
	}
	
	/**
	 * TODO add doc
	 * @param enrolmentNumber TODO add doc
	 */
	public static void testEnrolmentNumber(final int enrolmentNumber){
		if (enrolmentNumber > 999999 || enrolmentNumber < 100000)
            throw new IllegalArgumentException("the given enrolment number is invalid");
	}
	
	/**
	 * TODO add doc
	 * @param lectureId TODO add doc
	 */
	public static void testValidLectureId(final int lectureId){
		if (lectureId < 1)
			throw new IllegalArgumentException("the given lecture id is invalid");
	}
	
	/**
	 * TODO add doc
	 * @param moduleId TODO add doc
	 */
	public static void testValidModuleId(final int moduleId){
		if (moduleId < 1)
            throw new IllegalArgumentException("the given module id is invalid");
	}
	
	/**
	 * TODO add doc
	 * @param mark TODO add doc
	 */
	public static void testValidMark(final double mark){
		if (mark < 1.00 || mark > 5.00)
            throw new IllegalArgumentException("invalid mark value");
	}
}
