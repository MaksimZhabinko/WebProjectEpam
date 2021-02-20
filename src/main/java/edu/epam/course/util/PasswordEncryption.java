package edu.epam.course.util;

import java.util.Base64;

public class PasswordEncryption {
    public static String getEncoder(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    private PasswordEncryption() {
    }

    public static String getDecoder(String string) {
        byte[] decode = Base64.getDecoder().decode(string.getBytes());
        String decodeString = new String(decode);
        return decodeString;
    }
}
