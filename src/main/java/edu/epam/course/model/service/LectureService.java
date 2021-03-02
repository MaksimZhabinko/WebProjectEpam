package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Lecture;

import java.util.List;

public interface LectureService {
    List<Lecture> findAllLectureByCourseId(Long id) throws ServiceException;
    boolean addLecture(Lecture lecture) throws ServiceException;
    boolean deleteLecture(Long id) throws ServiceException;
    boolean updateLecture(Lecture lecture) throws ServiceException;
}
