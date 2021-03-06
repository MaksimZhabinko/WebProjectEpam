package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.ReviewDao;
import edu.epam.course.model.dao.impl.ReviewDaoImpl;
import edu.epam.course.model.entity.Review;
import edu.epam.course.model.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Review service.
 */
public class ReviewServiceImpl implements ReviewService {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ReviewServiceImpl.class);
    private ReviewDao reviewDao = new ReviewDaoImpl();

    @Override
    public List<Review> findAll() throws ServiceException {
        List<Review> reviews;
        try {
            reviews = reviewDao.findAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return reviews;
    }

    @Override
    public boolean add(Review review) throws ServiceException {
        boolean isAdd;
        try {
            isAdd = reviewDao.add(review);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isAdd;
    }

    @Override
    public boolean delete(Long reviewId) throws ServiceException {
        boolean isDelete;
        try {
            isDelete = reviewDao.deleteById(reviewId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isDelete;
    }

    @Override
    public boolean isHaveReviewByUserId(Long reviewId, Long userId) throws ServiceException {
        boolean isHave;
        try {
            isHave = reviewDao.isHaveReviewByUserId(reviewId, userId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isHave;
    }
}
