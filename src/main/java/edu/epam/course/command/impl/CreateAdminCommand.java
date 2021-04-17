package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.RoleType;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.UserService;
import edu.epam.course.util.IdUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Create admin command.
 */
public class CreateAdminCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CreateAdminCommand.class);
    private UserService userService;

    /**
     * Instantiates a new Create admin command.
     *
     * @param userService the user service
     */
    public CreateAdminCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String userIdString = request.getParameter(RequestParameter.USER_ID);
        String page = request.getParameter(RequestParameter.PAGE);
        Router router = new Router();
        try {
            Long userId = IdUtil.stringToLong(userIdString);
            Integer pageInt = Math.abs(Integer.parseInt(page));
            User user = new User();
            user.setId(userId);
            user.setRole(RoleType.ADMIN);
            userService.updateUserToAdmin(user);
            router.setPagePath(PagePath.SHOW_ALL_USERS.getServletPath() + pageInt);
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
