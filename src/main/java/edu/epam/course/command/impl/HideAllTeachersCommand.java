package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Hide all teachers command.
 */
public class HideAllTeachersCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.ALL_TEACHERS);
        router.setType(Router.Type.REDIRECT);
        router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
        return router;
    }
}
