package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Review;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.ReviewService;
import edu.epam.course.validator.ReviewValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

public class AddReviewCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddReviewCommand.class);
    private ReviewService reviewService;

    public AddReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String message = request.getParameter(RequestParameter.MESSAGE);
        String idUser = request.getParameter(RequestParameter.USER_ID);
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!ReviewValidator.isValidMessage(message)) {
                request.setAttribute(RequestAttribute.ERROR_REVIEW_MESSAGE, true);
                router.setPagePath(PagePath.REVIEW.getServletPath());
                dataCorrect = false;
            }
            if (dataCorrect) {
                Review review = new Review();
                User user = new User();
                user.setId(Long.parseLong(idUser));
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
