package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.AboutUs;
import edu.epam.course.model.service.AboutUsService;
import edu.epam.course.util.IdUtil;
import edu.epam.course.validator.MessageValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type About us update command.
 */
public class AboutUsUpdateCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(AboutUsUpdateCommand.class);
    private AboutUsService aboutUsService;

    /**
     * Instantiates a new About us update command.
     *
     * @param aboutUsService the about us service
     */
    public AboutUsUpdateCommand(AboutUsService aboutUsService) {
        this.aboutUsService = aboutUsService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String aboutUsIdString = request.getParameter(RequestParameter.ABOUT_US_ID);
        String message = request.getParameter(RequestParameter.MESSAGE).strip();
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Long aboutUsId = IdUtil.stringToLong(aboutUsIdString);
            if (!MessageValidator.isValidMessage(message)) {
                session.setAttribute(SessionAttribute.ERROR_MESSAGE, true);
                dataCorrect = false;
            }
            if (dataCorrect) {
                AboutUs aboutUs = new AboutUs();
                aboutUs.setId(aboutUsId);
                aboutUs.setMessage(message);
                aboutUsService.updateMessage(aboutUs);
            }
            router.setPagePath(PagePath.ABOUT_US.getServletPath());
            router.setType(Router.Type.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
