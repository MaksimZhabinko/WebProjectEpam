package edu.epam.course.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The type User validator test.
 */
public class UserValidatorTest {

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
     * Valid email and password object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validEmailAndPassword")
    public static Object[][] validEmailAndPassword() {
        return new Object[][] {
                {"maxim.style@mail.ru", "Maksman14789632@", true},
                {"", null, false},
                {null, "", false},
                {"", "", false},
                {null, null, false}
        };
    }

    /**
     * Valid email for forgot password object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validEmailForForgotPassword")
    public static Object[][] validEmailForForgotPassword() {
        return new Object[][] {
                {"maxim.style@mail.ru", true},
                {"", false},
                {null, false}
        };
    }

    /**
     * Valid email object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validEmail")
    public static Object[][] validEmail() {
        return new Object[][] {
                {"maxim.style@mail.ru", true},
                {"", false},
                {null, false},
                {"fdvs", false}
        };
    }

    /**
     * Valid password and repeat password object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "validPasswordAndRepeatPassword")
    public static Object[][] validPasswordAndRepeatPassword() {
        return new Object[][] {
                {"Maksman14789632@", "Maksman14789632@", true},
                {"", null, false},
                {null, "", false},
                {"", "", false},
                {null, null, false},
                {"Maksman14789632@", "Maksman14789632", false}
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
        boolean actual = UserValidator.isValidNameAndSurname(name, surname);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test is valid email and password.
     *
     * @param email    the email
     * @param password the password
     * @param expected the expected
     */
    @Test(dataProvider = "validEmailAndPassword")
    public void testIsValidEmailAndPassword(String email, String password, boolean expected) {
        boolean actual = UserValidator.isValidEmailAndPassword(email, password);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test is valid email for forgot password.
     *
     * @param email    the email
     * @param expected the expected
     */
    @Test(dataProvider = "validEmailForForgotPassword")
    public void testIsValidEmailForForgotPassword(String email, boolean expected) {
        boolean actual = UserValidator.isValidEmailForForgotPassword(email);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test is valid email.
     *
     * @param email    the email
     * @param expected the expected
     */
    @Test(dataProvider = "validEmail")
    public void testIsValidEmail(String email, boolean expected) {
        boolean actual = UserValidator.isValidEmail(email);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test is valid password and repeat password.
     *
     * @param password       the password
     * @param repeatPassword the repeat password
     * @param expected       the expected
     */
    @Test(dataProvider = "validPasswordAndRepeatPassword")
    public void testIsValidPasswordAndRepeatPassword(String password, String repeatPassword, boolean expected) {
        boolean actual = UserValidator.isValidPasswordAndRepeatPassword(password, repeatPassword);
        Assert.assertEquals(actual, expected);
    }
}
