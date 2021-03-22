package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UnblockUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UnblockUserCommand.class);
    private UserService userService;

    public UnblockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String[] usersId = request.getParameterValues(RequestParameter.USER_ID);
        List<Long> usersIdLong = new ArrayList<>();
        Router router = new Router();
        try {
            for (int i = 0; i < usersId.length; i++) {
                Long userIdLong = Long.valueOf(usersId[i]);
                usersIdLong.add(userIdLong);
            }
            userService.unblockUser(usersIdLong);
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
