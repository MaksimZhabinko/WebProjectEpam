package edu.epam.course.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type balance replenishment validator.
 */
public class BalanceReplenishmentValidator {
    /**
     * The constant money.
     */
    private static final Pattern MONEY_VALIDATOR = Pattern.compile("\\d+(\\.\\d{0,2})?");

    private BalanceReplenishmentValidator() {
    }

    /**
     * Is money valid.
     *
     * @param stringNumber the string number
     * @return the boolean
     */
    public static boolean isValidMoney(String stringNumber) {
        if (stringNumber == null || stringNumber.isBlank()) {
            return false;
        }
        Matcher matcher = MONEY_VALIDATOR.matcher(stringNumber);
        return matcher.matches();
    }
}
