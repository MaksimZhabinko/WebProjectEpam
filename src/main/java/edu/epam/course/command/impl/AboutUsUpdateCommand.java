package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.AboutUs;
import edu.epam.course.model.service.AboutUsService;
import edu.epam.course.validator.IdValidator;
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
        String aboutUsId = request.getParameter("about_us_id");
        String message = request.getParameter("message"); // todo constant
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!IdValidator.isValidId(aboutUsId)) {
                // todo что делать?
            }
            if (dataCorrect) {
                AboutUs aboutUs = new AboutUs();
                aboutUs.setId(Long.valueOf(aboutUsId));
                aboutUs.setMessage(message);
                aboutUsService.updateMessage(aboutUs);
                router.setPagePath(PagePath.ABOUT_US.getServletPath());
            }
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
