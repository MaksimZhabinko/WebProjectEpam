package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll() throws ServiceException;
    boolean addCourse(Course course) throws ServiceException;
    boolean deleteCourse(Long id) throws ServiceException;
    Optional<Course> findCourseById(Long id) throws ServiceException;
    List<Course> findUserEnrolledByCourse(Long userId) throws ServiceException;
}
