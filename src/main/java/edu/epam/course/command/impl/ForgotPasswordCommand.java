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
 * The type Forgot password command.
 */
public class ForgotPasswordCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ForgotPasswordCommand.class);
    private UserService userService;

    /**
     * Instantiates a new Forgot password command.
     *
     * @param userService the user service
     */
    public ForgotPasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        Router router = new Router();
        HttpSession session = request.getSession();
        boolean dataCorrect = true;
        try {
            if (!UserValidator.isValidEmailForForgotPassword(email)) {
                request.setAttribute(RequestAttribute.ERROR_EMAIL_MESSAGE_INVALID_FOR_FORGOT_PASSWORD, true);
                router.setPagePath(PagePath.FORGOT_PASSWORD.getDirectUrl());
                dataCorrect = false;
            }
            if (dataCorrect) {
                Optional<User> user = userService.findUserByEmail(email);
                if (user.isPresent()) {
                    userService.forgotUserPassword(user.get());
                    router.setPagePath(PagePath.SIGN_IN.getDirectUrl());
                    router.setType(Router.Type.REDIRECT);
                    session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.SIGN_IN.getDirectUrl());
                } else {
                    request.setAttribute(RequestAttribute.ERROR_EMAIL_MESSAGE_IS_NOT_EXIST, true);
                    request.setAttribute(RequestAttribute.EMAIL, email);
                    router.setPagePath(PagePath.FORGOT_PASSWORD.getDirectUrl());
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
