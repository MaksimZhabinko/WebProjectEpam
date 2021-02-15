package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.CourseDetails;

import java.util.Optional;

public interface CourseDetailsService {
    Optional<CourseDetails> findCourseDetailsById(Long id) throws ServiceException;
}
