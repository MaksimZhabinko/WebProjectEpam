package edu.epam.project.command.impl;

import edu.epam.project.command.Command;
import edu.epam.project.command.Router;
import edu.epam.project.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PageCommand implements Command {

    private String page;

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
