package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.UserService;
import edu.epam.course.util.UserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class EnrollCourseCommand implements Command {
    private static final Logger logger = LogManager.getLogger(EnrollCourseCommand.class);
    private UserService userService;

    public EnrollCourseCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String courseId = request.getParameter(RequestParameter.COURSE_ID);
        String courseCost = request.getParameter(RequestParameter.COURSE_COST);
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(RequestAttribute.USER);
        Router router = new Router();
        try {
            boolean isUserHaveCourse = userService.userHaveCourse(userSession.getId(), Long.valueOf(courseId));
            if (!isUserHaveCourse) {
                BigDecimal transaction = UserUtil.transaction(courseCost, userSession.getMoney());
                if (transaction.compareTo(BigDecimal.ZERO) > 0) {
                    userService.enrollCourse(userSession, Long.valueOf(courseId), transaction);
                    session.setAttribute(SessionAttribute.USER, userSession);
                    router.setType(Router.Type.REDIRECT);
                    router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
                } else {
                    request.setAttribute(RequestAttribute.ERROR_USER_HAVE_LITTLE_MONEY, true);
                    router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
                }
            } else {
                request.setAttribute(RequestAttribute.ERROR_USER_HAVE_COURSE, true);
                router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
