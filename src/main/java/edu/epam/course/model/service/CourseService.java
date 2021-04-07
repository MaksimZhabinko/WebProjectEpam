package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;

import java.util.List;
import java.util.Optional;

/**
 * The interface course service.
 */
public interface CourseService {
    /**
     * Find all course.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Course> findAll() throws ServiceException;

    /**
     * Create course.
     *
     * @param course the course
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addCourse(Course course) throws ServiceException;

    /**
     * Delete course.
     *
     * @param id the course id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteCourse(Long id) throws ServiceException;

    /**
     * Find course by id
     *
     * @param id the course id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Course> findCourseById(Long id) throws ServiceException;

    /**
     * Find user enrolled by course.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Course> findUserEnrolledByCourse(Long userId) throws ServiceException;
}
