package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class OpenPersonalAreaCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OpenPersonalAreaCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
