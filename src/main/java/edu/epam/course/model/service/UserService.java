package edu.epam.course.model.service;

import edu.epam.course.model.entity.User;
import edu.epam.course.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(long id) throws ServiceException;
    Optional<User> findUserByEmail(String email) throws ServiceException;
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;
    boolean addUser(User user, String password) throws ServiceException;
    boolean forgotUserPassword(User user) throws ServiceException;
    boolean updateUserBalance(String money, User user) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    boolean enrollCourse(User user, Long courseId, BigDecimal transaction) throws ServiceException;
    boolean userHaveCourse(Long userId, Long courseId) throws ServiceException;
    boolean updateUserPhoto(User user) throws ServiceException;
    boolean blockUser(List<Long> usersId) throws ServiceException;
    boolean unblockUser(List<Long> usersId) throws ServiceException;
}
