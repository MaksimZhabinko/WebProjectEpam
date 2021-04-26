package edu.epam.course.model.entity;

/**
 * The type Entity.
 */
public abstract class Entity {
    private Long id;

    /**
     * Instantiates a new Entity.
     */
    public Entity() {
    }

    /**
     * Instantiates a new Entity.
     *
     * @param id the id
     */
    public Entity(Long id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        return id != null ? id.equals(entity.id) : entity.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
