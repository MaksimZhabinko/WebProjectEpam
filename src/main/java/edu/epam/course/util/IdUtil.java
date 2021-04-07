package edu.epam.course.util;

/**
 * The type id util.
 */
public class IdUtil {

    private IdUtil() {
    }

    /**
     * Convert string to long.
     *
     * @param stringId the string id
     * @return the long
     */
    public static Long stringToLong(String stringId) {
        Long id = Math.abs(Long.valueOf(stringId));
        return id;
    }
}
