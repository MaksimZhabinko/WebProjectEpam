package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Lecture;

import java.util.List;
import java.util.Optional;

/**
 * The interface lecture service.
 */
public interface LectureService {
    /**
     * Find all lecture by course id.
     *
     * @param courseId the course id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Lecture> findAllLectureByCourseId(Long courseId) throws ServiceException;

    /**
     * Create lecture.
     *
     * @param lecture the lecture
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean add(Lecture lecture) throws ServiceException;

    /**
     * Delete lecture.
     *
     * @param lectureId the lecture id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean delete(Long lectureId) throws ServiceException;

    /**
     * Update lecture.
     *
     * @param lecture the lecture
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean update(Lecture lecture) throws ServiceException;

    /**
     * Find lecture by id optional.
     *
     * @param lectureId the lecture id
     * @param courseId  the course id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Lecture> findLectureByIdAndCourseId(Long lectureId, Long courseId) throws ServiceException;
}
