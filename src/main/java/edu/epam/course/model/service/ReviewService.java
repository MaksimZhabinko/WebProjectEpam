package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews() throws ServiceException;
    boolean addReview(Review review) throws ServiceException;
    boolean deleteReview(Long id) throws ServiceException;
}
