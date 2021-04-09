package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.CourseService;
import edu.epam.course.util.IdUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Course delete command.
 */
public class CourseDeleteCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseDeleteCommand.class);
    private CourseService courseService;

    /**
     * Instantiates a new Course delete command.
     *
     * @param courseService the course service
     */
    public CourseDeleteCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        Router router = new Router();
        try {
            Long courseId = IdUtil.stringToLong(courseIdString);
            courseService.delete(courseId);
            router.setType(Router.Type.REDIRECT);
            router.setPagePath(PagePath.MAIN.getServletPath());
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
