package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.Router;

import javax.servlet.http.HttpServletRequest;

public class TEST implements Command {
    @Override
    public Router execute(HttpServletRequest request) {

        String[] names = request.getParameterValues("name");
        for(String s : names) {
            System.out.println("Name : " + s);
        }
        int a = 0;
        return null;
    }
}
