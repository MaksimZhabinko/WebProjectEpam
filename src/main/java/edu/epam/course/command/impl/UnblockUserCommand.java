package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.UserService;
import edu.epam.course.util.IdUtil;
import edu.epam.course.validator.TeacherValidator;
import edu.epam.course.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Unblock user command.
 */
public class UnblockUserCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(UnblockUserCommand.class);
    private UserService userService;

    /**
     * Instantiates a new Unblock user command.
     *
     * @param userService the user service
     */
    public UnblockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = request.getParameter(RequestParameter.PAGE);
        String[] usersIdString = request.getParameterValues(RequestParameter.USER_ID);
        List<Long> usersId = new ArrayList<>();
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Integer pageInt = Math.abs(Integer.parseInt(page));
            if (!UserValidator.isValidUsersId(usersIdString)) {
                session.setAttribute(SessionAttribute.ERROR_SELECT_USER_ID, true);
                dataCorrect = false;
            }
            if (dataCorrect) {
                for (int i = 0; i < usersIdString.length; i++) {
                    Long userId = IdUtil.stringToLong(usersIdString[i]);
                    usersId.add(userId);
                }
                userService.unblockUser(usersId);
            }
            router.setPagePath(PagePath.SHOW_ALL_USERS.getServletPath() + pageInt);
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
