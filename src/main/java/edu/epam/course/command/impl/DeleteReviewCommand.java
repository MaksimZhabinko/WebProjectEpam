package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteReviewCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteReviewCommand.class);
    private ReviewService reviewService;

    public DeleteReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String reviewId = request.getParameter(RequestParameter.REVIEW_ID);
        Router router = new Router();
        try {
            // todo сделать провекру что этот ид не был удален до того как человек удалил
            reviewService.deleteReview(Long.valueOf(reviewId));
            router.setPagePath(PagePath.REVIEW.getServletPath());
            router.setType(Router.Type.REDIRECT);
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }

        return router;
    }
}
