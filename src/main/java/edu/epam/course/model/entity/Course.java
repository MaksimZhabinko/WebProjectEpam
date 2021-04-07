package edu.epam.course.model.entity;

/**
 * The type course.
 */
public class Course extends Entity {
    private String name;
    private Boolean isEnrollmentActive;

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
}
