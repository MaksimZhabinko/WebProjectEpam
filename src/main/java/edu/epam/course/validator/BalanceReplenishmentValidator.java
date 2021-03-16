package edu.epam.course.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BalanceReplenishmentValidator {
    private static final Pattern MONEY_VALIDATOR = Pattern.compile("\\d+(\\.\\d{0,2})?");

    private BalanceReplenishmentValidator() {
    }

    public static boolean isValidMoney(String stringNumber) {
        if (stringNumber == null || stringNumber.isBlank()) {
            return false;
        }
        Matcher matcher = MONEY_VALIDATOR.matcher(stringNumber);
        return matcher.matches();
    }
}
