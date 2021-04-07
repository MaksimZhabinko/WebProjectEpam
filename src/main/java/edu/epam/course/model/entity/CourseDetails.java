package edu.epam.course.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type Course details.
 */
public class CourseDetails extends Entity {
    private Integer hours;
    private String description;
    private LocalDate startCourse;
    private LocalDate endCourse;
    private LocalTime startOfClass;
    private BigDecimal cost;
    private Course course;
    private Teacher teacher;

    /**
     * Instantiates a new Course details.
     */
    public CourseDetails() {
    }

    /**
     * Gets hours.
     *
     * @return the hours
     */
    public Integer getHours() {
        return hours;
    }

    /**
     * Sets hours.
     *
     * @param hours the hours
     */
    public void setHours(Integer hours) {
        this.hours = hours;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets start course.
     *
     * @return the start course
     */
    public LocalDate getStartCourse() {
        return startCourse;
    }

    /**
     * Sets start course.
     *
     * @param startCourse the start course
     */
    public void setStartCourse(LocalDate startCourse) {
        this.startCourse = startCourse;
    }

    /**
     * Gets end course.
     *
     * @return the end course
     */
    public LocalDate getEndCourse() {
        return endCourse;
    }

    /**
     * Sets end course.
     *
     * @param endCourse the end course
     */
    public void setEndCourse(LocalDate endCourse) {
        this.endCourse = endCourse;
    }

    /**
     * Gets start of class.
     *
     * @return the start of class
     */
    public LocalTime getStartOfClass() {
        return startOfClass;
    }

    /**
     * Sets start of class.
     *
     * @param startOfClass the start of class
     */
    public void setStartOfClass(LocalTime startOfClass) {
        this.startOfClass = startOfClass;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
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

    /**
     * Gets teacher.
     *
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Sets teacher.
     *
     * @param teacher the teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CourseDetails that = (CourseDetails) o;

        if (hours != null ? !hours.equals(that.hours) : that.hours != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (startCourse != null ? !startCourse.equals(that.startCourse) : that.startCourse != null) return false;
        if (endCourse != null ? !endCourse.equals(that.endCourse) : that.endCourse != null) return false;
        if (startOfClass != null ? !startOfClass.equals(that.startOfClass) : that.startOfClass != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        if (course != null ? !course.equals(that.course) : that.course != null) return false;
        return teacher != null ? teacher.equals(that.teacher) : that.teacher == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startCourse != null ? startCourse.hashCode() : 0);
        result = 31 * result + (endCourse != null ? endCourse.hashCode() : 0);
        result = 31 * result + (startOfClass != null ? startOfClass.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CourseDetails{");
        sb.append("id=").append(getId());
        sb.append(", hours=").append(hours);
        sb.append(", description='").append(description).append('\'');
        sb.append(", startCourse=").append(startCourse);
        sb.append(", endCourse=").append(endCourse);
        sb.append(", startOfClass=").append(startOfClass);
        sb.append(", cost=").append(cost);
        sb.append(", course=").append(course);
        sb.append(", teacher=").append(teacher);
        sb.append('}');
        return sb.toString();
    }
}
