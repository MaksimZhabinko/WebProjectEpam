package edu.epam.course.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The type Message validator test.
 */
public class MessageValidatorTest {

    /**
     * Valid message object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validMessage")
    public static Object[][] validMessage() {
        return new Object[][] {
                {"some text", true},
                {"", false},
                {null, false}
        };
    }

    /**
     * Test is valid message.
     *
     * @param text     the text
     * @param expected the expected
     */
    @Test(dataProvider = "validMessage")
    public void testIsValidMessage(String text, boolean expected) {
        boolean actual = MessageValidator.isValidMessage(text);
        Assert.assertEquals(actual, expected);
    }
}
