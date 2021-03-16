package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.AboutUs;
import edu.epam.course.model.service.AboutUsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AboutUsUpdateCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AboutUsUpdateCommand.class);
    private AboutUsService aboutUsService;

    public AboutUsUpdateCommand(AboutUsService aboutUsService) {
        this.aboutUsService = aboutUsService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String aboutUsId = request.getParameter(RequestParameter.ABOUT_US_ID);
        String message = request.getParameter(RequestParameter.MESSAGE);
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (dataCorrect) {
                AboutUs aboutUs = new AboutUs();
                aboutUs.setId(Long.valueOf(aboutUsId));
                aboutUs.setMessage(message);
                aboutUsService.updateMessage(aboutUs);
                router.setPagePath(PagePath.ABOUT_US.getServletPath());
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
