package edu.epam.course.model.dao.impl;

import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.UserDao;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.Entity;
import edu.epam.course.model.entity.RoleType;
import edu.epam.course.model.entity.User;
import edu.epam.course.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * The type User dao.
 */
public class UserDaoImpl implements UserDao {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    /**
     * The constant find user by email.
     */
    private static final String FIND_USER_BY_EMAIL = "SELECT user_id, email, name, surname, role, enabled, money FROM course.users WHERE email = ?";
    /**
     * The constant find user by email and password.
     */
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT user_id, email, name, surname, role, enabled, money, photo FROM course.users WHERE email = ? AND password = ?";
    /**
     * The constant find user by id.
     */
    private static final String FIND_USER_BY_ID = "SELECT user_id, email, name, surname, role, enabled, money FROM course.users WHERE user_id = ?";
    /**
     * The constant add user.
     */
    private static final String ADD_USER = "INSERT INTO `users` (`email`, `name`, `surname`, `password`, `role`, `enabled`) VALUES (?, ?, ?, ?, ?, ?)";
    /**
     * The constant update user password.
     */
    private static final String UPDATE_USER_PASSWORD = "UPDATE course.users SET password = ? WHERE user_id = ?";
    /**
     * The constant update user balance.
     */
    private static final String UPDATE_USER_BALANCE = "UPDATE course.users SET money = ? WHERE user_id = ?";
    /**
     * The constant find all user limit.
     */
    private static final String FIND_ALL_USERS_LIMIT = "SELECT user_id, email, name, surname, role, enabled, money, photo FROM course.users LIMIT ?, ?";
    /**
     * The constant enroll course.
     */
    private static final String ENROLL_COURSE = "INSERT INTO `users_x_courses` (`fk_user_id`, `fk_course_id`) VALUES (?, ?)";
    /**
     * The constant user enroll course.
     */
    private static final String USER_ENROLL_COURSE = "SELECT fk_user_id, fk_course_id FROM course.users_x_courses WHERE fk_user_id = ? AND fk_course_id = ?";
    /**
     * The constant update user photo.
     */
    private static final String UPDATE_USER_PHOTO = "UPDATE course.users SET photo = ? WHERE user_id = ?";
    /**
     * The constant block user.
     */
    private static final String BLOCK_USER = "UPDATE course.users SET enabled = false WHERE user_id = ?";
    /**
     * The constant unblock user.
     */
    private static final String UNBLOCK_USER = "UPDATE course.users SET enabled = true WHERE user_id = ?";
    /**
     * The constant find max user id.
     */
    private static final String FIND_MAX_USER_ID = "SELECT MAX(user_id) FROM users";
    /**
     * The constant update user role.
     */
    private static final String UPDATE_USER_ROLE = "UPDATE course.users SET role = ? WHERE user_id = ?";
    /**
     * The constant update user name and surname.
     */
    private static final String UPDATE_USER_NAME_AND_SURNAME = "UPDATE course.users SET name = ?, surname = ? WHERE user_id = ?";
    /**
     * The constant find all users enrolled course limit.
     */
    private static final String FIND_ALL_USERS_ENROLLED_COURSE = "SELECT user_id, email, name, surname, course_id, course_name FROM course.users_x_courses INNER JOIN course.users ON fk_user_id = user_id INNER JOIN course.courses ON fk_course_id = course_id";

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(1))
                        .setEmail(resultSet.getString(2))
                        .setName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()))
                        .setEnabled(resultSet.getBoolean(6))
                        .setMoney(resultSet.getBigDecimal(7))
                        .setPhoto(resultSet.getString(8))
                        .build();
                userOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return userOptional;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(1))
                        .setEmail(resultSet.getString(2))
                        .setName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()))
                        .setEnabled(resultSet.getBoolean(6))
                        .setMoney(resultSet.getBigDecimal(7))
                        .build();
                userOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return userOptional;
    }

    @Override
    public Long findMaxUserId() throws DaoException {
        Long id;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_MAX_USER_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return id;
    }

    @Override
    public List<User> findAllLimit(int start, int limit) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS_LIMIT)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(1))
                        .setEmail(resultSet.getString(2))
                        .setName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()))
                        .setEnabled(resultSet.getBoolean(6))
                        .setMoney(resultSet.getBigDecimal(7))
                        .setPhoto(resultSet.getString(8))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(1))
                        .setEmail(resultSet.getString(2))
                        .setName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()))
                        .setEnabled(resultSet.getBoolean(6))
                        .setMoney(resultSet.getBigDecimal(7))
                        .build();
                userOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return userOptional;
    }

    @Override
    public boolean addUser(User user, String password) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, user.getRole().toString());
            preparedStatement.setBoolean(6, user.isEnabled());

            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isAdd;
    }

    @Override
    public boolean updatePassword(String password, Long userId) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, userId);

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateBalance(BigDecimal money, Long userId) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BALANCE)) {
            preparedStatement.setBigDecimal(1, money);
            preparedStatement.setLong(2, userId);

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean enrollCourse(User user, Long courseId) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ENROLL_COURSE)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, courseId);

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean isHaveCourse(Long userId, Long courseId) throws DaoException {
        boolean isHave = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_ENROLL_COURSE)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, courseId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isHave = true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isHave;
    }

    @Override
    public boolean updatePhoto(User user) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PHOTO)) {
            preparedStatement.setString(1, user.getPhoto());
            preparedStatement.setLong(2, user.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean blockUser(List<Long> usersId) throws DaoException {
        boolean isUpdate;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(BLOCK_USER);
            for (Long userId : usersId) {
                preparedStatement.setLong(1, userId);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            isUpdate = true;
        } catch (SQLException e) {
            rollback(connection);
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isUpdate;
    }

    @Override
    public boolean unblockUser(List<Long> usersId) throws DaoException {
        boolean isUpdate;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UNBLOCK_USER);
            for (Long userId : usersId) {
                preparedStatement.setLong(1, userId);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            isUpdate = true;
        } catch (SQLException e) {
            rollback(connection);
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isUpdate;
    }

    @Override
    public boolean updateUserToAdmin(User user) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE)) {
            preparedStatement.setString(1, String.valueOf(user.getRole()));
            preparedStatement.setLong(2, user.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateNameAndSurname(User user) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_NAME_AND_SURNAME)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setLong(3, user.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public List<User> findAllEnrolledCourse() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS_ENROLLED_COURSE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = Course.builder()
                        .setId(resultSet.getLong(5))
                        .setName(resultSet.getString(6))
                        .build();
                User user = User.builder()
                        .setId(resultSet.getLong(1))
                        .setEmail(resultSet.getString(2))
                        .setName(resultSet.getString(3))
                        .setSurname(resultSet.getString(4))
                        .setCourse(course)
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public boolean add(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }
}
