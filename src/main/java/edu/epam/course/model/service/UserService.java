package edu.epam.course.model.service;

import edu.epam.course.model.entity.User;
import edu.epam.course.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(long id) throws ServiceException;
    Optional<User> findUserByEmail(String email) throws ServiceException;
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;
    boolean addUser(User user, String password) throws ServiceException;
    boolean forgotUserPassword(User user) throws ServiceException;
    User updateUserBalance(String money, User user) throws ServiceException;
}
