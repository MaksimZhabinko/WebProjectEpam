package edu.epam.project.command.impl;

import edu.epam.project.command.Command;

import javax.servlet.http.HttpServletRequest;

public class PageCommand implements Command {

    private final String page;

    public PageCommand(String page) {
        this.page = page;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return page;
    }
}
