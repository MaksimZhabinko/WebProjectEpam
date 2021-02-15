package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.Lecture;

import java.util.List;

public interface LectureDao extends BaseDao<Long, Lecture> {
    List<Lecture> findAllById(Long id) throws DaoException;
}
