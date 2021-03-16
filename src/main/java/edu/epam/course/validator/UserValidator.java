package edu.epam.course.validator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final Pattern EMAIL_REGEX = Pattern
            .compile("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
    private static final Pattern PASSWORD_PATTERN = Pattern
            .compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    /* должен включать хотя бы одну букву в верхнем и нижнем регистре, хотя бы одину цифру,
     хотя бы один специальный символ ("@", "#". "$", "%", "^", "&", "( "или") ",
     без пробелов, табуляции и т. Д и не менее 8 символов*/

    private UserValidator() {
    }

    public static boolean isValidNameAndSurname(String name, String surname) {
        boolean isCorrect = true;
        if (name == null || name.isBlank() || surname == null || surname.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isValidEmailAndPassword(String email, String password) {
        boolean isCorrect = true;
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isValidEmailForForgotPassword(String email) {
        boolean isCorrect = true;
        if (email == null || email.isBlank()){
            isCorrect = false;
        }
        return isCorrect;
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPasswordAndRepeatPassword(String password, String repeatPassword) {
        if (password == null || password.isBlank() || repeatPassword == null || repeatPassword.isBlank() || !password.equals(repeatPassword)) {
            return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}
