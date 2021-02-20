package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CourseAddCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CourseAddCommand.class);
    private CourseService courseService;

    public CourseAddCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseName = request.getParameter(RequestParameter.COURSE_NAME);
        Router router = new Router();
        try {
            Course course = new Course();
            course.setName(courseName);
            courseService.addCourse(course);
            router.setType(Router.Type.REDIRECT);
            router.setPagePath(PagePath.MAIN.getServletPath());
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
