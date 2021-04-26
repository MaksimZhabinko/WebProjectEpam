package edu.epam.course.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The type Teacher validator test.
 */
public class TeacherValidatorTest {

    /**
     * Valid name and surname object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validNameAndSurname")
    public static Object[][] validNameAndSurname() {
        return new Object[][] {
                {"Maksim", "Zhabinko", true},
                {"", null, false},
                {null, "", false},
                {"", "", false},
                {null, null, false},
                {"Maksim13", "Zhabinko32", false}
        };
    }

    /**
     * Test is valid name and surname.
     *
     * @param name     the name
     * @param surname  the surname
     * @param expected the expected
     */
    @Test(dataProvider = "validNameAndSurname")
    public void testIsValidNameAndSurname(String name, String surname, boolean expected) {
        boolean actual = TeacherValidator.isValidNameAndSurname(name, surname);
        Assert.assertEquals(actual, expected);
    }
}
