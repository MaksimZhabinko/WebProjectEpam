package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.entity.Review;
import edu.epam.course.model.entity.User;
import edu.epam.course.model.service.LectureService;
import edu.epam.course.validator.ValidMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class LectureAddCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LectureAddCommand.class);
    private LectureService lectureService;

    public LectureAddCommand(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String message = request.getParameter(RequestParameter.MESSAGE);
        String courseId = request.getParameter(RequestParameter.COURSE_ID);
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            if (!ValidMessage.isValidMessage(message)) {
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, true);
                router.setPagePath(PagePath.REVIEW.getServletPath());
                dataCorrect = false;
            }
            if (dataCorrect) {
                Course course = new Course();
                course.setId(Long.valueOf(courseId));
                Lecture lecture = new Lecture();
                lecture.setLecture(message);
                lecture.setCourse(course);
                lectureService.addLecture(lecture);
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
