package edu.epam.course.model.service;

import edu.epam.course.model.entity.User;
import edu.epam.course.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface user service.
 */
public interface UserService {
    /**
     * Find max user id.
     *
     * @return the long
     * @throws ServiceException the service exception
     */
    Long findMaxUserId() throws ServiceException;

    /**
     * Fina user by id.
     *
     * @param id the user id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserById(long id) throws ServiceException;

    /**
     * Find user by email.
     *
     * @param email the email
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByEmail(String email) throws ServiceException;

    /**
     * Find user by email and password.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;

    /**
     * Create user.
     *
     * @param user     the user
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addUser(User user, String password) throws ServiceException;

    /**
     * Forgot user password.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean forgotUserPassword(User user) throws ServiceException;

    /**
     * Update user balance.
     *
     * @param money the money
     * @param user  the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUserBalance(String money, User user) throws ServiceException;

    /**
     * Find all users limit.
     *
     * @param page the page
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllUsersLimit(int page) throws ServiceException;

    /**
     * User enroll course.
     *
     * @param user        the user
     * @param courseId    the course id
     * @param transaction the transaction
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean enrollCourse(User user, Long courseId, BigDecimal transaction) throws ServiceException;

    /**
     * Check does the user have a course.
     *
     * @param userId   the user id
     * @param courseId the course id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean userHaveCourse(Long userId, Long courseId) throws ServiceException;

    /**
     * Update user photo.
     *
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateUserPhoto(User user) throws ServiceException;

    /**
     * Block user.
     *
     * @param usersId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean blockUser(List<Long> usersId) throws ServiceException;

    /**
     * Unblock user.
     *
     * @param usersId the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean unblockUser(List<Long> usersId) throws ServiceException;
}
