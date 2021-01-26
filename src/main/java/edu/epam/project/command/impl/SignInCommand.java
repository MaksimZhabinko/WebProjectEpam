package edu.epam.project.command.impl;

import edu.epam.project.command.*;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.validator.UserValidator;
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
                router.setPagePath(PagePath.SIGN_IN);
                dataCorrect = false;
            }
            if (dataCorrect) {
                Optional<User> userByEmailAndPassword = service.findUserByEmailAndPassword(email, password);
                if (userByEmailAndPassword.isPresent()) {
                    router.setPagePath(PagePath.MAIN);
                    router.setType(Router.Type.REDIRECT);
                    session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.MAIN);
                    //todo remove
                    session.setAttribute("user", userByEmailAndPassword.get());
                } else {
                    request.setAttribute(RequestAttribute.ERROR_USER_MESSAGE_IS_INCORRECT, true);
                    router.setPagePath(PagePath.SIGN_IN);
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500);
        }
        return router;
    }
}
