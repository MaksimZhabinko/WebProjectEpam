package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Review;
import edu.epam.course.model.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Open reviews command.
 */
public class OpenReviewsCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(OpenReviewsCommand.class);
    private ReviewService reviewService;

    /**
     * Instantiates a new Open reviews command.
     *
     * @param reviewService the review service
     */
    public OpenReviewsCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            List<Review> reviews = reviewService.findAllReview();
            request.setAttribute(RequestAttribute.REVIEWS, reviews);
            router.setPagePath(PagePath.REVIEW.getDirectUrl());
            session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.REVIEW.getServletPath());
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
