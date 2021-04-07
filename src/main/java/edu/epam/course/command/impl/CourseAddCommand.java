package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.service.CourseService;
import edu.epam.course.validator.CourseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Course add command.
 */
public class CourseAddCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseAddCommand.class);
    private CourseService courseService;

    /**
     * Instantiates a new Course add command.
     *
     * @param courseService the course service
     */
    public CourseAddCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseName = request.getParameter(RequestParameter.COURSE_NAME);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!CourseValidator.isValidName(courseName)) {
             session.setAttribute(SessionAttribute.ERROR_IS_NOT_VALID_COURSE_NAME, true);
             dataCorrect = false;
            }
            if (dataCorrect) {
                Course course = new Course();
                course.setName(courseName);
                course.setEnrollmentActive(true);
                courseService.addCourse(course);
            }
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
