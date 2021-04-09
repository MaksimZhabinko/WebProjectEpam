package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Teacher;

import java.util.Optional;

/**
 * The interface course details service.
 */
public interface CourseDetailsService {
    /**
     * Find course details by id.
     *
     * @param courseDetailsId the course details id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<CourseDetails> findCourseDetailsById(Long courseDetailsId) throws ServiceException;

    /**
     * Find course details by course id.
     *
     * @param courseId the course id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<CourseDetails> findCourseDetailsByCourseId(Long courseId) throws ServiceException;

    /**
     * Find out if there are course details in the course.
     *
     * @param courseId the course id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isCourseHaveDetails(Long courseId) throws ServiceException;

    /**
     * Create course details.
     *
     * @param hours        the hours
     * @param description  the description
     * @param startCourse  the start course
     * @param endCourse    the end course
     * @param startOfClass the start of class
     * @param cost         the cost
     * @param course       the course
     * @param teacher      the teacher
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean add(String hours, String description,
                String startCourse, String endCourse, String startOfClass,
                String cost, Course course, Teacher teacher) throws ServiceException;

    /**
     * Update description.
     *
     * @param courseDetails the course details
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateDescription(CourseDetails courseDetails) throws ServiceException;

    /**
     * Update hours.
     *
     * @param courseDetails the course details
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateHours(CourseDetails courseDetails) throws ServiceException;

    /**
     * Update start of class.
     *
     * @param courseDetails the course details
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateStartOfClass(CourseDetails courseDetails) throws ServiceException;

    /**
     * Update cost.
     *
     * @param courseDetails the course details
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateCost(CourseDetails courseDetails) throws ServiceException;

    /**
     * Update teacher boolean.
     *
     * @param courseDetails the course details
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateTeacher(CourseDetails courseDetails) throws ServiceException;
}
