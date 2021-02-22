package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.AboutUs;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.service.AboutUsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OpenAboutUsCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OpenAboutUsCommand.class);
    private AboutUsService aboutUsService;

    public OpenAboutUsCommand(AboutUsService aboutUsService) {
        this.aboutUsService = aboutUsService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            List<AboutUs> aboutUsList = aboutUsService.findAll();
            request.setAttribute("aboutUsList", aboutUsList); // todo constant
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
