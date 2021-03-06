package edu.epam.course.model.entity;

import java.math.BigDecimal;

/**
 * The type User.
 */
public class User extends Entity{
    private String email;
    private String name;
    private String surname;
    private RoleType role;
    private boolean enabled;
    private BigDecimal money;
    private String photo;
    private Course course;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param id      the id
     * @param email   the email
     * @param name    the name
     * @param surname the surname
     * @param role    the role
     * @param enabled the enabled
     * @param money   the money
     * @param photo   the photo
     * @param course  the course
     */
    public User(Long id, String email, String name, String surname, RoleType role,
                boolean enabled, BigDecimal money, String photo, Course course) {
        super(id);
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.enabled = enabled;
        this.money = money;
        this.photo = photo;
        this.course = course;
    }

    /**
     * Builder user builder.
     *
     * @return the user builder
     */
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    /**
     * Instantiates a new User.
     *
     * @param email   the email
     * @param name    the name
     * @param surname the surname
     * @param role    the role
     * @param enabled the enabled
     */
    public User(String email, String name, String surname, RoleType role, boolean enabled) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.enabled = enabled;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public RoleType getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(RoleType role) {
        this.role = role;
    }

    /**
     * Is enabled boolean.
     *
     * @return the boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enabled.
     *
     * @param enabled the enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * Sets money.
     *
     * @param money the money
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Gets course.
     *
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets course.
     *
     * @param course the course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (enabled != user.enabled) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (role != user.role) return false;
        if (money != null ? !money.equals(user.money) : user.money != null) return false;
        if (photo != null ? !photo.equals(user.photo) : user.photo != null) return false;
        return course != null ? course.equals(user.course) : user.course == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(getId());
        sb.append(", email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", role=").append(role);
        sb.append(", enabled=").append(enabled);
        sb.append(", money=").append(money);
        sb.append(", photo='").append(photo).append('\'');
        sb.append(", course=").append(course);
        sb.append('}');
        return sb.toString();
    }

    /**
     * The type User builder.
     */
    public static class UserBuilder {
        private Long id;
        private String email;
        private String name;
        private String surname;
        private RoleType role;
        private boolean enabled;
        private BigDecimal money;
        private String photo;
        private Course course;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public UserBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets email.
         *
         * @param email the email
         * @return the email
         */
        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets surname.
         *
         * @param surname the surname
         * @return the surname
         */
        public UserBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * Sets role.
         *
         * @param role the role
         * @return the role
         */
        public UserBuilder setRole(RoleType role) {
            this.role = role;
            return this;
        }

        /**
         * Sets enabled.
         *
         * @param enabled the enabled
         * @return the enabled
         */
        public UserBuilder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        /**
         * Sets money.
         *
         * @param money the money
         * @return the money
         */
        public UserBuilder setMoney(BigDecimal money) {
            this.money = money;
            return this;
        }

        /**
         * Sets photo.
         *
         * @param photo the photo
         * @return the photo
         */
        public UserBuilder setPhoto(String photo) {
            this.photo = photo;
            return this;
        }

        /**
         * Sets course.
         *
         * @param course the course
         * @return the course
         */
        public UserBuilder setCourse(Course course) {
            this.course = course;
            return this;
        }

        /**
         * Build user.
         *
         * @return the user
         */
        public User build() {
            return new User(id, email, name, surname, role, enabled, money, photo, course);
        }
    }
}
