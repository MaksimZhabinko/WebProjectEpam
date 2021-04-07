package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.RoleType;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.ReviewService;
import edu.epam.course.util.IdUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Review delete command.
 */
public class ReviewDeleteCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ReviewDeleteCommand.class);
    private ReviewService reviewService;

    /**
     * Instantiates a new Review delete command.
     *
     * @param reviewService the review service
     */
    public ReviewDeleteCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String reviewIdString = request.getParameter(RequestParameter.REVIEW_ID);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        Router router = new Router();
        try {
            Long reviewId = IdUtil.stringToLong(reviewIdString);
            if (user.getRole() == RoleType.ADMIN) {
                reviewService.deleteReview(reviewId);
                router.setPagePath(PagePath.REVIEW.getServletPath());
                router.setType(Router.Type.REDIRECT);
            } else {
                boolean isHave = reviewService.isHaveReviewUserById(reviewId, user.getId());
                if (isHave) {
                    reviewService.deleteReview(reviewId);
                    router.setPagePath(PagePath.REVIEW.getServletPath());
                    router.setType(Router.Type.REDIRECT);
                } else {
                    session.setAttribute(SessionAttribute.ERROR_DELETE_NOT_YOUR_REVIEW, true);
                    router.setPagePath(PagePath.REVIEW.getServletPath());
                    router.setType(Router.Type.REDIRECT);
                }
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
