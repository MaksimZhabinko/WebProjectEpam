package edu.epam.course.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Property reader util test.
 */
public class PropertyReaderUtilTest {

    /**
     * Test get path.
     */
    @Test
    public void testGetPath() {
        String actual = PropertyReaderUtil.getPath();
        String expected = "/Users/dasik/Desktop/photoUsersCourses/";
        Assert.assertEquals(actual, expected);
    }
}
