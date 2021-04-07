package edu.epam.course.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The type pagination util.
 */
public class PaginationUtil {
    /**
     * The constant limit.
     */
    public static final int USER_LIMIT = 2;

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
}
