package edu.epam.project.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final Pattern EMAIL_REGEX = Pattern
            .compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    private static final Pattern PASSWORD_PATTERN = Pattern
            .compile("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
    /* должен включать хотя бы одну букву в верхнем и нижнем регистре, хотя бы одину цифру,
     хотя бы один специальный символ ("@", "#". "$", "%", "^", "&", "( "или") ",
     без пробелов, табуляции и т. Д и не менее 8 символов*/

    public boolean isValidNameAndSurname(String name, String surname) {
        boolean isCorrect = true;
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            isCorrect = false;
        }
        return isCorrect;
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) return false;
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}
