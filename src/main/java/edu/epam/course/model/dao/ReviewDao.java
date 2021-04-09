package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.Review;

/**
 * The interface review dao.
 */
public interface ReviewDao extends BaseDao<Long, Review> {
    /**
     * The user has reviews by user id.
     *
     * @param reviewId the review id
     * @param userId   the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isHaveReviewByUserId(Long reviewId, Long userId) throws DaoException;
}
