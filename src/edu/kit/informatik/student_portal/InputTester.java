package edu.kit.informatik.student_portal;

public class InputTester {
	public static void testStringNotNullAndLowercase(final ErrorMessage message, final String input){
		if(input == null)
			throw new IllegalArgumentException(message.getNullErrorMessage());
		if(!input.matches("\\p{javaLowerCase}*"))
			throw new IllegalArgumentException(message.getNotLowerCaseErrorMessage());
	}
	
	public static void testEnrolmentNumber(final int enrolmentNumber){
		if (enrolmentNumber > 999999 || enrolmentNumber < 100000)
            throw new IllegalArgumentException("the given enrolment number is invalid");
	}
}
