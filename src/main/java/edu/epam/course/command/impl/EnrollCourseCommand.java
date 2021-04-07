package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.CourseDetailsService;
import edu.epam.course.model.service.UserService;
import edu.epam.course.util.CountMoneyUtil;
import edu.epam.course.util.IdUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * The type Enroll course command.
 */
public class EnrollCourseCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(EnrollCourseCommand.class);
    private UserService userService;
    private CourseDetailsService courseDetailsService;

    /**
     * Instantiates a new Enroll course command.
     *
     * @param userService          the user service
     * @param courseDetailsService the course details service
     */
    public EnrollCourseCommand(UserService userService, CourseDetailsService courseDetailsService) {
        this.userService = userService;
        this.courseDetailsService = courseDetailsService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(RequestAttribute.USER);
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Long courseId = IdUtil.stringToLong(courseIdString);
            Optional<CourseDetails> courseDetails = courseDetailsService.findCourseDetailsByCourseId(courseId);
            if (courseDetails.isPresent() && courseDetails.get().getCourse().getEnrollmentActive() == true) {
                boolean isUserHaveCourse = userService.userHaveCourse(userSession.getId(), courseId);
                if (!isUserHaveCourse) {
                    BigDecimal transaction = CountMoneyUtil.transaction(courseDetails.get().getCost(), userSession.getMoney());
                    if (transaction.compareTo(BigDecimal.ZERO) > 0) {
                        userService.enrollCourse(userSession, courseId, transaction);
                        session.setAttribute(SessionAttribute.USER, userSession);
                        router.setType(Router.Type.REDIRECT);
                        router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
                    } else {
                        session.setAttribute(SessionAttribute.ERROR_USER_HAVE_LITTLE_MONEY, true);
                        dataCorrect = false;
                    }
                } else {
                    session.setAttribute(SessionAttribute.ERROR_USER_HAVE_COURSE, true);
                    dataCorrect = false;
                }
            } else {
                session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.MAIN.getServletPath());
            }
            if (!dataCorrect) {
                router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
