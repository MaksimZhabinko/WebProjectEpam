package edu.epam.course.model.entity;

public class Lecture extends Entity {
    private Long id;
    private String lecture;
    private Course course;

    public Lecture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecture lecture1 = (Lecture) o;

        if (id != null ? !id.equals(lecture1.id) : lecture1.id != null) return false;
        if (lecture != null ? !lecture.equals(lecture1.lecture) : lecture1.lecture != null) return false;
        return course != null ? course.equals(lecture1.course) : lecture1.course == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lecture != null ? lecture.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lecture{");
        sb.append("id=").append(id);
        sb.append(", lecture='").append(lecture).append('\'');
        sb.append(", course=").append(course);
        sb.append('}');
        return sb.toString();
    }
}
