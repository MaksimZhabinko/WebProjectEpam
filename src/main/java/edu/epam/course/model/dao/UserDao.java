package edu.epam.course.model.dao;

import edu.epam.course.model.entity.User;
import edu.epam.course.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;
    Optional<User> findByEmail(String email) throws DaoException;
    boolean addUser(User user, String password) throws DaoException;
    boolean updateUserPassword(String password, Long userId) throws DaoException;
    boolean updateUserBalance(BigDecimal money, Long userId) throws DaoException;
    boolean enrollCourse(User user, Long courseId) throws DaoException;
    boolean userHaveCourse(Long userId, Long courseId) throws DaoException;
    boolean updateUserPhoto(User user) throws DaoException;
    boolean blockUser(List<Long> usersId) throws DaoException;
    boolean unblockUser(List<Long> usersId) throws DaoException;
}
