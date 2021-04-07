package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.Lecture;

import java.util.List;

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
    List<Lecture> findAllByCourseId(Long id) throws DaoException;

    /**
     * Update lecture.
     *
     * @param lecture the lecture
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateLecture(Lecture lecture) throws DaoException;
}
