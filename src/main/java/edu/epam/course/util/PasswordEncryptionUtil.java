package edu.epam.course.util;

import java.util.Base64;

/**
 * The type password encryption util.
 */
public class PasswordEncryptionUtil {

    private PasswordEncryptionUtil() {
    }

    /**
     * Encoder.
     *
     * @param string the string
     * @return the string
     */
    public static String getEncoder(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

}
