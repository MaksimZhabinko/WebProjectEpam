package edu.epam.course.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The type Course details validator test.
 */
public class CourseDetailsValidatorTest {

    /**
     * Valid description object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validDescription")
    public static Object[][] validDescription() {
        return new Object[][] {
                {"Description", true},
                {"", false},
                {null, false}
        };
    }

    /**
     * Valid hours object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validHours")
    public static Object[][] validHours() {
        return new Object[][] {
                {"77", true},
                {"", false},
                {null, false},
                {"fds43", false}
        };
    }

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
     * Valid cost object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validCost")
    public static Object[][] validCost() {
        return new Object[][] {
                {"1499", true},
                {"", false},
                {null, false},
                {"1499f", false}
        };
    }

    /**
     * Valid start of class object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validStartOfClass")
    public static Object[][] validStartOfClass() {
        return new Object[][] {
                {"19:00", true},
                {"", false},
                {null, false},
                {"19:00F", false}
        };
    }

    /**
     * Valid date object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validDate")
    public static Object[][] validDate() {
        return new Object[][] {
                {"2021-03-15", "2021-06-15", true},
                {"", "", false},
                {null, "", false},
                {"", null, false},
                {"2021-03-15F", "2021-06-15F", false}
        };
    }


    /**
     * Test is valid description.
     *
     * @param description the description
     * @param expected    the expected
     */
    @Test(dataProvider = "validDescription")
    public void testIsValidDescription(String description, boolean expected) {
        boolean actual = CourseDetailsValidator.isValidDescription(description);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test is valid hours.
     *
     * @param hours    the hours
     * @param expected the expected
     */
    @Test(dataProvider = "validHours")
    public void testIsValidHours(String hours, boolean expected) {
        boolean actual = CourseDetailsValidator.isValidHours(hours);
        Assert.assertEquals(actual, expected);
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
        boolean actual = CourseDetailsValidator.isValidNameAndSurname(name, surname);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test is valid cost.
     *
     * @param cost     the cost
     * @param expected the expected
     */
    @Test(dataProvider = "validCost")
    public void testIsValidCost(String cost, boolean expected) {
        boolean actual = CourseDetailsValidator.isValidCost(cost);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test is valid start of class.
     *
     * @param startOfClass the start of class
     * @param expected     the expected
     */
    @Test(dataProvider = "validStartOfClass")
    public void testIsValidStartOfClass(String startOfClass, boolean expected) {
        boolean actual = CourseDetailsValidator.isValidStartOfClass(startOfClass);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test is valid date.
     *
     * @param startCourse the start course
     * @param endCourse   the end course
     * @param expected    the expected
     */
    @Test(dataProvider = "validDate")
    public void testIsValidDate(String startCourse, String endCourse, boolean expected) {
        boolean actual = CourseDetailsValidator.isValidDate(startCourse, endCourse);
        Assert.assertEquals(actual, expected);
    }
}
