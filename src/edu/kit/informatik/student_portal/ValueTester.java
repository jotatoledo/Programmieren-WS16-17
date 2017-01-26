package edu.kit.informatik.student_portal;

/**
 * TODO add doc
 * @author s_toledonavarro
 * @version 1.00
 */
public class ValueTester {
    /**
     * TODO add doc
     * @param message TODO add doc
     * @param input TODO add doc
     * @return TODO add doc
     */
    public static String testStringNotNullAndLowercase(final ErrorMessage message, 
            final String input) {
        if (input == null)
            throw new IllegalArgumentException(message.getNullErrorMessage());
        if (!input.matches("\\p{javaLowerCase}*"))
            throw new IllegalArgumentException(message.getNotLowerCaseErrorMessage());
        return input;
    }

    /**
     * TODO add doc
     * @param enrolmentNumber TODO add doc
     * @return TODO add doc
     */
    public static int testValidEnrolmentNumber(final int enrolmentNumber) {
        if (enrolmentNumber > 999999 || enrolmentNumber < 100000)
            throw new IllegalArgumentException("the given enrolment number is invalid");
        return enrolmentNumber;
    }

    /**
     * TODO add doc
     * @param lectureId TODO add doc
     * @return TODO add doc
     */
    public static int testValidLectureId(final int lectureId) {
        if (lectureId < 1)
            throw new IllegalArgumentException("the given lecture id is invalid");
        return lectureId;
    }

    /**
     * TODO add doc
     * @param moduleId TODO add doc
     * @return TODO add doc
     */
    public static int testValidModuleId(final int moduleId) {
        if (moduleId < 1)
            throw new IllegalArgumentException("the given module id is invalid");
        return moduleId;
    }

    /**
     * TODO add doc
     * @param mark TODO add doc
     * @return TODO add doc
     */
    public static double testValidMark(final double mark) {
        if (mark < 1.00 || mark > 5.00)
            throw new IllegalArgumentException("invalid mark value");
        return mark;
    }

    /**
     * TODO add doc
     * @param credits TODO add doc
     * @return TODO add doc
     */
    public static int testValidCredits(final int credits) {
        if (credits < 0 || credits > 9)
            throw new IllegalArgumentException(credits < 0 ? "credits is smaller than 0" : "credits is bigger than 9");
        return credits;
    }
    
    /**
     * TODO add doc
     * @param input TODO add doc
     * @return TODO add doc
     */
    public static int parseModuleId(final String input) {
        try {
            return testValidModuleId(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid module id");
        }
    }
    
    /**
     * TODO add doc
     * @param input TODO add doc
     * @return TODO add doc
     */
    public static int parseLectureId(final String input) {
        try {
            return testValidLectureId(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid lecture id");
        }
    }
    
    /**
     * TODO add doc
     * @param input TODO add doc
     * @return TODO add doc
     */
    public static int parseCredits(final String input) {
        try {
            return testValidCredits(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid input format for credits");
        }
    }
    
    /**
     * TODO add doc
     * @param input TODO add doc
     * @return TODO add doc
     */
    public static double parseMark(final String input) {
        try {
            return testValidMark(Double.parseDouble(input));
        } catch (NumberFormatException e1) {
            throw new IllegalArgumentException("invalid input format for mark value");
        } catch (NullPointerException e2) {
            throw new IllegalArgumentException("null input for mark value");
        }
    }
    
    /**
     * TODO add doc
     * @param input TODO add doc
     * @return TODO add doc
     */
    public static int parseEnrolmentNumber(final String input) {
        try {
            return testValidEnrolmentNumber(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid input format for enrolment number");
        }
    }
}
