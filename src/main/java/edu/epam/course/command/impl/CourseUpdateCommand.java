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
import edu.epam.course.util.IdUtil;
import edu.epam.course.validator.CourseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The type Course update command.
 */
public class CourseUpdateCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseUpdateCommand.class);
    private CourseService courseService;

    /**
     * Instantiates a new Course update command.
     *
     * @param courseService the course service
     */
    public CourseUpdateCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String courseName = request.getParameter(RequestParameter.COURSE_NAME);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Long courseId = IdUtil.stringToLong(courseIdString);
            if (!CourseValidator.isValidName(courseName)) {
                session.setAttribute(SessionAttribute.ERROR_IS_NOT_VALID_COURSE_NAME, true);
                dataCorrect = false;
            }
            if (dataCorrect) {
                Optional<Course> courseOptional = courseService.findById(courseId);
                if (!courseOptional.isPresent()) {
                    session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                    dataCorrect = false;
                }
            }
            if (dataCorrect) {
                Course course = Course.builder()
                        .setId(courseId)
                        .setName(courseName)
                        .build();
                courseService.updateCourseName(course);
            }
            router.setType(Router.Type.REDIRECT);
            router.setPagePath(PagePath.MAIN.getServletPath());
        }  catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
