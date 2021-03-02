package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.Lecture;

import java.util.List;

public interface LectureDao extends BaseDao<Long, Lecture> {
    List<Lecture> findAllByCourseId(Long id) throws DaoException;
    boolean updateLecture(Lecture lecture) throws DaoException;
}
