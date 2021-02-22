package edu.epam.course.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdValidator {
    private static final Pattern ID_IS_NUMBER = Pattern.compile("\\d+");

    private IdValidator() {
    }

    public static boolean isValidId(String courseId) {
        if (courseId == null || courseId.isBlank()) {
            return false;
        }
        Matcher matcher = ID_IS_NUMBER.matcher(courseId);
        return matcher.matches();
    }
}
