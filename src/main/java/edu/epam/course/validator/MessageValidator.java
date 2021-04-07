package edu.epam.course.validator;

/**
 * The type message validator
 */
public class MessageValidator {

    private MessageValidator() {
    }

    /**
     * Is message valid.
     *
     * @param message the message
     * @return the boolean
     */
    public static boolean isValidMessage(String message) {
        boolean isCorrect = true;
        if (message == null || message.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
