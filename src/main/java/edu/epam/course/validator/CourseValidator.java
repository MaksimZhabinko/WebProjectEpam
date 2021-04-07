package edu.epam.course.validator;

/**
 * The type course validator.
 */
public class CourseValidator {

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
        if (courseName == null || courseName.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
