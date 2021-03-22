package edu.epam.course.model.dao.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.AboutUsDao;
import edu.epam.course.model.entity.AboutUs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AboutUsDaoImpl implements AboutUsDao {
    private static final Logger logger = LogManager.getLogger(AboutUsDaoImpl.class);
    private static final String FIND_ABOUT_US = "SELECT about_us_id, message FROM course.about_us";
    private static final String UPDATE_MESSAGE = "update course.about_us SET message = ? WHERE about_us_id = ?";

    @Override
    public List<AboutUs> findAll() throws DaoException {
        List<AboutUs> aboutUsList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ABOUT_US)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AboutUs aboutUs = new AboutUs();
                aboutUs.setId(resultSet.getLong(1));
                aboutUs.setMessage(resultSet.getString(2));
                aboutUsList.add(aboutUs);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return aboutUsList;
    }

    @Override
    public boolean updateMessage(AboutUs aboutUs) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MESSAGE)) {
            preparedStatement.setString(1, aboutUs.getMessage());
            preparedStatement.setLong(2, aboutUs.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public Optional<AboutUs> findEntityById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(AboutUs aboutUs) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
