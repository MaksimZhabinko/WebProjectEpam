package edu.epam.project.model.dao;

import edu.epam.project.model.entity.User;
import edu.epam.project.exception.DaoException;

import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    boolean addUser(User user, String password) throws DaoException;
}
