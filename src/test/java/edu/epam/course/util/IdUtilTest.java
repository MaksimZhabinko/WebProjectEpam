package edu.epam.course.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The type Id util test.
 */
public class IdUtilTest {

    /**
     * String to long object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "stringToLong")
    public static Object[][] stringToLong() {
        return new Object[][] {
                {"10", 10L},
                {"20", 20L}
        };
    }

    /**
     * Test string to long.
     *
     * @param stringId the string id
     * @param expected the expected
     */
    @Test(dataProvider = "stringToLong")
    public void testStringToLong(String stringId, Long expected) {
        Long actual = IdUtil.stringToLong(stringId);
        Assert.assertEquals(actual, expected);
    }
}
