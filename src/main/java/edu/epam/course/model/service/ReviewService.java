package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Review;

import java.util.List;

/**
 * The interface review service.
 */
public interface ReviewService {
    /**
     * Find all review.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Review> findAll() throws ServiceException;

    /**
     * Create review.
     *
     * @param review the review
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean add(Review review) throws ServiceException;

    /**
     * Delete review.
     *
     * @param reviewId the review id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean delete(Long reviewId) throws ServiceException;

    /**
     * The user has reviews by user id.
     *
     * @param reviewId the review id
     * @param userId   the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isHaveReviewByUserId(Long reviewId, Long userId) throws ServiceException;
}
