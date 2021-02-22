package edu.epam.course.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class CourseDetails extends Entity {
    private Integer hours;
    private String description;
    private Boolean isTest;
    private LocalDate startCourse;
    private LocalDate endCourse;
    private LocalTime startOfClass;
    private BigDecimal cost;
    private Course course;
    private Teacher teacher;

    public CourseDetails() {
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getTest() {
        return isTest;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }

    public LocalDate getStartCourse() {
        return startCourse;
    }

    public void setStartCourse(LocalDate startCourse) {
        this.startCourse = startCourse;
    }

    public LocalDate getEndCourse() {
        return endCourse;
    }

    public void setEndCourse(LocalDate endCourse) {
        this.endCourse = endCourse;
    }

    public LocalTime getStartOfClass() {
        return startOfClass;
    }

    public void setStartOfClass(LocalTime startOfClass) {
        this.startOfClass = startOfClass;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

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
        if (isTest != null ? !isTest.equals(that.isTest) : that.isTest != null) return false;
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
        result = 31 * result + (isTest != null ? isTest.hashCode() : 0);
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
        sb.append("hours=").append(hours);
        sb.append(", description='").append(description).append('\'');
        sb.append(", isTest=").append(isTest);
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
