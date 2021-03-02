package edu.epam.course.command.impl;

import edu.epam.course.command.*;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.service.CourseDetailsService;
import edu.epam.course.model.service.CourseService;
import edu.epam.course.model.service.LectureService;
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
    private CourseService courseService;

    public OpenLectureCommand(LectureService lectureService, CourseDetailsService courseDetailsService,
                              CourseService courseService) {
        this.lectureService = lectureService;
        this.courseDetailsService = courseDetailsService;
        this.courseService = courseService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String courseId = request.getParameter(RequestParameter.COURSE_ID);
        try {
            Long courseIdLong = Long.valueOf(courseId);
            Optional<Course> courseById = courseService.findCourseById(courseIdLong);
            if (courseById.isPresent()) {
                List<Lecture> lectures = lectureService.findAllLectureByCourseId(courseIdLong);
                Optional<CourseDetails> courseDetails = courseDetailsService.findCourseDetailsById(courseIdLong);
                if (!lectures.isEmpty()) {
                    request.setAttribute(RequestAttribute.LECTURES, lectures);
                }
                if (courseDetails.isPresent()) {
                    request.setAttribute(RequestAttribute.COURSE_DETAILS, courseDetails.get());
                }
                request.setAttribute(RequestAttribute.COURSE_ID, courseById.get().getId());
                router.setPagePath(PagePath.LECTURE.getDirectUrl());
                session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.LECTURE.getServletPath() + courseId);
            } else {
                router.setPagePath(PagePath.ERROR_404.getDirectUrl());
            }
        } catch (ServiceException | NumberFormatException e) {
            logger.error(e);
            router.setPagePath(PagePath.ERROR_500.getDirectUrl());
            request.setAttribute(RequestAttribute.EXCEPTION, e.getMessage());
        }
        return router;
    }
}
