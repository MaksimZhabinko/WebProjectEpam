package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.TeacherService;
import edu.epam.course.util.IdUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Teacher delete command.
 */
public class TeacherDeleteCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(TeacherDeleteCommand.class);
    private TeacherService teacherService;

    /**
     * Instantiates a new Teacher delete command.
     *
     * @param teacherService the teacher service
     */
    public TeacherDeleteCommand(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String[] teachersIdString = request.getParameterValues(RequestParameter.TEACHER_ID);
        List<Long> teachersId = new ArrayList<>();
        Router router = new Router();
        try {
            for (int i = 0; i < teachersIdString.length; i++) {
                Long teacherId = IdUtil.stringToLong(teachersIdString[i]);
                teachersId.add(teacherId);
            }
            teacherService.deleteTeachers(teachersId);
            router.setPagePath(PagePath.SHOW_ALL_TEACHERS.getServletPath());
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
