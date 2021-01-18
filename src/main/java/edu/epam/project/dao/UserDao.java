package edu.epam.project.dao;

import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;

import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
}
