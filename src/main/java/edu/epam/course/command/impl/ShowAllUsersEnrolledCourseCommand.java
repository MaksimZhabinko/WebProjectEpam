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
import edu.epam.course.util.PaginationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Show all users enrolled course command.
 */
public class ShowAllUsersEnrolledCourseCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ShowAllUsersEnrolledCourseCommand.class);
    private UserService userService;

    /**
     * Instantiates a new Show all users enrolled course command.
     *
     * @param userService the user service
     */
    public ShowAllUsersEnrolledCourseCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = request.getParameter(RequestParameter.PAGE_ENROLLED);
        HttpSession session = request.getSession();
        Router router = new Router();
        try {
            Integer pageInt = Math.abs(Integer.parseInt(page));
            List<User> allUsersEnrolledCourse = userService.findAllEnrolledCourse();
            List<Integer> pages = PaginationUtil.paginationUserPages(Long.valueOf(allUsersEnrolledCourse.size()));
            session.setAttribute(SessionAttribute.PAGES_ENROLLED, pages);
            if (pageInt > pages.get(pages.size() - 1)) {
                pageInt = pages.get(pages.size() - 1);
            }
            List<User> usersEnrolledCourseLimit = PaginationUtil.usersEnrolledCourseLimit(allUsersEnrolledCourse, pageInt);
            session.setAttribute(SessionAttribute.USERS_ENROLLED_COURSE_LIMIT, usersEnrolledCourseLimit);
            router.setType(Router.Type.REDIRECT);
            router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
