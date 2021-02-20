package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll() throws ServiceException;
    boolean addCourse(Course course) throws ServiceException;
    boolean deleteCourse(Long id) throws ServiceException;
}
