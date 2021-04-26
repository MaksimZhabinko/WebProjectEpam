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
import java.math.BigDecimal;
import java.util.Optional;

/**
 * The type Course details cost update command.
 */
public class CourseDetailsCostUpdateCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseDetailsCostUpdateCommand.class);
    private CourseDetailsService courseDetailsService;

    /**
     * Instantiates a new Course details cost update command.
     *
     * @param courseDetailsService the course details service
     */
    public CourseDetailsCostUpdateCommand(CourseDetailsService courseDetailsService) {
        this.courseDetailsService = courseDetailsService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        String cost = request.getParameter(RequestParameter.COST);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Long courseId = IdUtil.stringToLong(courseIdString);
            Optional<CourseDetails> courseDetailsOptional = courseDetailsService.findCourseDetailsByCourseId(courseId);
            if (courseDetailsOptional.isPresent()) {
                if (!CourseDetailsValidator.isValidCost(cost)) {
                    session.setAttribute(SessionAttribute.ERROR_COST, true);
                    dataCorrect = false;
                }
                if (dataCorrect) {
                    CourseDetails courseDetails = CourseDetails.builder()
                            .setId(courseDetailsOptional.get().getId())
                            .setCost(new BigDecimal(cost))
                            .build();
                    courseDetailsService.updateCost(courseDetails);
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
