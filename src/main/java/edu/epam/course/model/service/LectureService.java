package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Lecture;

import java.util.List;

public interface LectureService {
    List<Lecture> findAllLectureById(Long id) throws ServiceException;
}
