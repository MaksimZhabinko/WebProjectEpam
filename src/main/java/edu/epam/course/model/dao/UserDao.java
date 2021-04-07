package edu.epam.course.model.dao;

import edu.epam.course.model.entity.User;
import edu.epam.course.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface user dao.
 */
public interface UserDao extends BaseDao<Long, User> {
    /**
     * Find max user id.
     *
     * @return the long
     * @throws DaoException the dao exception
     */
    Long findMaxUserId() throws DaoException;

    /**
     * Find all user by limit.
     *
     * @param start the start counting
     * @param limit the limit
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllLimit(int start, int limit) throws DaoException;

    /**
     * Find user by email and password.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * Find user by email.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Create user.
     *
     * @param user     the user
     * @param password the password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean addUser(User user, String password) throws DaoException;

    /**
     * Update user password.
     *
     * @param password the password
     * @param userId   the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserPassword(String password, Long userId) throws DaoException;

    /**
     * Update user balance.
     *
     * @param money  the money
     * @param userId the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserBalance(BigDecimal money, Long userId) throws DaoException;

    /**
     * Enroll course.
     *
     * @param user     the user
     * @param courseId the course id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean enrollCourse(User user, Long courseId) throws DaoException;

    /**
     * Check does the user have a course.
     *
     * @param userId   the user id
     * @param courseId the course id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean userHaveCourse(Long userId, Long courseId) throws DaoException;

    /**
     * Update user photo.
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserPhoto(User user) throws DaoException;

    /**
     * Block user.
     *
     * @param usersId the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean blockUser(List<Long> usersId) throws DaoException;

    /**
     * Unblock user.
     *
     * @param usersId the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean unblockUser(List<Long> usersId) throws DaoException;
}
