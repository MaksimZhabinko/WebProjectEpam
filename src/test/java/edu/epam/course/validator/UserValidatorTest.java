package edu.epam.course.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {

    @DataProvider(name = "validNameAndSurname")
    public static Object[][] validNameAndSurname() {
        return new Object[][] {
                {"Maksim", "Zhabinko", true},
                {"", null, false},
                {null, "", false},
                {"", "", false},
                {null, null, false}
        };
    }

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

    @DataProvider(name = "validEmailForForgotPassword")
    public static Object[][] validEmailForForgotPassword() {
        return new Object[][] {
                {"maxim.style@mail.ru", true},
                {"", false},
                {null, false}
        };
    }

    @DataProvider(name = "validEmail")
    public static Object[][] validEmail() {
        return new Object[][] {
                {"maxim.style@mail.ru", true},
                {"", false},
                {null, false},
                {"fdvs", false}
        };
    }

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

    @Test(dataProvider = "validNameAndSurname")
    public void testIsValidNameAndSurname(String name, String surname, boolean expected) {
        boolean actual = UserValidator.isValidNameAndSurname(name, surname);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "validEmailAndPassword")
    public void testIsValidEmailAndPassword(String email, String password, boolean expected) {
        boolean actual = UserValidator.isValidEmailAndPassword(email, password);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "validEmailForForgotPassword")
    public void testIsValidEmailForForgotPassword(String email, boolean expected) {
        boolean actual = UserValidator.isValidEmailForForgotPassword(email);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "validEmail")
    public void testIsValidEmail(String email, boolean expected) {
        boolean actual = UserValidator.isValidEmail(email);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "validPasswordAndRepeatPassword")
    public void testIsValidPasswordAndRepeatPassword(String password, String repeatPassword, boolean expected) {
        boolean actual = UserValidator.isValidPasswordAndRepeatPassword(password, repeatPassword);
        Assert.assertEquals(actual, expected);
    }
}
