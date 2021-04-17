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

/**
 * The type Update user name and surname command.
 */
public class UpdateUserNameAndSurnameCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(UpdateUserNameAndSurnameCommand.class);
    private UserService userService;

    /**
     * Instantiates a new Update user name and surname command.
     *
     * @param userService the user service
     */
    public UpdateUserNameAndSurnameCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!UserValidator.isValidNameAndSurname(name, surname)) {
                request.setAttribute(RequestAttribute.ERROR_NAME_AND_SURNAME_MESSAGE, true);
                router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
                dataCorrect = false;
            }
            if (dataCorrect) {
                userService.updateNameAndSurname(name, surname, user.getId());
                user.setName(name);
                user.setSurname(surname);
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
            }
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
