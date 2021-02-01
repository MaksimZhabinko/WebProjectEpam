package edu.epam.project.command.impl;

import edu.epam.project.command.*;
import edu.epam.project.model.entity.RoleType;
import edu.epam.project.model.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.model.service.UserService;
import edu.epam.project.util.MailSender;
import edu.epam.project.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);
    private UserService service;

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
        HttpSession session = request.getSession();
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
                router.setPagePath(PagePath.MAIN);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.MAIN);
                MailSender mailSender = new MailSender();
                mailSender.sendMessage(user.getEmail(), user.getName(), user.getSurname());
            } else {
                router.setPagePath(PagePath.SIGN_UP);
            }
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500);
        }
        return router;
    }
}
