package edu.epam.course.validator;

/**
 * The type teacher validator.
 */
public class TeacherValidator {

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
        if (name == null || name.isBlank() || surname == null || surname.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
