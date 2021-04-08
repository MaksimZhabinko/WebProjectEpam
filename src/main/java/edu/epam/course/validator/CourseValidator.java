package edu.epam.course.validator;

import java.util.regex.Pattern;

/**
 * The type course validator.
 */
public class CourseValidator {

    /**
     * The The constant string.
     */
    private static final Pattern STRING_PATTERN = Pattern.compile("^[\\p{L}]+$");

    private CourseValidator() {
    }

    /**
     * Is name valid.
     *
     * @param courseName the course name
     * @return the boolean
     */
    public static boolean isValidName(String courseName) {
        boolean isCorrect = true;
        if (courseName == null || courseName.isBlank() || !STRING_PATTERN.matcher(courseName).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
