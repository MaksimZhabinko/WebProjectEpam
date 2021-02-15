package edu.epam.course.validator;

public class ReviewValidator {
    private ReviewValidator() {
    }

    public static boolean isValidMessage(String message) {
        boolean isCorrect = true;
        if (message == null || message.isBlank()) {
            isCorrect = false;
        }
        return isCorrect;
    }
}
