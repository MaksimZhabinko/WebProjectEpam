package edu.epam.course.model.entity;

/**
 * The type course.
 */
public class Course extends Entity {
    private String name;
    private Boolean isEnrollmentActive;

    /**
     * Instantiates a new Course.
     */
    public Course() {
    }

    /**
     * Instantiates a new Course.
     *
     * @param id                 the id
     * @param name               the name
     * @param isEnrollmentActive the is enrollment active
     */
    public Course(Long id, String name, Boolean isEnrollmentActive) {
        super(id);
        this.name = name;
        this.isEnrollmentActive = isEnrollmentActive;
    }

    /**
     * Builder course builder.
     *
     * @return the course builder
     */
    public static CourseBuilder builder() {
        return new CourseBuilder();
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
     * Gets enrollment active.
     *
     * @return enrollment active
     */
    public Boolean getEnrollmentActive() {
        return isEnrollmentActive;
    }

    /**
     * Sets enrollment active.
     *
     * @param enrollmentActive the enrollment active
     */
    public void setEnrollmentActive(Boolean enrollmentActive) {
        isEnrollmentActive = enrollmentActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Course course = (Course) o;

        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        return isEnrollmentActive != null ? isEnrollmentActive.equals(course.isEnrollmentActive) : course.isEnrollmentActive == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isEnrollmentActive != null ? isEnrollmentActive.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("id=").append(getId());
        sb.append(", name='").append(name).append('\'');
        sb.append(", isEnrollmentActive=").append(isEnrollmentActive);
        sb.append('}');
        return sb.toString();
    }

    /**
     * The type Course builder.
     */
    public static class CourseBuilder {
        private Long id;
        private String name;
        private Boolean isEnrollmentActive;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public CourseBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public CourseBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets enrollment active.
         *
         * @param enrollmentActive the enrollment active
         * @return the enrollment active
         */
        public CourseBuilder setEnrollmentActive(Boolean enrollmentActive) {
            isEnrollmentActive = enrollmentActive;
            return this;
        }

        /**
         * Build course.
         *
         * @return the course
         */
        public Course build() {
            return new Course(id, name, isEnrollmentActive);
        }
    }
}
