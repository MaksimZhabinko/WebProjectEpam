package edu.epam.project.model.dao;

import edu.epam.project.model.entity.Entity;

import java.util.List;

public interface BaseDao <K, T extends Entity>{
    List<T> findAll();
    T findEntityById(K id);
    boolean add(T t);
    boolean deleteById(K id);
    T update(T t);
}
