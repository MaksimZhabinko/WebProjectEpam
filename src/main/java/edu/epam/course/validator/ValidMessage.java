package edu.epam.course.validator;

public class ValidMessage {
    private ValidMessage() {
    }

    public static boolean isValidMessage(String message) {
        boolean isCorrect = true;
        if (message == null || message.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
