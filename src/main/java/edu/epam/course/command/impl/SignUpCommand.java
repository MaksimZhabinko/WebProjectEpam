package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.exception.EmailException;
import edu.epam.course.model.entity.RoleType;
import edu.epam.course.model.entity.User;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.UserService;
import edu.epam.course.util.MailSenderUtil;
import edu.epam.course.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * The type Sign up command.
 */
public class SignUpCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);
    private UserService service;

    /**
     * Instantiates a new Sign up command.
     *
     * @param service the service
     */
    public SignUpCommand(UserService service) {
        this.service = service;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String repeatPassword = request.getParameter(RequestParameter.REPEAT_PASSWORD);
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!UserValidator.isValidEmail(email)) {
                request.setAttribute(RequestAttribute.ERROR_EMAIL_MESSAGE_INVALID, true);
                dataCorrect = false;
            }
            if (!UserValidator.isValidNameAndSurname(name, surname)) {
                request.setAttribute(RequestAttribute.ERROR_NAME_AND_SURNAME_MESSAGE, true);
                dataCorrect = false;
            }
            if (!UserValidator.isValidPasswordAndRepeatPassword(password, repeatPassword)) {
                request.setAttribute(RequestAttribute.ERROR_PASSWORD_MESSAGE, true);
                dataCorrect = false;
            }
            Optional<User> userByEmail = service.findUserByEmail(email);
            if (userByEmail.isPresent()) {
                request.setAttribute(RequestAttribute.ERROR_EMAIL_MESSAGE_IS_EXIST, true);
                dataCorrect = false;
            }
            if (dataCorrect) {
                User user = new User(email, name, surname, RoleType.USER, true);
                service.addUser(user, password);
                MailSenderUtil.sendMessage(user.getEmail(), user.getName(), user.getSurname());
                router.setPagePath(PagePath.SIGN_IN.getServletPath());
                router.setType(Router.Type.REDIRECT);
            } else {
                router.setPagePath(PagePath.SIGN_UP.getDirectUrl());
                request.setAttribute(RequestAttribute.EMAIL, email);
                request.setAttribute(RequestAttribute.NAME, name);
                request.setAttribute(RequestAttribute.SURNAME, surname);
            }
        } catch (ServiceException | EmailException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
