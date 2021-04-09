package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Teacher;

import java.util.List;
import java.util.Optional;

/**
 * The interface teacher service.
 */
public interface TeacherService {
    /**
     * Create teacher.
     *
     * @param name    the name
     * @param surname the surname
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean add(String name, String surname) throws ServiceException;

    /**
     * Find all teachers.
     *
     * @return teh list
     * @throws ServiceException the service exception
     */
    List<Teacher> findAll() throws ServiceException;

    /**
     * Delete teacher.
     *
     * @param teachersId the teacher id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteTeachers(List<Long> teachersId) throws ServiceException;

    /**
     * Update teacher photo.
     *
     * @param teacher the teacher
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePhoto(Teacher teacher) throws ServiceException;

    /**
     * Find teacher by name and surname.
     *
     * @param name    the name
     * @param surname the surname
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Teacher> findByNameAndSurname(String name, String surname) throws ServiceException;
}
