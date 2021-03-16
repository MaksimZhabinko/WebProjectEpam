package edu.epam.course.util;

import java.util.Base64;

public class PasswordEncryption {
    private PasswordEncryption() {
    }

    public static String getEncoder(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

}
