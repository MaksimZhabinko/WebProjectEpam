package edu.epam.course.model.entity;

import java.time.LocalDate;

public class Review extends Entity {
    private String message;
    private LocalDate dateMessage;
    private User user;

    public Review() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(LocalDate dateMessage) {
        this.dateMessage = dateMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Review review = (Review) o;

        if (message != null ? !message.equals(review.message) : review.message != null) return false;
        if (dateMessage != null ? !dateMessage.equals(review.dateMessage) : review.dateMessage != null) return false;
        return user != null ? user.equals(review.user) : review.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (dateMessage != null ? dateMessage.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Review{");
        sb.append("message='").append(message).append('\'');
        sb.append(", dateMessage=").append(dateMessage);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
