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
    boolean addTeacher(String name, String surname) throws ServiceException;

    /**
     * Find all teachers.
     *
     * @return teh list
     * @throws ServiceException the service exception
     */
// todo как правильно если так правильно, то должно быть на конце s?
    List<Teacher> findAllTeachers() throws ServiceException;

    /**
     * Delete teacher.
     *
     * @param teachersId the teacher id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteTeacher(List<Long> teachersId) throws ServiceException;

    /**
     * Update teacher photo.
     *
     * @param teacher the teacher
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateTeacherPhoto(Teacher teacher) throws ServiceException;

    /**
     * Find teacher by name and surname.
     *
     * @param name    the name
     * @param surname the surname
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Teacher> findTeacherByNameAndSurname(String name, String surname) throws ServiceException;
}
