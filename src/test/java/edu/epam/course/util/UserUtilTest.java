package edu.epam.course.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class UserUtilTest {

    @DataProvider(name = "transaction")
    public static Object[][] transaction() {
        return new Object[][] {
                {"999", new BigDecimal(1500), new BigDecimal(501)},
                {"1499", new BigDecimal(1500), new BigDecimal(1)}
        };
    }

    @Test(dataProvider = "transaction")
    public void testTransaction(String courseCost, BigDecimal userMoney, BigDecimal expected) {
        BigDecimal actual = UserUtil.transaction(courseCost, userMoney);
        Assert.assertEquals(actual, expected);
    }
}
