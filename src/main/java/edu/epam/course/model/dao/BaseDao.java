package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface BaseDao <K, T extends Entity>{
    List<T> findAll() throws DaoException;
    Optional<T> findEntityById(K id) throws DaoException;
    boolean add(T t) throws DaoException;
    boolean deleteById(K id) throws DaoException;
}
