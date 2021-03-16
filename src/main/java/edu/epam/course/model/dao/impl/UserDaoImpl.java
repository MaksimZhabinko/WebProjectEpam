package edu.epam.course.model.dao.impl;

import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.UserDao;
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
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static final String FIND_USER_BY_EMAIL = "SELECT user_id, email, name, surname, role, enabled, money FROM course.users WHERE email = ?";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT user_id, email, name, surname, role, enabled, money, photo FROM course.users WHERE email = ? AND password = ?";
    private static final String FIND_USER_BY_ID = "SELECT user_id, email, name, surname, role, enabled, money FROM course.users WHERE user_id = ?";
    private static final String ADD_USER = "INSERT INTO `users` (`email`, `name`, `surname`, `password`, `role`, `enabled`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_PASSWORD = "UPDATE course.users SET password = ? WHERE user_id = ?";
    private static final String UPDATE_USER_BALANCE = "UPDATE course.users SET money = ? WHERE user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT user_id, email, name, surname, role, enabled, money FROM course.users";
    private static final String ENROLL_COURSE = "INSERT INTO `users_x_courses` (`fk_user_id`, `fk_course_id`) VALUES (?, ?)";
    private static final String USER_ENROLL_THIS_COURSE = "SELECT fk_user_id, fk_course_id FROM course.users_x_courses WHERE fk_user_id = ? AND fk_course_id = ?";
    private static final String UPDATE_USER_PHOTO = "UPDATE course.users SET photo = ? WHERE user_id = ?";
    private static final String BLOCK_USER = "UPDATE course.users SET enabled = false WHERE user_id = ?";

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setEmail(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setSurname(resultSet.getString(4));
                user.setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()));
                user.setEnabled(resultSet.getBoolean(6));
                user.setMoney(resultSet.getBigDecimal(7));
                user.setPhoto(resultSet.getString(8));
                userOptional = Optional.ofNullable(user);
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
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setEmail(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setSurname(resultSet.getString(4));
                user.setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()));
                user.setEnabled(resultSet.getBoolean(6));
                user.setMoney(resultSet.getBigDecimal(7));
                userOptional = Optional.ofNullable(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return userOptional;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setEmail(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setSurname(resultSet.getString(4));
                user.setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()));
                user.setEnabled(resultSet.getBoolean(6));
                user.setMoney(resultSet.getBigDecimal(7));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findEntityById(Long id) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setEmail(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setSurname(resultSet.getString(4));
                user.setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()));
                user.setEnabled(resultSet.getBoolean(6));
                user.setMoney(resultSet.getBigDecimal(7));
                userOptional = Optional.ofNullable(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return userOptional;
    }

    @Override
    public boolean add(User user) {
        throw new UnsupportedOperationException();
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
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean updateUserPassword(String password, Long userId) throws DaoException {
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
    public boolean updateUserBalance(BigDecimal money, Long userId) throws DaoException {
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
    public boolean userHaveCourse(Long userId, Long courseId) throws DaoException {
        boolean isHave = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_ENROLL_THIS_COURSE)) {
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
    public boolean updateUserPhoto(User user) throws DaoException {
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
        } catch (SQLException e) { // todo look
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error("rollback error");
                }
            }
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isUpdate;
    }
}
