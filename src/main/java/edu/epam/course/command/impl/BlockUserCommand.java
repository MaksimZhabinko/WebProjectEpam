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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BlockUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(BlockUserCommand.class);
    private UserService userService;

    public BlockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) { // todo look
        String[] usersId = request.getParameterValues(RequestParameter.USER_ID);
        List<Long> usersIdLong = new ArrayList<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        Router router = new Router();
        try {
            for (int i = 0; i < usersId.length; i++) {
                Long userIdLong = Long.valueOf(usersId[i]);
                if (user.getId().equals(userIdLong)) {
                    session.setAttribute("ErrorBLockYourself", true); // todo sessionAttribute and вывести на экран
                } else {
                    usersIdLong.add(userIdLong);
                }
            }
            userService.blockUser(usersIdLong);
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
