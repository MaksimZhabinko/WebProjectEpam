package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OpenPersonalAreaCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OpenPersonalAreaCommand.class);
    private CourseService courseService;

    public OpenPersonalAreaCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        try {
            if (user != null) {
                List<Course> userEnrolledByCourse = courseService.findUserEnrolledByCourse(user.getId());
                if (!userEnrolledByCourse.isEmpty()) {
                    request.setAttribute(RequestAttribute.USER_ENROLLED_BY_COURSE, userEnrolledByCourse);
                }
            }
            router.setPagePath(PagePath.PERSONAL_AREA.getDirectUrl());
            session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.PERSONAL_AREA.getServletPath());
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
