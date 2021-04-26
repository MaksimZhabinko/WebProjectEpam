package edu.epam.course.model.entity;

/**
 * The type Teacher.
 */
public class Teacher extends Entity {
    private String name;
    private String surname;
    private String photo;

    /**
     * Instantiates a new Teacher.
     */
    public Teacher() {
    }

    /**
     * Instantiates a new Teacher.
     *
     * @param id      the id
     * @param name    the name
     * @param surname the surname
     * @param photo   the photo
     */
    public Teacher(Long id, String name, String surname, String photo) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.photo = photo;
    }

    /**
     * Builder teacher builder.
     *
     * @return the teacher builder
     */
    public static TeacherBuilder builder() {
        return new TeacherBuilder();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Teacher teacher = (Teacher) o;

        if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;
        if (surname != null ? !surname.equals(teacher.surname) : teacher.surname != null) return false;
        return photo != null ? photo.equals(teacher.photo) : teacher.photo == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Teacher{");
        sb.append("id=").append(getId());
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", photo='").append(photo).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * The type Teacher builder.
     */
    public static class TeacherBuilder {
        private Long id;
        private String name;
        private String surname;
        private String photo;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public TeacherBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public TeacherBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets surname.
         *
         * @param surname the surname
         * @return the surname
         */
        public TeacherBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * Sets photo.
         *
         * @param photo the photo
         * @return the photo
         */
        public TeacherBuilder setPhoto(String photo) {
            this.photo = photo;
            return this;
        }

        /**
         * Build teacher.
         *
         * @return the teacher
         */
        public Teacher build() {
            return new Teacher(id, name, surname, photo);
        }
    }
}
