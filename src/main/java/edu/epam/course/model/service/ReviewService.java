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
    List<Review> findAllReview() throws ServiceException;

    /**
     * Create review.
     *
     * @param review the review
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addReview(Review review) throws ServiceException;

    /**
     * Delete review.
     *
     * @param id the review id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteReview(Long id) throws ServiceException;

    /**
     * The user has reviews by user id.
     *
     * @param reviewId the review id
     * @param userId   the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isHaveReviewUserById(Long reviewId, Long userId) throws ServiceException;
}
