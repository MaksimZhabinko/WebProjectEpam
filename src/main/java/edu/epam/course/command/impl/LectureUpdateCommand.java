package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.service.LectureService;
import edu.epam.course.validator.ValidMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LectureUpdateCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LectureUpdateCommand.class);
    private LectureService lectureService;

    public LectureUpdateCommand(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String message = request.getParameter(RequestParameter.MESSAGE);
        String courseId = request.getParameter(RequestParameter.COURSE_ID);
        String lectureId = request.getParameter(RequestParameter.LECTURE_ID);
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!ValidMessage.isValidMessage(message)) {
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, true);
                router.setPagePath(PagePath.REVIEW.getServletPath());
                dataCorrect = false;
            }
            if (dataCorrect) {
                Lecture lecture = new Lecture();
                lecture.setLecture(message);
                lecture.setId(Long.valueOf(lectureId));
                lectureService.updateLecture(lecture);
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
