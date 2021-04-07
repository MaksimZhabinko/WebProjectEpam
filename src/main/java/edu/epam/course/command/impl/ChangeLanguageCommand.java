package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Change language command.
 */
public class ChangeLanguageCommand implements Command {
    /**
     * The constant ru.
     */
    private static final String RU = "ru";
    /**
     * The constant en.
     */
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
