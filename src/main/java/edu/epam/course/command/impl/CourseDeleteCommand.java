package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.CourseService;
import edu.epam.course.validator.IdValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CourseDeleteCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CourseDeleteCommand.class);
    private CourseService courseService;

    public CourseDeleteCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseId = request.getParameter(RequestParameter.COURSE_ID);
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!IdValidator.isValidId(courseId)) {
                router.setPagePath(PagePath.ERROR_404.getDirectUrl()); // todo куда кидать если courseId будет строкой а не число
                dataCorrect = false;
            }
            if (dataCorrect) {
                courseService.deleteCourse(Long.valueOf(courseId));
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.MAIN.getServletPath());
            }
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
