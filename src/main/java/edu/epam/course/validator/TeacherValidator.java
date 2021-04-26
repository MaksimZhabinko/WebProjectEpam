package edu.epam.course.validator;

import java.util.regex.Pattern;

/**
 * The type teacher validator.
 */
public class TeacherValidator {

    /**
     * The constant string.
     */
    private static final Pattern STRING_PATTERN = Pattern.compile("^[\\p{L}]+$");

    private TeacherValidator() {
    }

    /**
     * Is name and surname valid.
     *
     * @param name    the name
     * @param surname the surname
     * @return the boolean
     */
    public static boolean isValidNameAndSurname(String name, String surname) {
        boolean isCorrect = true;
        if (name == null || name.isBlank() || surname == null || surname.isBlank()
                || !STRING_PATTERN.matcher(name).matches() || !STRING_PATTERN.matcher(surname).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    /**
     * Is valid teachers boolean.
     *
     * @param teachersId the teachers
     * @return the boolean
     */
    public static boolean isValidTeachersId(String[] teachersId) {
        boolean isCorrect = true;
        if (teachersId == null || teachersId.length == 0) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
