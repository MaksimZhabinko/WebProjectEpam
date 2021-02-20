package edu.epam.course.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseValidator {
    private static final Pattern COURSE_ID_IS_NUMBER = Pattern.compile("\\d+");

    private CourseValidator() {
    }

    public static boolean isValidCourseId(String courseId) {
        if (courseId == null || courseId.isBlank()) {
            return false;
        }
        Matcher matcher = COURSE_ID_IS_NUMBER.matcher(courseId);
        return matcher.matches();
    }
}
