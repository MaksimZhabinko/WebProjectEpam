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
import edu.epam.course.util.IdUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block user command.
 */
public class BlockUserCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(BlockUserCommand.class);
    private UserService userService;

    /**
     * Instantiates a new Block user command.
     *
     * @param userService the user service
     */
    public BlockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = request.getParameter(RequestParameter.PAGE);
        String[] usersIdString = request.getParameterValues(RequestParameter.USER_ID);
        List<Long> usersId = new ArrayList<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        Router router = new Router();
        try {
            for (int i = 0; i < usersIdString.length; i++) {
                Long userId = IdUtil.stringToLong(usersIdString[i]);
                if (user.getId().equals(userId)) {
                    session.setAttribute(SessionAttribute.ERROR_BLOCK_YOURSELF, true);
                } else {
                    usersId.add(userId);
                }
            }
            userService.blockUser(usersId);
            router.setPagePath(PagePath.SHOW_ALL_USERS.getServletPath() + page);
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
