package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.service.CourseDetailsService;
import edu.epam.course.util.IdUtil;
import edu.epam.course.validator.CourseDetailsValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.Optional;

/**
 * The type Course details start of class update command.
 */
public class CourseDetailsStartOfClassUpdateCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseDetailsStartOfClassUpdateCommand.class);
    private CourseDetailsService courseDetailsService;

    /**
     * Instantiates a new Course details start of class update command.
     *
     * @param courseDetailsService the course details service
     */
    public CourseDetailsStartOfClassUpdateCommand(CourseDetailsService courseDetailsService) {
        this.courseDetailsService = courseDetailsService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String startOfClass = request.getParameter(RequestParameter.START_OF_CLASS);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Long courseId = IdUtil.stringToLong(courseIdString);
            Optional<CourseDetails> courseDetailsOptional = courseDetailsService.findCourseDetailsByCourseId(courseId);
            if (courseDetailsOptional.isPresent()) {
                if (!CourseDetailsValidator.isValidStartOfClass(startOfClass)) {
                    session.setAttribute(SessionAttribute.ERROR_START_OF_CLASS, true);
                    dataCorrect = false;
                }
                if (dataCorrect) {
                    CourseDetails courseDetails = new CourseDetails();
                    courseDetails.setId(courseDetailsOptional.get().getId());
                    courseDetails.setStartOfClass(LocalTime.parse(startOfClass));
                    courseDetailsService.updateStartOfClass(courseDetails);
                }
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
            } else {
                session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.MAIN.getServletPath());
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
