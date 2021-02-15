package edu.epam.course.command.impl;

import edu.epam.course.command.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    private static final String RU = "ru";
    private static final String EN = "en";

    @Override
    public Router execute(HttpServletRequest request) {
        String language = request.getParameter(RequestParameter.LANGUAGE);
        HttpSession session = request.getSession();
        String pagePath = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        switch (language) {
            case RU :
                session.setAttribute(RequestAttribute.LANGUAGE, RU);
                break;
            case EN :
                session.setAttribute(RequestAttribute.LANGUAGE, EN);
                break;
            default:
                session.setAttribute(RequestAttribute.LANGUAGE, RU);
        }
        return new Router(pagePath, Router.Type.REDIRECT);
    }
}
