package edu.epam.course.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type course details validator.
 */
public class CourseDetailsValidator {
    /**
     * The constant hours.
     */
    private static final Pattern HOURS_VALIDATOR = Pattern.compile("\\d+");
    /**
     * The constant cost.
     */
    private static final Pattern COST_VALIDATOR = Pattern.compile("\\d+");
    /**
     * The constant start of class.
     */
    private static final Pattern START_OF_CLASS_VALIDATOR = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
    /**
     * The constant date.
     */
    private static Pattern DATE_PATTERN = Pattern.compile(
            "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");

    /**
     * The constant string.
     */
    private static final Pattern STRING_PATTERN = Pattern.compile("^[\\p{L}]+$");

    private CourseDetailsValidator() {
    }

    /**
     * Is description valid.
     *
     * @param description the description
     * @return the boolean
     */
    public static boolean isValidDescription(String description) {
        boolean isCorrect = true;
        if (description == null || description.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    /**
     * I hours valid.
     *
     * @param hours the hours
     * @return the boolean
     */
    public static boolean isValidHours(String hours) {
        if (hours == null || hours.isBlank()) {
            return false;
        }
        Matcher matcher = HOURS_VALIDATOR.matcher(hours);
        return matcher.matches();
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
     * Is cost valid.
     *
     * @param cost the cost
     * @return the boolean
     */
    public static boolean isValidCost(String cost) {
        if (cost == null || cost.isBlank()) {
            return false;
        }
        Matcher matcher = COST_VALIDATOR.matcher(cost);
        return matcher.matches();
    }

    /**
     * Is start of class valid.
     *
     * @param startOfClass the start of class
     * @return the boolean
     */
    public static boolean isValidStartOfClass(String startOfClass) {
        if (startOfClass == null || startOfClass.isBlank()) {
            return false;
        }
        Matcher matcher = START_OF_CLASS_VALIDATOR.matcher(startOfClass);
        return matcher.matches();
    }

    /**
     * Is date valid.
     *
     * @param startCourse the start course
     * @param endCourse   the end course
     * @return the boolean
     */
    public static boolean isValidDate(String startCourse, String endCourse) {
        boolean isCorrect = true;
        if (startCourse == null || startCourse.isBlank() || endCourse == null || endCourse.isBlank()) {
            return false;
        }
        Matcher matcherStart = DATE_PATTERN.matcher(startCourse);
        Matcher matcherEnd = DATE_PATTERN.matcher(endCourse);
        if (!matcherStart.matches() || !matcherEnd.matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
