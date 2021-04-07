package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.AboutUs;
import edu.epam.course.model.service.AboutUsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Open about us command.
 */
public class OpenAboutUsCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(OpenAboutUsCommand.class);
    private AboutUsService aboutUsService;

    /**
     * Instantiates a new Open about us command.
     *
     * @param aboutUsService the about us service
     */
    public OpenAboutUsCommand(AboutUsService aboutUsService) {
        this.aboutUsService = aboutUsService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            List<AboutUs> aboutUsList = aboutUsService.findAll();
            request.setAttribute(RequestAttribute.ABOUT_US_LIST, aboutUsList);
            router.setPagePath(PagePath.ABOUT_US.getDirectUrl());
            session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.ABOUT_US.getServletPath());
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
