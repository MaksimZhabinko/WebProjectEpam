package edu.epam.course.util;

import edu.epam.course.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The type pagination util.
 */
public class PaginationUtil {
    /**
     * The constant limit.
     */
    public static final int USER_LIMIT = 5;

    private PaginationUtil() {
    }

    /**
     * User pagination pages.
     *
     * @param size the size
     * @return the list
     */
    public static List<Integer> paginationUserPages(Long size) {
        Double sizeDouble = Double.valueOf(size);
        Double userLimitDouble = Double.valueOf(USER_LIMIT);
        Double pages = sizeDouble / userLimitDouble;
        double result = Math.ceil(pages);
        List<Integer> paginationPages = new ArrayList<>();
        for (int i = 1; i <= result; i++) {
            paginationPages.add(i);
        }
        return paginationPages;
    }

    /**
     * Users enrolled course limit list.
     *
     * @param allUsersEnrolledCourse the all users enrolled course
     * @param page                   the page
     * @return the list
     */
    public static List<User> usersEnrolledCourseLimit(List<User> allUsersEnrolledCourse, int page) {
        List<User> userLimit = new ArrayList<>();
        int start;
        final int position = 1;
        if (page == position) {
            for (int i = 0; i < USER_LIMIT; i++) {
                userLimit.add(allUsersEnrolledCourse.get(i));
            }
        } else {
            start = (page - position) * USER_LIMIT;
            for (int i = 0; i < USER_LIMIT; i++) {
                if (start + i < allUsersEnrolledCourse.size()) {
                    userLimit.add(allUsersEnrolledCourse.get(i + start));
                } else {
                    break;
                }
            }
        }
        return userLimit;
    }
}
