package edu.epam.course.model.dao;

import edu.epam.course.model.entity.User;
import edu.epam.course.exception.DaoException;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    boolean addUser(User user, String password) throws DaoException;
    boolean updateUserPassword(String password, Long userId) throws DaoException;
    boolean updateUserBalance(BigDecimal money, Long userId) throws DaoException;
}
