package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Lecture;

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
    boolean add(Course course) throws ServiceException;

    /**
     * Delete course.
     *
     * @param courseId the course id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean delete(Long courseId) throws ServiceException;

    /**
     * Find course by id
     *
     * @param courseId the course id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Course> findById(Long courseId) throws ServiceException;

    /**
     * Find user enrolled by course.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Course> findUserEnrolledByCourse(Long userId) throws ServiceException;

    /**
     * Update course name boolean.
     *
     * @param course the course
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateCourseName(Course course) throws ServiceException;

    /**
     * Update start and end new course boolean.
     *
     * @param courseDetails the course details
     * @param lectures      the lectures
     * @return the boolean
     * @throws ServiceException the service exception
     */
    Long updateStartAndEndNewCourse(CourseDetails courseDetails, List<Lecture> lectures) throws ServiceException;
}
