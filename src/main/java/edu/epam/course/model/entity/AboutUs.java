package edu.epam.course.model.entity;

public class AboutUs extends Entity{
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AboutUs aboutUs = (AboutUs) o;

        return message != null ? message.equals(aboutUs.message) : aboutUs.message == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AboutUs{");
        sb.append("id=").append(getId());
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
