package edu.epam.course.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PasswordEncryptionUtilTest {

    @DataProvider(name = "passwordEncryption")
    public static Object[][] passwordEncryption() {
        return new Object[][] {
                {"1", "MQ=="},
                {"maksim", "bWFrc2lt"}
        };
    }

    @Test(dataProvider = "passwordEncryption")
    public void testGetEncoder(String text, String expected) {
        String actual = PasswordEncryptionUtil.getEncoder(text);
        Assert.assertEquals(actual, expected);
    }
}
