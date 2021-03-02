package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Review;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.ReviewService;
import edu.epam.course.validator.ValidMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class ReviewAddCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ReviewAddCommand.class);
    private ReviewService reviewService;

    public ReviewAddCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String message = request.getParameter(RequestParameter.MESSAGE);
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(RequestAttribute.USER);
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!ValidMessage.isValidMessage(message)) {
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, true);
                router.setPagePath(PagePath.REVIEW.getServletPath());
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
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.REVIEW.getServletPath());
            }
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
