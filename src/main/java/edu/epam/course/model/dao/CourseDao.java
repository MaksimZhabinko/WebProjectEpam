package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.Course;

import java.util.List;

public interface CourseDao extends BaseDao<Long, Course> {
    List<Course> findUserEnrolledByCourse(Long userId) throws DaoException;
}
