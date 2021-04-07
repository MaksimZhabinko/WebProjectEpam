package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Page command.
 */
public class PageCommand implements Command {

    private String page;

    /**
     * Instantiates a new Page command.
     *
     * @param page the page
     */
    public PageCommand(String page) {
        this.page = page;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, page);
        return new Router(page);
    }
}
