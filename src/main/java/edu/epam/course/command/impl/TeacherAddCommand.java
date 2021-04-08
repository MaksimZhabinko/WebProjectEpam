package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.command.SessionAttribute;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Teacher;
import edu.epam.course.model.service.TeacherService;
import edu.epam.course.validator.TeacherValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The type Teacher add command.
 */
public class TeacherAddCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(TeacherAddCommand.class);
    private TeacherService teacherService;

    /**
     * Instantiates a new Teacher add command.
     *
     * @param teacherService the teacher service
     */
    public TeacherAddCommand(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String teacherName = request.getParameter(RequestParameter.TEACHER_NAME).trim();
        String teacherSurname = request.getParameter(RequestParameter.TEACHER_SURNAME).trim();
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!TeacherValidator.isValidNameAndSurname(teacherName, teacherSurname)) {
                session.setAttribute(SessionAttribute.ERROR_TEACHER_ADD, true);
                dataCorrect = false;
            }
            if (dataCorrect) {
                Optional<Teacher> teacherOptional = teacherService.findTeacherByNameAndSurname(teacherName, teacherSurname);
                if (teacherOptional.isPresent()) {
                    session.setAttribute(SessionAttribute.ERROR_TEACHER_HAVE, true);
                    dataCorrect = false;
                }
            }
            if (dataCorrect) {
                teacherService.addTeacher(teacherName, teacherSurname);
            }
            router.setType(Router.Type.REDIRECT);
            router.setPagePath(PagePath.PERSONAL_AREA.getServletPath()); // todo возможно на стр показать все учителей
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
