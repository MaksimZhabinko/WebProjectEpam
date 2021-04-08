package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Lecture;

import java.util.List;

/**
 * The interface course dao.
 */
public interface CourseDao extends BaseDao<Long, Course> {
    /**
     * Find user enrolled by course.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Course> findUserEnrolledByCourse(Long userId) throws DaoException;

    /**
     * Update course name boolean.
     *
     * @param course the course
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateCourseName(Course course) throws DaoException;


// todo example
    void example(Course course, CourseDetails courseDetails, List<Lecture> lectures);
}
