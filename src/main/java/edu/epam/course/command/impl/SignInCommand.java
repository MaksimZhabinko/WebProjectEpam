package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.model.entity.User;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.UserService;
import edu.epam.course.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private UserService service;

    public SignInCommand(UserService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!UserValidator.isValidEmailAndPassword(email, password)) {
                request.setAttribute(RequestAttribute.ERROR_USER_MESSAGE_IS_VALID, true);
                router.setPagePath(PagePath.SIGN_IN.getDirectUrl());
                dataCorrect = false;
            }
            if (dataCorrect) {
                Optional<User> user = service.findUserByEmailAndPassword(email, password);
                if (user.isPresent()) {
                    if (user.get().isEnabled()) {
                        router.setPagePath(PagePath.MAIN.getServletPath());
                        router.setType(Router.Type.REDIRECT);
                        session.setAttribute(RequestAttribute.USER, user.get());
                    } else {
                        request.setAttribute(RequestAttribute.ERROR_USER_BLOCK, true);
                        request.setAttribute(RequestAttribute.EMAIL, email);
                        router.setPagePath(PagePath.SIGN_IN.getDirectUrl());
                    }
                } else {
                    request.setAttribute(RequestAttribute.ERROR_USER_MESSAGE_IS_INCORRECT, true);
                    request.setAttribute(RequestAttribute.EMAIL, email);
                    router.setPagePath(PagePath.SIGN_IN.getDirectUrl());
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
