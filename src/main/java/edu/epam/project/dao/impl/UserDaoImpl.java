package edu.epam.project.dao.impl;

import edu.epam.project.connector.CustomConnection;
import edu.epam.project.dao.UserDao;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import edu.epam.project.util.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static final String FIND_USER_BY_EMAIL = "SELECT user_id, email, name, surname, role_name, enabled FROM rename_base.users JOIN rename_base.roles ON fk_role_id = role_id WHERE email = ?";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT user_id, email, name, surname, role_name, enabled FROM rename_base.users JOIN rename_base.roles ON fk_role_id = role_id WHERE email = ? " +
            "AND password = ?";
    private static final String ADD_USER = "INSERT INTO `users` (`email`, `name`, `surname`, `password`, `fk_role_id`, `enabled`) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setSurname(resultSet.getString(4));
                user.setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()));
                user.setEnabled(resultSet.getBoolean(6));
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setSurname(resultSet.getString(4));
                user.setRole(RoleType.valueOf(resultSet.getString(5).toUpperCase()));
                user.setEnabled(resultSet.getBoolean(6));
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean add(User user, String password) throws DaoException {
        boolean update;
        try (Connection connection = CustomConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, password);
            preparedStatement.setInt(5, user.getRole().ordinal() + 1);
            preparedStatement.setBoolean(6, user.isEnabled());

            update = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return update;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
