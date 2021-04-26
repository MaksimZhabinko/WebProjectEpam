package edu.epam.course.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The type Course validator test.
 */
public class CourseValidatorTest {

    /**
     * Valid name object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validName")
    public static Object[][] validName() {
        return new Object[][] {
                {"java", true},
                {"", false},
                {null, false},
                {"java 13", true}
        };
    }

    /**
     * Test is valid name.
     *
     * @param name     the name
     * @param expected the expected
     */
    @Test(dataProvider = "validName")
    public void testIsValidName(String name, boolean expected) {
        boolean actual = CourseValidator.isValidName(name);
        Assert.assertEquals(actual, expected);
    }
}
