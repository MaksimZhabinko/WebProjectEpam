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
import edu.epam.course.validator.BalanceReplenishmentValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Balance replenishment command.
 */
public class BalanceReplenishmentCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(BalanceReplenishmentCommand.class);
    /**
     * The constant limit money.
     */
    private static final Integer LIMIT_MONEY = 5000;
    private UserService userService;

    /**
     * Instantiates a new Balance replenishment command.
     *
     * @param userService the user service
     */
    public BalanceReplenishmentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String money = request.getParameter(RequestParameter.MONEY);
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        boolean dataCorrect = true;
        try {
            if (!BalanceReplenishmentValidator.isValidMoney(money)) {
                request.setAttribute(RequestAttribute.ERROR_BALANCE_MESSAGE_IS_VALID, true);
                router.setPagePath(PagePath.BALANCE_REPLENISHMENT.getDirectUrl());
                dataCorrect = false;
            }
            if (Integer.parseInt(money) > LIMIT_MONEY) {
                request.setAttribute(RequestAttribute.ERROR_LIMIT_MONEY, true);
                router.setPagePath(PagePath.BALANCE_REPLENISHMENT.getDirectUrl());
                dataCorrect = false;
            }
            if (dataCorrect) {
                userService.updateUserBalance(money, user);
                session.setAttribute(RequestAttribute.USER, user);
                router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
