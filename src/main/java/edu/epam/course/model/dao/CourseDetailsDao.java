package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.CourseDetails;

import java.util.Optional;

/**
 * The interface course details dao.
 */
public interface CourseDetailsDao extends BaseDao<Long, CourseDetails> {
    /**
     * Find course details by course id.
     *
     * @param courseId the course id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<CourseDetails> findCourseDetailsByCourseId(Long courseId) throws DaoException;

    /**
     * Find out if there are course details in the course.
     *
     * @param courseId the course id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isCourseHaveDetails(Long courseId) throws DaoException;

    /**
     * Update description.
     *
     * @param courseDetails the course details
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateDescription(CourseDetails courseDetails) throws DaoException;

    /**
     * Update hours.
     *
     * @param courseDetails the course details
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateHours(CourseDetails courseDetails) throws DaoException;

    /**
     * Update start of class.
     *
     * @param courseDetails the course details
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateStartOfClass(CourseDetails courseDetails) throws DaoException;

    /**
     * Update cost.
     *
     * @param courseDetails the course details
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateCost(CourseDetails courseDetails) throws DaoException;

    /**
     * Update teacher boolean.
     *
     * @param courseDetails the course details
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateTeacher(CourseDetails courseDetails) throws DaoException;
}
