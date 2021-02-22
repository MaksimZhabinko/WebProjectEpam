package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.service.CourseDetailsService;
import edu.epam.course.model.service.LectureService;
import edu.epam.course.validator.IdValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class OpenLectureCommand implements Command {
    private static final Logger logger = LogManager.getLogger(OpenLectureCommand.class);
    private LectureService lectureService;
    private CourseDetailsService courseDetailsService;

    public OpenLectureCommand(LectureService lectureService, CourseDetailsService courseDetailsService) {
        this.lectureService = lectureService;
        this.courseDetailsService = courseDetailsService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String courseId = request.getParameter(RequestParameter.COURSE_ID);
        boolean dataCorrect = true;
        try {
            if (!IdValidator.isValidId(courseId)) {
                router.setPagePath(PagePath.ERROR_404.getDirectUrl()); // todo куда кидать если courseId будет строкой а не число
                dataCorrect = false;
            }
            if (dataCorrect) {
                Long courseIdLong = Long.valueOf(courseId);
                List<Lecture> lectures = lectureService.findAllLectureById(courseIdLong);
                if (!lectures.isEmpty()) {
                    Optional<CourseDetails> courseDetails = courseDetailsService.findCourseDetailsById(courseIdLong);
                    request.setAttribute(RequestAttribute.LECTURES, lectures);
                    request.setAttribute(RequestAttribute.COURSE_DETAILS, courseDetails.get());
                }
                router.setPagePath(PagePath.LECTURE.getDirectUrl());
                session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.LECTURE.getServletPath() + courseId);
            }
        } catch (ServiceException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
