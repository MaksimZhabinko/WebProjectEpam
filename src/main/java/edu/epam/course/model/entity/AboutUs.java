package edu.epam.course.model.entity;

/**
 * The type about us.
 */
public class AboutUs extends Entity{
    private String message;

    /**
     * Instantiates a new About us.
     */
    public AboutUs() {
    }

    /**
     * Instantiates a new About us.
     *
     * @param id      the id
     * @param message the message
     */
    public AboutUs(Long id, String message) {
        super(id);
        this.message = message;
    }

    /**
     * Builder about us builder.
     *
     * @return the about us builder
     */
    public static AboutUsBuilder builder() {
        return new AboutUsBuilder();
    }

    /**
     * Get message.
     *
     * @return the email
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set message.
     *
     * @param message the message
     */
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

    /**
     * The type About us builder.
     */
    public static class AboutUsBuilder {
        private Long id;
        private String message;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public AboutUsBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets message.
         *
         * @param message the message
         * @return the message
         */
        public AboutUsBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Build about us.
         *
         * @return the about us
         */
        public AboutUs build() {
            return new AboutUs(id, message);
        }
    }
}
