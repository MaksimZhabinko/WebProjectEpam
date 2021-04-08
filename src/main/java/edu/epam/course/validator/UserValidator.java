package edu.epam.course.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type user validator.
 */
public class UserValidator {

    /**
     * The constant email.
     */
    private static final Pattern EMAIL_REGEX = Pattern
            .compile("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
    /**
     * The constant password.
     */
    private static final Pattern PASSWORD_PATTERN = Pattern
            .compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    /* должен включать хотя бы одну букву в верхнем и нижнем регистре, хотя бы одину цифру,
     хотя бы один специальный символ ("@", "#". "$", "%", "^", "&", "( "или") ",
     без пробелов, табуляции и т. Д и не менее 8 символов*/

    /**
     * The The constant string.
     */
    private static final Pattern STRING_PATTERN = Pattern.compile("^[\\p{L}]+$");

    private UserValidator() {
    }

    /**
     * Is name and surname valid.
     *
     * @param name    the name
     * @param surname the surname
     * @return the boolean
     */
    public static boolean isValidNameAndSurname(String name, String surname) {
        boolean isCorrect = true;
        if (name == null || name.isBlank() || surname == null || surname.isBlank()
                || !STRING_PATTERN.matcher(name).matches() || !STRING_PATTERN.matcher(surname).matches()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    /**
     * Is email and password valid.
     *
     * @param email    the email
     * @param password the password
     * @return the boolean
     */
    public static boolean isValidEmailAndPassword(String email, String password) {
        boolean isCorrect = true;
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    /**
     * Is email and for forgot password valid.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmailForForgotPassword(String email) {
        boolean isCorrect = true;
        if (email == null || email.isBlank()){
            isCorrect = false;
        }
        return isCorrect;
    }

    /**
     * Is email valid.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.matches();
    }

    /**
     * Is password and repeat password valid.
     *
     * @param password       the password
     * @param repeatPassword the repeat password
     * @return the boolean
     */
    public static boolean isValidPasswordAndRepeatPassword(String password, String repeatPassword) {
        if (password == null || password.isBlank() || repeatPassword == null || repeatPassword.isBlank() || !password.equals(repeatPassword)) {
            return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}
