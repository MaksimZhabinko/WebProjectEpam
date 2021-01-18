package edu.epam.project.command.impl;

import edu.epam.project.command.Command;
import edu.epam.project.command.PagePath;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);
    private final UserService service;

    public SignUpCommand(UserService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat_password");
        boolean dataCorrect = true;
        try {
            if (!service.isValidEmail(email)) {
                request.setAttribute("errorEmailMessage", "Email is incorrect");
                dataCorrect = false;
            }
            if (!service.isValidNameAndSurname(name, surname)) {
                request.setAttribute("errorNameAndSurnameMessage", "name and surname cannot be empty");
                dataCorrect = false;
            }
            if (!service.isValidPasswordAndRepeatPassword(password, repeatPassword)) {
                request.setAttribute("errorPasswordMessage", "Password is incorrect or repeat the password does not match the password");
                dataCorrect = false;
            }
            if (service.isExistUser(email)) {
                request.setAttribute("errorEmailMessage", "This email is already in use");
                dataCorrect = false;
            }
            if (dataCorrect) {
                User user = new User(email, name, surname, RoleType.USER, true);
                service.addUser(user, password);
                page = PagePath.MAIN;
            } else {
                page = PagePath.SIGN_UP;
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
