package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.service.CourseService;
import edu.epam.course.model.service.LectureService;
import edu.epam.course.util.IdUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The type Lecture delete command.
 */
public class LectureDeleteCommand implements Command {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(LectureDeleteCommand.class);
    private LectureService lectureService;
    private CourseService courseService;

    /**
     * Instantiates a new Lecture delete command.
     *
     * @param lectureService the lecture service
     * @param courseService  the course service
     */
    public LectureDeleteCommand(LectureService lectureService, CourseService courseService) {
        this.lectureService = lectureService;
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String lectureIdString = request.getParameter(RequestParameter.LECTURE_ID);
        String courseIdString = request.getParameter(RequestParameter.COURSE_ID);
        HttpSession session = request.getSession();
        Router router = new Router();
        boolean dataCorrect = true;
        try {
            Long lectureId = IdUtil.stringToLong(lectureIdString);
            Long courseId = IdUtil.stringToLong(courseIdString);
            Optional<Course> courseOptional = courseService.findById(courseId);
            if (!courseOptional.isPresent()) {
                session.setAttribute(SessionAttribute.ERROR_COURSE_NOT_FOUND, true);
                router.setType(Router.Type.REDIRECT);
                router.setPagePath(PagePath.MAIN.getServletPath());
                dataCorrect = false;
            }
            if (dataCorrect) {
                lectureService.delete(lectureId);
                router.setPagePath(PagePath.LECTURE.getServletPath() + courseId);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
