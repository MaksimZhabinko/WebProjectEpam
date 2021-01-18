package edu.epam.project.dao;

import edu.epam.project.entity.Entity;
import edu.epam.project.exception.DaoException;

import java.util.List;

public interface BaseDao <K, T extends Entity>{
    List<T> findAll();
    T findEntityById(K id);
    boolean add(T t, String password) throws DaoException;
    boolean deleteById(K id);
    T update(T t);
}
