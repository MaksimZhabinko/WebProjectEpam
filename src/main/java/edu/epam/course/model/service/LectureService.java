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
     * @param id the course id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Lecture> findAllLectureByCourseId(Long id) throws ServiceException;

    /**
     * Create lecture.
     *
     * @param lecture the lecture
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addLecture(Lecture lecture) throws ServiceException;

    /**
     * Delete lecture.
     *
     * @param id the lecture id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteLecture(Long id) throws ServiceException;

    /**
     * Update lecture.
     *
     * @param lecture the lecture
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateLecture(Lecture lecture) throws ServiceException;

    /**
     * Find lecture by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Lecture> findLectureByIdAndCourseId(Long lectureId, Long courseId) throws ServiceException;
}
