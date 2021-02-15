package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OpenMainCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OpenMainCommand.class);
    private CourseService courseService;

    public OpenMainCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            List<Course> courses = courseService.findAll();
            request.setAttribute(RequestAttribute.COURSES, courses);
            router.setPagePath(PagePath.MAIN.getDirectUrl());
            session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.MAIN.getServletPath());
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
