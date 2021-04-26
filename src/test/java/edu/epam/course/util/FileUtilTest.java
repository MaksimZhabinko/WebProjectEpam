package edu.epam.course.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type File util test.
 */
public class FileUtilTest {

    /**
     * Test generate name.
     */
    @Test
    public void testGenerateName() {
        boolean condition = FileUtil.generateName("efdhgbhevkr.img").isEmpty();
        Assert.assertFalse(condition);
    }
}
