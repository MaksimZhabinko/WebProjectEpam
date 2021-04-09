package edu.epam.course.command.impl;

import edu.epam.course.command.Command;
import edu.epam.course.command.PagePath;
import edu.epam.course.command.RequestAttribute;
import edu.epam.course.command.RequestParameter;
import edu.epam.course.command.Router;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.LectureService;
import edu.epam.course.util.IdUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Lecture delete command.
 */
public class LectureDeleteCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(LectureDeleteCommand.class);
    private LectureService lectureService;

    /**
     * Instantiates a new Lecture delete command.
     *
     * @param lectureService the lecture service
     */
    public LectureDeleteCommand(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String lectureIdString = request.getParameter(RequestParameter.LECTURE_ID);
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        Router router = new Router();
        try { // todo нет проверки сущ данный курс или нет а также лекция, хотя лекция пофиг так как просто ничего не произойдет
            Long lectureId = IdUtil.stringToLong(lectureIdString);
            Long courseId = IdUtil.stringToLong(courseIdString);
            lectureService.delete(lectureId);
            router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
            router.setType(Router.Type.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
