package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.Router;

import javax.servlet.http.HttpServletRequest;

public class ShowAllUsersEnrolledCourse implements Command { // fixme
    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
