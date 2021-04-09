package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Teacher;
import edu.epam.course.model.service.TeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Show all teachers command.
 */
public class ShowAllTeachersCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ShowAllTeachersCommand.class);
    private TeacherService teacherService;

    /**
     * Instantiates a new Show all teachers command.
     *
     * @param teacherService the teacher service
     */
    public ShowAllTeachersCommand(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        try {
            List<Teacher> allTeachers = teacherService.findAll();
            session.setAttribute(SessionAttribute.ALL_TEACHERS, allTeachers);
            router.setType(Router.Type.REDIRECT);
            router.setPagePath(PagePath.PERSONAL_AREA.getServletPath());
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
