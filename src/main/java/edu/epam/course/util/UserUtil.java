package edu.epam.course.util;

import java.math.BigDecimal;

public class UserUtil {
    private UserUtil() {
    }

    public static BigDecimal transaction(String courseCost, BigDecimal userMoney) {
        BigDecimal courseCostBigDecimal = new BigDecimal(courseCost);
        BigDecimal result = userMoney.subtract(courseCostBigDecimal);
        return result;
    }
}
