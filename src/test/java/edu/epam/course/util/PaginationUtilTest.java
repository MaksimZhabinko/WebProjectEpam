package edu.epam.course.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Pagination util test.
 */
public class PaginationUtilTest {

    /**
     * Pagination user pages object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "paginationUserPages")
    public static Object[][] paginationUserPages() {
        return new Object[][] {
                {10L, new ArrayList<>(Arrays.asList(1,2))},
                {20L, new ArrayList<>(Arrays.asList(1,2,3,4))}
        };
    }

    /**
     * Test pagination user pages.
     *
     * @param size     the size
     * @param expected the expected
     */
    @Test(dataProvider = "paginationUserPages")
    public void testPaginationUserPages(Long size, ArrayList expected) {
        List<Integer> actual =  PaginationUtil.paginationUserPages(size);
        Assert.assertEquals(actual, expected);
    }
}
