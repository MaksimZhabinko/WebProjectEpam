package edu.epam.course.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

/**
 * The type Count money util test.
 */
public class CountMoneyUtilTest {

    /**
     * Transaction object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "transaction")
    public static Object[][] transaction() {
        return new Object[][] {
                {new BigDecimal(1500), new BigDecimal(2000), new BigDecimal(500)},
                {new BigDecimal(500), new BigDecimal(2000), new BigDecimal(1500)}
        };
    }

    /**
     * Test transaction.
     *
     * @param courseCost the course cost
     * @param userMoney  the user money
     * @param expected   the expected
     */
    @Test(dataProvider = "transaction")
    public void testTransaction(BigDecimal courseCost, BigDecimal userMoney, BigDecimal expected) {
        BigDecimal actual = CountMoneyUtil.transaction(courseCost, userMoney);
        Assert.assertEquals(actual, expected);
    }
}
