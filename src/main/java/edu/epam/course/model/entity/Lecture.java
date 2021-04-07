package edu.epam.course.model.entity;

/**
 * The type Lecture.
 */
public class Lecture extends Entity {
    private String lecture;
    private Course course;

    /**
     * Instantiates a new Lecture.
     */
    public Lecture() {
    }

    /**
     * Gets lecture.
     *
     * @return the lecture
     */
    public String getLecture() {
        return lecture;
    }

    /**
     * Sets lecture.
     *
     * @param lecture the lecture
     */
    public void setLecture(String lecture) {
        this.lecture = lecture;
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

        Lecture lecture1 = (Lecture) o;

        if (lecture != null ? !lecture.equals(lecture1.lecture) : lecture1.lecture != null) return false;
        return course != null ? course.equals(lecture1.course) : lecture1.course == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (lecture != null ? lecture.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lecture{");
        sb.append("id=").append(getId());
        sb.append(", lecture='").append(lecture).append('\'');
        sb.append(", course=").append(course);
        sb.append('}');
        return sb.toString();
    }
}
