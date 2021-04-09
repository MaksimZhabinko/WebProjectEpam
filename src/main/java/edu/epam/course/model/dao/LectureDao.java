package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.Lecture;

import java.util.List;
import java.util.Optional;

/**
 * The interface lecture dao.
 */
public interface LectureDao extends BaseDao<Long, Lecture> {
    /**
     * Find all lecture by course id.
     *
     * @param id the id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Lecture> findAllLectureByCourseId(Long id) throws DaoException;

    /**
     * Update lecture.
     *
     * @param lecture the lecture
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean update(Lecture lecture) throws DaoException;

    /**
     * Find lecture by id and course id optional.
     *
     * @param lectureId the lecture id
     * @param courseId  the course id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Lecture> findLectureByIdAndCourseId(Long lectureId, Long courseId) throws DaoException;
}
