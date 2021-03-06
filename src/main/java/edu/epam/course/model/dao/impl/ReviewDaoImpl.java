package edu.epam.course.model.dao.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.ReviewDao;
import edu.epam.course.model.entity.Review;
import edu.epam.course.model.entity.RoleType;
import edu.epam.course.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Review dao.
 */
public class ReviewDaoImpl implements ReviewDao {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ReviewDaoImpl.class);
    /**
     * The constant find all review.
     */
    private static final String FIND_ALL_REVIEW = "SELECT review_id, message, date_message, user_id, email, name, surname, role, enabled FROM course.reviews INNER JOIN users ON fk_reviews_x_user_id = user_id ORDER BY date_message";
    /**
     * The constant add review.
     */
    private static final String ADD_REVIEW = "INSERT INTO `reviews` (`message`, `date_message`, `fk_reviews_x_user_id`) VALUES (?, ?, ?);";
    /**
     * The constant delete review.
     */
    private static final String DELETE_REVIEW = "DELETE FROM course.reviews WHERE review_id = ?";
    /**
     * The constant user has review.
     */
    private static final String USER_HAVE_REVIEW = "SELECT * FROM course.reviews WHERE review_id = ? AND fk_reviews_x_user_id = ?";

    @Override
    public List<Review> findAll() throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_REVIEW)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .setId(resultSet.getLong(4))
                        .setEmail(resultSet.getString(5))
                        .setName(resultSet.getString(6))
                        .setSurname(resultSet.getString(7))
                        .setRole(RoleType.valueOf(resultSet.getString(8).toUpperCase()))
                        .setEnabled(resultSet.getBoolean(9))
                        .build();
                Review review = Review.builder()
                        .setId(resultSet.getLong(1))
                        .setMessage(resultSet.getString(2))
                        .setDateMessage(resultSet.getDate(3).toLocalDate())
                        .setUser(user)
                        .build();
                reviews.add(review);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return reviews;
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

    @Override
    public boolean isHaveReviewByUserId(Long reviewId, Long userId) throws DaoException {
        boolean isHave = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_HAVE_REVIEW)) {
            preparedStatement.setLong(1, reviewId);
            preparedStatement.setLong(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                isHave = true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isHave;
    }

    @Override
    public Optional<Review> findById(Long id) {
        throw new UnsupportedOperationException();
    }
}
