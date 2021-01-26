package edu.epam.project.command.impl;

import edu.epam.project.command.Command;
import edu.epam.project.command.RequestParameter;
import edu.epam.project.command.Router;
import edu.epam.project.command.SessionAttribute;

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
                session.setAttribute(RequestParameter.LANGUAGE, RU);
                break;
            case EN :
                session.setAttribute(RequestParameter.LANGUAGE, EN);
                break;
            default:
                session.setAttribute(RequestParameter.LANGUAGE, RU);
        }
        return new Router(pagePath, Router.Type.REDIRECT);
    }
}
