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
import edu.epam.course.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The type Update user password command.
 */
public class UpdateUserPasswordCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(UpdateUserPasswordCommand.class);
    private UserService userService;

    /**
     * Instantiates a new Update user password command.
     *
     * @param userService the user service
     */
    public UpdateUserPasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String password = request.getParameter(RequestParameter.PASSWORD);
        String repeatPassword = request.getParameter(RequestParameter.REPEAT_PASSWORD);
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        boolean dataCorrect = true;
        try {
            if (!UserValidator.isValidPasswordAndRepeatPassword(password, repeatPassword)) {
                request.setAttribute(RequestAttribute.ERROR_PASSWORD_MESSAGE, true);
                router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
                dataCorrect = false;
            }
            if (dataCorrect) {
                Optional<User> userOptional = userService.findByEmailAndPassword(user.getEmail(), password);
                if (userOptional.isPresent()) {
                    session.setAttribute(SessionAttribute.ERROR_CANNOT_UPDATE_PASSWORD, true);
                    router.setType(Router.Type.REDIRECT);
                    router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
                } else {
                    userService.updatePassword(password, user);
                    session.setAttribute(SessionAttribute.SUCCESS, true);
                    router.setType(Router.Type.REDIRECT);
                    router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
