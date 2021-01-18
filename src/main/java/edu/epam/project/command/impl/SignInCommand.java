package edu.epam.project.command.impl;

import edu.epam.project.command.Command;
import edu.epam.project.command.PagePath;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private final UserService service;

    public SignInCommand(UserService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            if (service.isExistUser(email, password)) {
                page = PagePath.MAIN;
            } else {
                request.setAttribute("errorUserMessage", "incorrect email or password");
                page = PagePath.SIGN_IN;
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
        return page;
    }
}
