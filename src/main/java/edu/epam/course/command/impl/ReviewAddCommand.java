package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Review;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.ReviewService;
import edu.epam.course.validator.MessageValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 * The type Review add command.
 */
public class ReviewAddCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ReviewAddCommand.class);
    private ReviewService reviewService;

    /**
     * Instantiates a new Review add command.
     *
     * @param reviewService the review service
     */
    public ReviewAddCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String message = request.getParameter(RequestParameter.MESSAGE).trim();
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(RequestAttribute.USER);
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!MessageValidator.isValidMessage(message)) {
                session.setAttribute(SessionAttribute.ERROR_MESSAGE, true);
                dataCorrect = false;
            }
            if (dataCorrect) {
                Review review = new Review();
                User user = new User();
                user.setId(userSession.getId());
                review.setUser(user);
                review.setMessage(message);
                review.setDateMessage(LocalDate.now());
                reviewService.addReview(review);
            }
            router.setType(Router.Type.REDIRECT);
            router.setPagePath(PagePath.REVIEW.getServletPath());
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
