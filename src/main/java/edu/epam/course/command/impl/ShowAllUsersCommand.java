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
import edu.epam.course.util.PaginationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Show all users command.
 */
public class ShowAllUsersCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ShowAllUsersCommand.class);
    private UserService userService;

    /**
     * Instantiates a new Show all users command.
     *
     * @param userService the user service
     */
    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = request.getParameter(RequestParameter.PAGE);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Integer pageInt = Math.abs(Integer.parseInt(page));
            Long maxUserId = userService.findMaxUserId();
            List<Integer> pages = PaginationUtil.paginationUserPages(maxUserId);
            session.setAttribute(SessionAttribute.PAGES, pages);
            if (pageInt > pages.get(pages.size() - 1)) {
                // todo посмотреть, может улучшить, а то если захочет 4 стр из 3, то булет 404
                router.setPagePath(PagePath.ERROR_404.getDirectUrl());
                dataCorrect = false;
            }
            if (dataCorrect) {
                List<User> allUsers = userService.findAllLimit(pageInt);
                session.setAttribute(SessionAttribute.PAGE, page);
                session.setAttribute(SessionAttribute.ALL_USERS, allUsers);
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
