package edu.epam.course.model.dao.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.ReviewDao;
import edu.epam.course.model.entity.Review;
import edu.epam.course.model.entity.RoleType;
import edu.epam.course.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDaoImpl implements ReviewDao {
    private static final Logger logger = LogManager.getLogger(ReviewDaoImpl.class);
    private static final String FIND_ALL_REVIEW = "SELECT review_id, message, date_message, user_id, email, name, surname, role, enabled FROM course.reviews INNER JOIN users ON fk_reviews_x_user_id = user_id ORDER BY date_message";
    private static final String ADD_REVIEW = "INSERT INTO `reviews` (`message`, `date_message`, `fk_reviews_x_user_id`) VALUES (?, ?, ?);";
    private static final String DELETE_REVIEW = "DELETE FROM course.reviews WHERE review_id = ?";

    @Override
    public List<Review> findAll() throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_REVIEW)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review();
                User user = new User();
                review.setId(resultSet.getLong(1));
                review.setMessage(resultSet.getString(2));
                review.setDateMessage(resultSet.getDate(3).toLocalDate());
                user.setId(resultSet.getLong(4));
                user.setEmail(resultSet.getString(5));
                user.setName(resultSet.getString(6));
                user.setSurname(resultSet.getString(7));
                user.setRole(RoleType.valueOf(resultSet.getString(8).toUpperCase()));
                user.setEnabled(resultSet.getBoolean(9));
                review.setUser(user);
                reviews.add(review);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return reviews;
    }

    @Override
    public Optional<Review> findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean add(Review review) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_REVIEW)) {
            preparedStatement.setString(1, review.getMessage());
            preparedStatement.setDate(2, Date.valueOf(review.getDateMessage()));
            preparedStatement.setLong(3, review.getUser().getId());

            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isAdd;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        boolean isDelete;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVIEW)) {
            preparedStatement.setLong(1, id);

            isDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isDelete;
    }
}
