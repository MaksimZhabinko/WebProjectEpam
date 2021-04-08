package edu.epam.course.util;

import java.math.BigDecimal;

/**
 * The type count money util.
 */
public class CountMoneyUtil {

    private CountMoneyUtil() {
    }

    /**
     * Transaction money.
     *
     * @param courseCost the course cost
     * @param userMoney  the userMoney
     * @return the big decimal
     */
    public static BigDecimal transaction(BigDecimal courseCost, BigDecimal userMoney) { // todo как обозвать
        BigDecimal result = userMoney.subtract(courseCost);
        return result;
    }
}
