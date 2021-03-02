package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.LectureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LectureDeleteCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LectureDeleteCommand.class);
    private LectureService lectureService;

    public LectureDeleteCommand(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String lectureId = request.getParameter(RequestParameter.LECTURE_ID);
        String courseId = request.getParameter(RequestParameter.COURSE_ID);
        Router router = new Router();
        try {
            lectureService.deleteLecture(Long.valueOf(lectureId));
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
