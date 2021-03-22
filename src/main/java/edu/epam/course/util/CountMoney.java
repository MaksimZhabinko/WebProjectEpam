package edu.epam.course.util;

import java.math.BigDecimal;

public class CountMoney {
    private CountMoney() {
    }

    public static BigDecimal transaction(String courseCost, BigDecimal userMoney) {
        BigDecimal courseCostBigDecimal = new BigDecimal(courseCost);
        BigDecimal result = userMoney.subtract(courseCostBigDecimal);
        return result;
    }
}
