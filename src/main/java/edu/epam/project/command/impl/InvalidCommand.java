package edu.epam.project.command.impl;

import edu.epam.project.command.Command;
import edu.epam.project.command.PagePath;
import edu.epam.project.command.Router;

import javax.servlet.http.HttpServletRequest;

public class InvalidCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        // todo
//        String command = request.getParameter("command");
        // log
        return new Router(PagePath.ERROR_404);
    }
}
