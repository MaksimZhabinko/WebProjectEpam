package edu.epam.course.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BalanceReplenishmentValidatorTest {

    @DataProvider(name = "validMoney")
    public static Object[][] validMoney() {
        return new Object[][] {
                {"2000", true},
                {"", false},
                {null, false},
                {"-2222", false}
        };
    }

    @Test(dataProvider = "validMoney")
    public void testIsValidMoney(String text, boolean expected) {
        boolean actual = BalanceReplenishmentValidator.isValidMoney(text);
        Assert.assertEquals(actual, expected);
    }
}
