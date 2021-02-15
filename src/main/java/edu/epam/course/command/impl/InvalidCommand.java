package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.Router;

import javax.servlet.http.HttpServletRequest;

public class InvalidCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        // todo тут еще что-то надо?
        return new Router(PagePath.ERROR_404.getDirectUrl());
    }
}
