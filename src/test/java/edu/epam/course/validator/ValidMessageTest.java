package edu.epam.course.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidMessageTest {

    @DataProvider(name = "validMessage")
    public static Object[][] validMessage() {
        return new Object[][] {
                {"some text", true},
                {"", false},
                {null, false}
        };
    }

    @Test(dataProvider = "validMessage")
    public void testIsValidMessage(String text, boolean expected) {
        boolean actual = ValidMessage.isValidMessage(text);
        Assert.assertEquals(actual, expected);
    }
}
