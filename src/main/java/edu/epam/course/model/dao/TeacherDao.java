package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.Teacher;

import java.util.List;
import java.util.Optional;


/**
 * The interface teacher dao.
 */
public interface TeacherDao extends BaseDao<Long, Teacher> {
    /**
     * Delete teachers.
     *
     * @param teachersId the teachers id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteTeachers(List<Long> teachersId) throws DaoException;

    /**
     * Update teacher photo.
     *
     * @param teacher the teacher
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updatePhoto(Teacher teacher) throws DaoException;

    /**
     * Find teacher by name and surname.
     *
     * @param name    the name
     * @param surname the surname
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Teacher> findByNameAndSurname(String name, String surname) throws DaoException;
}
